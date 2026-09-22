package de.aleibr.PackageManagers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PackageManagerDetector {

    private static final Map<String, PackageManagerInterface> packageManagers = Map.ofEntries(
            Map.entry("apt", new AptPackageManager()),
            Map.entry("pacman", new PacmanPackageManager()),
            Map.entry("snap", new SnapPackageManager()),
            Map.entry("dpkg", new DpkgPackageManager()),
            Map.entry("aptitude", new AptitudePackageManager()),
            Map.entry("dnf", new DnfPackageManager()),
            Map.entry("dnf5", new Dnf5PackageManager()),
            Map.entry("yum", new YumPackageManager()),
            Map.entry("microdnf", new MicrodnfPackageManager()),
            Map.entry("rpm", new RpmPackageManager()),
            Map.entry("tdnf", new TdnfPackageManager()),
            Map.entry("pamac", new PacmanPackageManager()),
            Map.entry("yay", new YayPackageManager()),
            Map.entry("paru", new ParuPackageManager()),
            Map.entry("pikaur", new PikaurPackageManager()),
            Map.entry("trizen", new TrizenPackageManager()),
            Map.entry("aurman", new AurmanPackageManager())
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
