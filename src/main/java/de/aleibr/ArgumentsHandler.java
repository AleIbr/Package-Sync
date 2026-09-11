package de.aleibr;

import org.apache.commons.cli.*;

public class ArgumentsHandler {

    public static void argsHandler(String[] args){
        Option export = Option.builder("E")
                .longOpt("export")
                .optionalArg(true)
                .argName("FILE")
                .desc("exports all packages")
                .get();

        OptionGroup operations = new OptionGroup();
        operations.addOption(export);
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
                        helper.export();
                        break;
                }
            }
        }catch(ParseException e){
            HelpFormatter helpFormatter = new HelpFormatter();
            helpFormatter.printHelp("help", options);
        }
    }

}
