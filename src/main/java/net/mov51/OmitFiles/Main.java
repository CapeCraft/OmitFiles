package net.mov51.OmitFiles;

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

    public static void main(String[] args) {
        if(args.length<2){//If total passed args is smaller than 2 then exit
            System.out.println("Please fill out your arguments as follows");
            System.out.println("<Filter file path> <Direction in which to filter your credentials (hide|show)>");
            System.exit(0);//Exits the jvm after error
        }
        if (args[1].equals("hide")){//Checks the argument passed for desired output
            //TODO Allow for different/better worded inputs
            FilterParse(2,1, args[0]);//Passes replacement order to the FilterAction method
        }else if(args[1].equals("show")){
            FilterParse(1,2, args[0]);//Passes replacement order to the FilterAction method
        }else{//If no values match then print help and exit
            System.out.println("Please choose an option for argument 2!");
            System.out.println("<Direction in which to filter (hide|show)>");
            System.exit(0);//Exits the jvm after error
        }
    }

    public static void FilterAction(String FilePath, String filter, String replacement){
            Path File = Paths.get(FilePath);//Retrieves the full path from the first section of the FilterFie line
            Charset charset = StandardCharsets.UTF_8;//Defines the character set
            String content = null;//Defines the content buffer string
            try {
                content = new String(Files.readAllBytes(File), charset);//places file path into a content buffer
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(0);//Exits the jvm after error
            }
            content = content.replace(filter, replacement);//Replaces value 1 "from" with Value 2 "to"
            try {
                Files.write(File, content.getBytes(charset));//Writes modified content buffer
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(0);//Exits the jvm after error
            }
    }

    public static void FilterParse(int filterFrom, int filterTo, String FileLocation){
        Scanner FilterFile = null;//Defines file scanner
        try {
            FilterFile = new Scanner(new File(FileLocation));//Adds file location to scanner
        } catch (FileNotFoundException e) {
            System.out.println("Your file did not exist :(");
            System.out.println(FileLocation);
            System.out.println("Please pass the path to your file as the first argument!");
            System.exit(0);//Exits the jvm after error
        }
        while (FilterFile.hasNextLine()){//Runs while FilterFile has another line to process
            String line = FilterFile.nextLine();//Sets line to the current FilterFile line
            String[] SingleLine = line.split("-");//Splits the filter line based on deliminator TODO Allow for different deliminators
            FilterAction(SingleLine[0] , SingleLine[filterFrom], SingleLine[filterTo]);
        }

    }
}

