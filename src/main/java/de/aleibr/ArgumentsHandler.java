package de.aleibr;

public class ArgumentsHandler {

    public static void argsHandler(String[] args){
        for(String arg : args){
            switch(arg){
                default:
                    System.out.println("Unknown argument " + arg + " was used");
            }
        }
    }

}
