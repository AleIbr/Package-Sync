package de.aleibr.PackageManagers;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TdnfPackageManager implements PackageManagerInterface{

    @Override
    public Map<String, List<String>> exportExplicit(){
        try{
            Process process = new ProcessBuilder("tdnf", "list", "installed").start();
            process.waitFor();
            return Map.of("tdnf|explicit", process.inputReader().lines().collect(Collectors.toList()));
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
                process = new ProcessBuilder("sudo", "tdnf", "install", "-y", currentPackage).inheritIO().start();
                process.waitFor();
            }
        }catch(IOException | InterruptedException e){
            Thread.currentThread().interrupt();
            throw new RuntimeException("Can't import packages: \n" + e);
        }
    }

}
