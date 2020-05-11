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
        if(args.length<2){
            System.out.println("Please fill out your arguments as follows");
            System.out.println("<Filter file path> <Direction in which to filter your credentials (hide|show)>");
            System.exit(0);
        }
        if (args[1].equals("hide")){
            FilterAction(2,1, args[0]);
        }else if(args[1].equals("show")){
            FilterAction(1,2, args[0]);
        }else{
            System.out.println("Please choose an option for argument 2!");
            System.out.println("<Direction in which to filter (hide|show)>");
            System.exit(0);
        }
    }

    public static void FilterAction(int from, int to, String FileLocation){
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(FileLocation));
        } catch (FileNotFoundException e) {
            System.out.println("Your file did not exist :(");
            System.out.println(FileLocation);
            System.out.println("Please pass the path to your file as the first argument!");
            System.exit(0);
        }
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            // process the line
            String[] SingleLine = line.split("-");
            Path File = Paths.get(SingleLine[0]);
            Charset charset = StandardCharsets.UTF_8;
            String content = null;
            try {
                content = new String(Files.readAllBytes(File), charset);
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(0);
            }
            content = content.replace(SingleLine[from], SingleLine[to]);
            try {
                Files.write(File, content.getBytes(charset));
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(0);
            }
        }
    }
}
