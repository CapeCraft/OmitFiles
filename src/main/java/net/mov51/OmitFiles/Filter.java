package net.mov51.OmitFiles;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Filter {

    static final Logger Log = Logger.getLogger(Filter.class);

    public static void FilterAction(String FilePath, String filter, String replacement){

        if(Main.Debug == 1){
            Logger.getRootLogger().setLevel(Level.DEBUG);
        }

        Log.debug("File path string is " + FilePath);
        Path File = Paths.get(FilePath);//Retrieves the full path from the first section of the FilterFie line
        Log.debug("File path path is " + File);
        Charset charset = StandardCharsets.UTF_8;//Defines the character set
        String content = null;//Defines the content buffer string
        try {
            content = new String(Files.readAllBytes(File), charset);//places file path into a content buffer
        } catch (IOException e) {
            Log.error("Your target file to filter does not exist!");
            Log.error(File.toString());//Prints attempted file path
        }
        Log.debug("Content filter is " + filter);
        Log.debug("Content replacement is " + replacement);
        if (content != null) {
            content = content.replace(filter, replacement);//Replaces value 1 "from" with Value 2 "to"
        }else{
            Log.error("File To Filter \"" + FilePath + "\" is empty");
            Log.error("It will not be filtered");
        }

        try {
            assert content != null;
            Files.write(File, content.getBytes(charset));//Writes modified content buffer
        } catch (IOException e) {
            Log.error("The program failed to write the filtered file!");
            Log.error(File);//Prints attempted file path
        }

    }
}
