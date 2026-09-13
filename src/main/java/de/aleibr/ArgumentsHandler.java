package de.aleibr;

import org.apache.commons.cli.*;

public class ArgumentsHandler {

    public static void argsHandler(String[] args){
        Option export = Option.builder("E")
                .longOpt("export")
                .optionalArg(true)
                .argName("FILEPATH")
                .desc("exports all packages")
                .get();
        Option explicitExport = Option.builder("EE")
                .longOpt("explicitExport")
                .optionalArg(true)
                .argName("FILEPATH")
                .desc("exports all packages that were manually installed")
                .get();
        Option help = Option.builder("H")
                .longOpt("help")
                .desc("showing all existing commands")
                .get();

        OptionGroup operations = new OptionGroup();
        operations.addOption(export);
        operations.addOption(explicitExport);
        operations.addOption(help);
        operations.setRequired(true);

        Options options = new Options();
        options.addOptionGroup(operations);
        ManagerHelper helper = new ManagerHelper();

        CommandLineParser parser = new DefaultParser();
        try{
            CommandLine cmd = parser.parse(options, args);

            for(Option option : cmd.getOptions()){
                switch(option.getLongOpt()){
                    case "export":
                        String exportPath = cmd.getOptionValue("export", "");
                        if(!exportPath.isBlank()){
                            exportPath = exportPath + "/";
                        }
                        helper.export(exportPath);
                        break;
                    case "explicitExport":
                        String explicitExportPath = cmd.getOptionValue("explicitExport", "");
                        if(!explicitExportPath.isBlank()){
                            explicitExportPath = explicitExportPath + "/";
                        }
                        helper.explicitExport(explicitExportPath);
                        break;
                    case "help":
                        HelpFormatter helpFormatter = new HelpFormatter();
                        helpFormatter.printHelp("help", options);
                        break;
                }
            }
        }catch(ParseException e){
            HelpFormatter helpFormatter = new HelpFormatter();
            helpFormatter.printHelp("help", options);
        }
    }

}
