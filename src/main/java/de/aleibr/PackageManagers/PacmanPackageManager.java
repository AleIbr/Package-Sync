package de.aleibr.PackageManagers;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PacmanPackageManager implements PackageManagerInterface{

    @Override
    public Map<String, List<String>> exportExplicit() {
        try{
            Process process = new ProcessBuilder("pacman", "-Qqe").start();
            process.waitFor();
            return Map.of("pacman|explicit", process.inputReader().lines().collect(Collectors.toList()));
        }catch(IOException | InterruptedException e){
            Thread.currentThread().interrupt();
            return  Map.of();
        }
    }

    @Override
    public Map<String, List<String>> exportDependencies() {
        try{
            Process process = new ProcessBuilder("pacman", "-Qqd").start();
            process.waitFor();
            return Map.of("pacman|dependencies", process.inputReader().lines().collect(Collectors.toList()));
        }catch(IOException | InterruptedException e){
            Thread.currentThread().interrupt();
            return  Map.of();
        }
    }
}
