package de.aleibr.packagesync.PackageManagers;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Dnf5PackageManager implements PackageManagerInterface{

    @Override
    public Map<String, List<String>> exportExplicit(){
        try{
            Process process = new ProcessBuilder("dnf5", "repoquery", "--installed", "--userinstalled", "--queryformat", "%{name}\\n").start();
            process.waitFor();
            return Map.of("dnf5|explicit", process.inputReader().lines().collect(Collectors.toList()));
        }catch(IOException | InterruptedException e){
            Thread.currentThread().interrupt();
            return  Map.of();
        }
    }

    @Override
    public Map<String, List<String>>exportDependencies(){
        try{
            Process process = new ProcessBuilder("dnf5", "repoquery", "--installed", "--queryformat", "%{name}\\n").start();
            process.waitFor();
            return Map.of("dnf5|dependencies", process.inputReader().lines().collect(Collectors.toList()));
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
                process = new ProcessBuilder("sudo", "dnf5", "install", "-y", currentPackage).inheritIO().start();
                process.waitFor();
            }
        }catch(IOException | InterruptedException e){
            Thread.currentThread().interrupt();
            throw new RuntimeException("Can't import packages: \n" + e);
        }
    }

}
