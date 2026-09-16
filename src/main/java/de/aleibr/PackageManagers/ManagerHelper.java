package de.aleibr.PackageManagers;

import de.aleibr.YMLFileHelper;

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

    public void dependencyExport(String filePath){
        Map<String, List<String>> packages = new HashMap<>();
        for(String packageManager : availablePackageManagers.keySet()){
            packages.putAll(availablePackageManagers.get(packageManager).exportDependencies());
        }
        YMLFileHelper.createYMLFile(packages, "Export-Dependencies", filePath);
    }

    public void importPackages(String filePath){

    }

}
