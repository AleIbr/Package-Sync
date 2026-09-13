package de.aleibr;

import de.aleibr.PackageManagers.PackageManagerDetector;
import de.aleibr.PackageManagers.PackageManagerInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManagerHelper {

    private final Map<String, PackageManagerInterface> availablePackageManagers;

    public ManagerHelper(){
        availablePackageManagers = PackageManagerDetector.detectPackageManagers();
    }

    public void export(String filePath){
        Map<String, List<String>> packages = new HashMap<>();
        for(String packageManager : availablePackageManagers.keySet()){
            packages.putAll(availablePackageManagers.get(packageManager).exportExplicit());
            packages.putAll(availablePackageManagers.get(packageManager).exportDependencies());
        }
        YMLFileHelper.createYMLFile(packages, "Export-All", filePath);
    }

    public void explicitExport(String filePath){
        Map<String, List<String>> packages = new HashMap<>();
        for(String packageManager : availablePackageManagers.keySet()){
            packages.putAll(availablePackageManagers.get(packageManager).exportExplicit());
        }
        YMLFileHelper.createYMLFile(packages, "Export-Explicit", filePath);
    }

}
