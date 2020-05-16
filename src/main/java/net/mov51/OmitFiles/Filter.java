package net.mov51.OmitFiles;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Filter {
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
}
