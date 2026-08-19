package de.aleibr;

import de.aleibr.Arguments.ExportArgument;

public class ArgumentsHandler {

    public static void argsHandler(String[] args){
        for(String arg : args){
            switch(arg){
                case "-E":
                case "-export":
                    ExportArgument.export();
                    break;
                default:
                    System.out.println("Unknown argument " + arg + " was used");
            }
        }
    }

}
