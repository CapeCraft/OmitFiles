package net.mov51.OmitFiles.ParseUtils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.extern.log4j.Log4j2;
import net.mov51.OmitFiles.Filter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.Reader;

@Log4j2
public class JSONtools {

    public static void Parse(String from, String to, String filterFile) {

        Reader reader = null;//Defines reader
        try {
            reader = new InputStreamReader(new FileInputStream(filterFile));//Places file path into reader
        } catch (FileNotFoundException e) {
            log.error("Your file did not exist :(");
            log.error(filterFile);//Prints attempted file path
            log.error("Please pass the path to your file as the first argument!");
            e.printStackTrace();
            System.exit(0);//Exits the jvm after error
        }
        log.info("Provided JSON file found");
        JsonArray rootObj = JsonParser.parseReader(reader).getAsJsonArray();//Parses the reader into a JsonArray
        for (JsonElement element : rootObj.getAsJsonArray()) {//Loops through each element in the json array
            log.debug("Json object is " + element);
            JsonObject filterObj = element.getAsJsonObject();//filters the element into a new json object
            String fileToFilter = filterObj.get("file").getAsString();//Gets target file from json element
            log.debug(String.format("%s > %s", from, to));
            String replaceFrom = filterObj.get(from).getAsString();//Uses passed from value to find either key or filter
            String replaceTo = filterObj.get(to).getAsString();//Uses passed too value to find either key or filter
            Filter.FilterAction(fileToFilter, replaceFrom, replaceTo);//calls filter action with parsed json element
        }
    }
}
