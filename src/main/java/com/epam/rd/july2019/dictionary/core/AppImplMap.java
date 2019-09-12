package com.epam.rd.july2019.dictionary.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


public class AppImplMap {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppImplMap.class);

    public static void main(String[] args) throws Exception {

        String fileName = "";

        String prefix = "";

        if (args.length == 2) {

            fileName = args[0];

            prefix = args[1];


        } else {

            LOGGER.info("You should fill a command line! File name and prefix.");
            LOGGER.info("Example of load: java -jar homework.jar words_alpha.txt pref");

            System.exit(0);
        }

        dictionary(fileName, prefix);

    }

    public static void dictionary(String fileNameRead, String prefix) throws Exception{

        MapImpl<String, String> dictionary = new MapImpl<String, String>();

        if (!Files.isRegularFile(Paths.get(fileNameRead))) {
            throw new Exception("A file is not exist!");
        }

        try(BufferedReader reader = new BufferedReader(new FileReader(fileNameRead)))
        {
            while (reader.ready()) {

                String stringFromFile = reader.readLine();

                if (stringFromFile.equals("")) {
                    continue;
                }

                String[] words = stringFromFile.split("\\s+");


                for(String word: words) {

                    word = word.replace(".", "");
                    word = word.replace(",", "");

                    if (!word.startsWith(prefix)) {
                        continue;
                    }

                    dictionary.put(word, word.substring(0, prefix.length()));

                }
            }

        } catch (IOException ex) {
            ex.printStackTrace();

        }

        int countWords = 0;

        Iterator<MapImpl.Entry<String, String>> iteratorDictionary = dictionary.iterator();

        while (iteratorDictionary.hasNext()) {

            MapImpl.Entry<String, String> entryDictionary = iteratorDictionary.next();

            if (entryDictionary.value.equals(prefix)) {

                countWords++;

                LOGGER.info(entryDictionary.key);

            }

        }

        LOGGER.info("Number words with prefix " + prefix + ": " + countWords);

    }

}
