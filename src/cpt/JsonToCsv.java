package cpt;

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

        try (FileReader reader = new FileReader("data.json"))
        {
            Object obj = parser.parse(reader);
            JSONArray jsonArray = (JSONArray) obj;
            String csv = "";
            for (Object jsonObject : jsonArray) {
                JSONObject json = (JSONObject) jsonObject;
                for (Object key : json.keySet()) {
                    csv += json.get(key) + ",";
                }
                csv += "\n";
            }
            try (FileWriter writer = new FileWriter("data.csv")) {
                writer.write(csv);
                writer.flush();
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}