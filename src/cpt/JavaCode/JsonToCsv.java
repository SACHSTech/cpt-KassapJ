package cpt.JavaCode;

import java.io.*;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonToCsv {

    public static void main(String[] args) {
        JSONParser parser = new JSONParser();
        File directory =  new File("SpotifyJsonFilesHERE");
        String[] files = directory.list();
        int intJsonAmount = files.length - 1;
        String csv = "";
        double jsonArraySize = 0;
        double count = 0;
        
        //Go through all the streaming history files
        for(int i = 0; i < intJsonAmount; i++){
            try (FileReader reader = new FileReader("SpotifyJsonFilesHERE/StreamingHistory" + i + ".json")){
            // identifying the json array we're reading through
            Object obj = parser.parse(reader);
            JSONArray jsonArray = (JSONArray) obj;
            jsonArraySize += jsonArray.size();
            }catch (IOException | ParseException e) {
                e.printStackTrace();
            }
        }

        for(int i = 0; i < intJsonAmount; i++){
            try (FileReader reader = new FileReader("SpotifyJsonFilesHERE/StreamingHistory" + i + ".json")){
            // identifying the json array we're reading through
            Object obj = parser.parse(reader);
            JSONArray jsonArray = (JSONArray) obj;
            // Creating a string text to format the csv
            for (Object jsonObject : jsonArray) {
                JSONObject json = (JSONObject) jsonObject;
                Iterator<String> iterator = json.keySet().iterator();
                while (iterator.hasNext()) {
                    String key = iterator.next();
                    csv += json.get(key);
                    if (iterator.hasNext()) {
                        csv += ",";
                    }
                }
                csv += "\n";
                count++;
                System.out.println("Processing " + (String.format("%.0f%%",(count / jsonArraySize)*100)));
            }
            System.out.println("File " + (i+1) + " complete");
            }catch (IOException | ParseException e) {
                e.printStackTrace();
            }
        }
            // writing the csv file
            try (FileWriter writer = new FileWriter("ConvertedFiles/data.csv")) {
                writer.write(csv);
                writer.flush();
                System.out.println("File conversion complete");
            }catch (IOException e) {
            e.printStackTrace();
            }
    }
}