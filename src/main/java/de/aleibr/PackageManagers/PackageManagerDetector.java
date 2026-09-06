package de.aleibr.PackageManagers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PackageManagerDetector {

    private static final Map<String, PackageManagerInterface> packageManagers = Map.of(
            "apt", new AptPackageManager(),
            "pacman", new PacmanPackageManager(),
            "snap", new SnapPackageManager()
    );

    public static Map<String, PackageManagerInterface> detectPackageManagers(){
        Map<String, PackageManagerInterface> installedPackageManagers = new HashMap<>();
        for(String packageManager : packageManagers.keySet()){
            if(isInstalled(packageManager)){
                installedPackageManagers.put(packageManager, packageManagers.get(packageManager));
            }
        }
        return installedPackageManagers;
    }

    private static boolean isInstalled(String packageManager){
        try{
            Process process = new ProcessBuilder("sh", "-c", "command -v " + packageManager)
                    .redirectErrorStream(true)
                    .start();
            return process.waitFor() == 0;
        }catch(IOException | InterruptedException e){
            Thread.currentThread().interrupt();
            return false;
        }
    }

}
