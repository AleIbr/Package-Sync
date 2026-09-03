package de.aleibr.PackageManagers;

import java.util.List;
import java.util.Map;

public interface PackageManagerInterface {

    Map<String, List<String>> exportExplicit();

    Map<String, List<String>>exportDependencies();

}
