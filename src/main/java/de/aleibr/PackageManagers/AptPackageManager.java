package de.aleibr.PackageManagers;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AptPackageManager implements PackageManagerInterface{

    @Override
    public Map<String, List<String>>exportExplicit(){
        try{
            Process process = new ProcessBuilder("apt-mark", "showmanual").start();
            process.waitFor();
            return Map.of("apt|explicit", process.inputReader().lines().collect(Collectors.toList()));
        }catch(IOException | InterruptedException e){
            Thread.currentThread().interrupt();
            return  Map.of();
        }
    }

    @Override
    public Map<String, List<String>>exportDependencies(){
        try{
            Process process = new ProcessBuilder("apt-mark", "showauto").start();
            process.waitFor();
            return Map.of("apt|dependencies", process.inputReader().lines().collect(Collectors.toList()));
        }catch(IOException | InterruptedException e){
            Thread.currentThread().interrupt();
            return  Map.of();
        }
    }



}
