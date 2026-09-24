package de.aleibr.PackageManagers;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ApkPackageManager implements PackageManagerInterface{

    @Override
    public Map<String, List<String>> exportExplicit(){
        try{
            Process process = new ProcessBuilder("apk", "info", "--installed", "--manual").start();
            process.waitFor();
            return Map.of("apk|explicit", process.inputReader().lines().collect(Collectors.toList()));
        }catch(IOException | InterruptedException e){
            Thread.currentThread().interrupt();
            return  Map.of();
        }
    }

    @Override
    public Map<String, List<String>>exportDependencies(){
        try{
            Process process = new ProcessBuilder("apk", "info", "--installed", "--dependencies").start();
            process.waitFor();
            return Map.of("apk|dependencies", process.inputReader().lines().collect(Collectors.toList()));
        }catch(IOException | InterruptedException e){
            Thread.currentThread().interrupt();
            return  Map.of();
        }
    }

    @Override
    public void importPackages(List<String> packages){
        try{
            Process process;
            for(String currentPackage : packages){
                process = new ProcessBuilder("sudo", "apk", "add", "--no-cache", currentPackage).inheritIO().start();
                process.waitFor();
            }
        }catch(IOException | InterruptedException e){
            Thread.currentThread().interrupt();
            throw new RuntimeException("Can't import packages: \n" + e);
        }
    }

}
