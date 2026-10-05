package de.aleibr.packagesync.PackageManagers;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SnapPackageManager implements PackageManagerInterface{

    @Override
    public Map<String, List<String>> exportExplicit(){
        try{
            Process process = new ProcessBuilder("snap", "list").start();
            process.waitFor();
            return Map.of("snap|explicit", process.inputReader().lines().skip(1).map(line -> line.trim().split("\\s+")[0]).collect(Collectors.toList()));
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
                process = new ProcessBuilder("sudo", "snap", "install", currentPackage).inheritIO().start();
                process.waitFor();
            }
        }catch(IOException | InterruptedException e){
            Thread.currentThread().interrupt();
            throw new RuntimeException("Can't import packages: \n" + e);
        }
    }

}
