package net.mov51.OmitFiles;

import net.mov51.OmitFiles.ParseUtils.JSONtools;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        if(args.length<2){//If total passed args is smaller than 2 then exit
            System.out.println("Please fill out your arguments as follows");
            System.out.println("<Filter file path> <Direction in which to filter your credentials (hide|show)>");
            System.exit(0);//Exits the jvm after error
        }
        if (args[1].equals("show")){//Checks the argument passed for desired output
            JSONtools.Parse("filter","key", args[0]);//Passes replacement order to the FilterAction method
        }else if(args[1].equals("hide")){
            JSONtools.Parse("key","filter", args[0]);//Passes replacement order to the FilterAction method
        }else{//If no values match then print help and exit
            System.out.println("Please choose an option for argument 2!");
            System.out.println("<Direction in which to filter (hide|show)>");
            System.exit(0);//Exits the jvm after error
        }
    }


}

