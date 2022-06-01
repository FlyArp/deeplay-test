import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

public class Config {

    public static HashMap<Character, Integer> getConfig(String path,String species) {
        HashMap<Character, Integer> weightOfStep = new HashMap<>();
        JSONParser parser = new JSONParser();

        try {
            JSONObject jsonData = (JSONObject) parser.parse(new FileReader(path));
            JSONObject jsonSpecies = (JSONObject) jsonData.get(species);
            if (jsonSpecies == null) {
                throw new IllegalArgumentException("Wrong species");
            }
            Set<String> keys = jsonSpecies.keySet();
            for (String key : keys) {
                char typeOfField = key.toCharArray()[0];
                int weight = (int)(long)jsonSpecies.get(key);
                weightOfStep.put(typeOfField, weight);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return weightOfStep;
    }
}
