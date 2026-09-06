package de.aleibr;


public class ArgumentsHandler {

    public static void argsHandler(String[] args){
        ManagerHelper helper = new ManagerHelper();
        for(String arg : args){
            switch(arg){
                case "-E":
                case "-export":
                    helper.export();
                    break;
                default:
                    System.out.println("Unknown argument " + arg + " was used");
            }
        }
    }

}
