import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String json = readString("src/main/java/new_data.json");
        List<Employee> list = jsonToList(json);
        for (Employee emp : list) {
            System.out.println(emp);
        }
    }

    private static List<Employee> jsonToList(String json) {
        JSONParser parser = new JSONParser();
        List<Employee> listEmp = new ArrayList<>();
        try {
            JSONArray arrayEmp = (JSONArray) parser.parse(json);
            for (Object jsonObject : arrayEmp) {
                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();
                listEmp.add(gson.fromJson(jsonObject.toString(), Employee.class));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return listEmp;
    }

    private static String readString(String path) {
        JSONParser parser = new JSONParser();
        try {
            return parser.parse(new FileReader(path)).toString();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
