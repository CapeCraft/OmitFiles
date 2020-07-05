package net.mov51.OmitFiles;

import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Log4j2
public class Filter {

    public static void FilterAction(String filePath, String filter, String replacement) {

        log.debug("File path string is {}", filePath);
        Path file = Paths.get(filePath);//Retrieves the full path from the first section of the FilterFie line
        log.debug("File path path is {}", file);
        Charset charset = StandardCharsets.UTF_8;//Defines the character set
        String content = null;//Defines the content buffer string
        try {
            content = new String(Files.readAllBytes(file), charset);//places file path into a content buffer
        } catch (IOException e) {
            log.error("Your target file to filter does not exist!");
            log.error(file.toString());//Prints attempted file path
        }
        log.debug("Content filter is {}", filter);
        log.debug("Content replacement is {}", replacement);
        if (content != null) {
            content = content.replace(filter, replacement);//Replaces value 1 "from" with Value 2 "to"
        } else {
            log.error("File To Filter {} is empty", filePath);
            log.error("It will not be filtered");
        }

        try {
            assert content != null;
            Files.write(file, content.getBytes(charset));//Writes modified content buffer
        } catch (IOException e) {
            log.error("The program failed to write the filtered file!");
            log.error(file);//Prints attempted file path
        }

    }
}
