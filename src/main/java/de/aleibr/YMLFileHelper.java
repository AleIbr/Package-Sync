package de.aleibr;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class YMLFileHelper {

    public static void createYMLFile(Map<String, List<String>> packages, String name){
        DumperOptions options = new DumperOptions();
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);

        Yaml yaml = new Yaml(options);
        try(FileWriter writer = new FileWriter(name + ".yml")){
            yaml.dump(packages, writer);
        } catch (IOException ignored) {
            throw  new RuntimeException("Can't create YML file");
        }
    }

}
