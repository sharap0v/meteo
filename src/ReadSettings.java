import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;
import java.io.*;

public class ReadSettings {
    private static String json;
    private static JSONObject jo;
    private static String lat = null;
    private static String lan = null;
    private static String file_path;
    private static String readFile(){
        try(FileReader fileReader = new FileReader("settings")){
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            json = bufferedReader.readLine();
            return json;
        } catch (IOException e) {
            json ="{\"lat\": \"59.12014887911697\", \"lon\": \"37.900858623389674\", \"file_path\": \"meteolog.txt\"}";
            return json;
        }
    }
    public static void readSettings() throws ParseException {
        jo = (JSONObject) JSONValue.parseWithException(readFile());
            lat = jo.get("lat").toString();
            lan = jo.get("lon").toString();
            file_path = jo.get("file_path").toString();
    }

    public static String getLat() {
        return lat;
    }

    public static String getLan() {
        return lan;
    }

    public static String getPathname() {
        return file_path;
    }
}
