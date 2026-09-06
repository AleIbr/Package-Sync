package de.aleibr.PackageManagers;

import java.util.List;
import java.util.Map;

public interface PackageManagerInterface {

    default Map<String, List<String>> exportExplicit(){
        return Map.of();
    }

    default Map<String, List<String>>exportDependencies(){
        return Map.of();
    }

}
