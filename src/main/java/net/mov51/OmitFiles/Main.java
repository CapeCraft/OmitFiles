package net.mov51.OmitFiles;

import net.mov51.OmitFiles.ParseUtils.JSONtools;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class Main {

    static final Logger Log = Logger.getLogger("GLOBAL");
    public static int Debug = 0;

    public static void main(String[] args){

        BasicConfigurator.configure();
        Logger.getRootLogger().setLevel(Level.INFO);//Default log mode

        if (args.length == 3){
            if (args[2].equalsIgnoreCase("debug")){
                Logger.getRootLogger().setLevel(Level.DEBUG);
                Debug = 1; //Define global Debug TODO Allows for multiple Debug modes
            }else {
                Log.error("Debug mode not defined, please refer to the wiki for debug modes");
            }
        }

        //arg[0] should be a relative path to the desired filter file
        //arg[1] should be the mode in which you want the program to run in
        if(args.length<2){//If total passed args is smaller than 2 then exit
            Log.error("Please fill out your arguments as follows");
            Log.info("<Filter file path> <Direction in which to filter your credentials (hide|show)>");
            System.exit(0);//Exits the jvm after error
        } else if (args[1].equals("show")){//Show places the credentials back into each file
            Log.info("Action value is " + args[1]);
            Log.debug("Expected action value is show");
            //Filter is the variable used to hide credentials
            //Key is the credential used when the program starts
            JSONtools.Parse("filter","key", args[0]);//Passes replacement order to the FilterAction method
        }else if(args[1].equals("hide")){//Hide places the filter into each file in place of the credential
            Log.info("Action value is " + args[1]);
            Log.debug("Expected action value is hide");
            //Filter is the variable used to hide credentials
            //Key is the credential used when the program starts
            JSONtools.Parse("key","filter", args[0]);//Passes replacement order to the FilterAction method
        }else{//If no values match then print help and exit
            System.out.println("Please choose an option for argument 2!");
            System.out.println("<Direction in which to filter (hide|show)>");
            System.exit(0);//Exits the jvm after error
        }
    }


}

