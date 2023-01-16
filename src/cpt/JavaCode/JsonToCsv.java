package cpt.JavaCode;

import java.io.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonToCsv {

    public static void main(String[] args) {
        JSONParser parser = new JSONParser();
        String directory =  "src/cpt/SpotifyJsonFilesHERE/DROPFILESHERE.txt";
        String[] files = directory.list()
        //choosing the file to read
        try (FileReader reader = new FileReader("src/cpt/SpotifyJsonFilesHERE/StreamingHistory0.json"))
        {
            // identifying the json array we're reading through
            Object obj = parser.parse(reader);
            JSONArray jsonArray = (JSONArray) obj;
            String csv = "";
            // Creating a string text to format the csv
            for (Object jsonObject : jsonArray) {
                JSONObject json = (JSONObject) jsonObject;
                for (Object key : json.keySet()) {
                    csv += json.get(key) + ",";
                }
                csv += "\n";
            }

            // writing the csv file
            try (FileWriter writer = new FileWriter("src/cpt/ConvertedFiles/data.csv")) {
                writer.write(csv);
                writer.flush();
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}