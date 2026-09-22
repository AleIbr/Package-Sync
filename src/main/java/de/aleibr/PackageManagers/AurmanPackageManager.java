package de.aleibr.PackageManagers;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AurmanPackageManager implements PackageManagerInterface{

    @Override
    public Map<String, List<String>> exportExplicit(){
        try{
            Process process = new ProcessBuilder("aurman", "-Qeq").start();
            process.waitFor();
            return Map.of("aurman|explicit", process.inputReader().lines().collect(Collectors.toList()));
        }catch(IOException | InterruptedException e){
            Thread.currentThread().interrupt();
            return  Map.of();
        }
    }

    @Override
    public Map<String, List<String>>exportDependencies(){
        try{
            Process process = new ProcessBuilder("aurman", "-Qdq").start();
            process.waitFor();
            return Map.of("aurman|dependencies", process.inputReader().lines().collect(Collectors.toList()));
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
                process = new ProcessBuilder("sudo", "aurman", "-S", "--noconfirm", currentPackage).inheritIO().start();
                process.waitFor();
            }
        }catch(IOException | InterruptedException e){
            Thread.currentThread().interrupt();
            throw new RuntimeException("Can't import packages: \n" + e);
        }
    }

}
