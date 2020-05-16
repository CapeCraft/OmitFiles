package net.mov51.OmitFiles;

import net.mov51.OmitFiles.ParseUtils.JSONtools;

public class Main {

    public static void main(String[] args){
        //arg[0] should be a relative path to the desired filter file
        //arg[1] should be the mode in which you want the program to run in
        if(args.length<2){//If total passed args is smaller than 2 then exit
            System.out.println("Please fill out your arguments as follows");
            System.out.println("<Filter file path> <Direction in which to filter your credentials (hide|show)>");
            System.exit(0);//Exits the jvm after error
        }
        if (args[1].equals("show")){//Show places the credentials back into each file
            //Filter is the variable used to hide credentials
            //Key is the credential used when the program starts
            JSONtools.Parse("filter","key", args[0]);//Passes replacement order to the FilterAction method
        }else if(args[1].equals("hide")){//Hide places the filter into each file in place of the credential
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

