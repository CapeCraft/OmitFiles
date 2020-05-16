package net.mov51.OmitFiles.ParseUtils;


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.mov51.OmitFiles.Filter;

import java.io.*;

public class JSONtools {
    public static void Parse(String from, String too, String FilterFile){

        Reader reader = null;
        try {
            reader = new InputStreamReader(new FileInputStream(FilterFile));
        } catch (FileNotFoundException e) {
            System.out.println("Your file did not exist :(");
            System.out.println(FilterFile);
            System.out.println("Please pass the path to your file as the first argument!");
            System.exit(0);//Exits the jvm after error
            e.printStackTrace();
        }
        JsonArray rootObj = JsonParser.parseReader(reader).getAsJsonArray();
            for (JsonElement element : rootObj.getAsJsonArray()){
                JsonObject filterObj = element.getAsJsonObject();
                String FileToFilter = filterObj.get("file").getAsString();
                String ReplaceFrom = filterObj.get(from).getAsString();
                String ReplaceToo = filterObj.get(too).getAsString();
                Filter.FilterAction(FileToFilter, ReplaceFrom, ReplaceToo);
            }
    }
}
