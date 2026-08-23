package de.aleibr.PackageManagers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PackageManagerDetector {

    private static final String[] packageManagers = {
            "apt",
            "pacman"
    };

    public static List<String> detectPackageManagers(){
        List<String> installedPackageManagers = new ArrayList<>();
        for(String packageManager : packageManagers){
            if(isInstalled(packageManager)){
                installedPackageManagers.add(packageManager);
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
