package net.mov51.OmitFiles.ParseUtils;


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.mov51.OmitFiles.Filter;
import net.mov51.OmitFiles.Main;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.io.*;
public class JSONtools {

    static final Logger Log = Logger.getLogger(Filter.class);

    public static void Parse(String from, String too, String FilterFile){

        if(Main.Debug == 1){
            Logger.getRootLogger().setLevel(Level.DEBUG);
        }

        Reader reader = null;//Defines reader
        try {
            reader = new InputStreamReader(new FileInputStream(FilterFile));//Places file path into reader
        } catch (FileNotFoundException e) {
            System.out.println("Your file did not exist :(");
            System.out.println(FilterFile);//Prints attempted file path
            System.out.println("Please pass the path to your file as the first argument!");
            e.printStackTrace();
            System.exit(0);//Exits the jvm after error
        }
        Log.info("File found");
        JsonArray rootObj = JsonParser.parseReader(reader).getAsJsonArray();//Parses the reader into a JsonArray
            for (JsonElement element : rootObj.getAsJsonArray()){//Loops through each element in the json array
                Log.debug("Json object is " + element);
                JsonObject filterObj = element.getAsJsonObject();//filters the element into a new json object
                String FileToFilter = filterObj.get("file").getAsString();//Gets target file from json element
                Log.info(from + " > " + too);
                String ReplaceFrom = filterObj.get(from).getAsString();//Uses passed from value to find either key or filter
                String ReplaceToo = filterObj.get(too).getAsString();//Uses passed too value to find either key or filter
                Filter.FilterAction(FileToFilter, ReplaceFrom, ReplaceToo);//calls filter action with parsed json element
            }
    }
}
