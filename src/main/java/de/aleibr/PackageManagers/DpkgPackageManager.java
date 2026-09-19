package de.aleibr.PackageManagers;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DpkgPackageManager implements PackageManagerInterface{

    @Override
    public Map<String, List<String>> exportExplicit(){
        try{
            Process process = new ProcessBuilder("dpkg-query", "-W", "-f=${binary:Package}\\n").start();
            process.waitFor();
            return Map.of("dpkg|explicit", process.inputReader().lines().collect(Collectors.toList()));
        }catch(IOException | InterruptedException e){
            Thread.currentThread().interrupt();
            return  Map.of();
        }
    }

}
