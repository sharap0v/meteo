import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class MeteoClient{
    private URL url;
    private HttpURLConnection con;

    public MeteoClient(String lat, String lon) throws IOException {
        url = new URL("https://api.weather.yandex.ru/v1/informers?lat=" + lat+ "&lon="+lon);
        con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("X-Yandex-API-Key", "5499c1bf-dc18-4a11-9a1a-77b5a844de59");
        con.setConnectTimeout(1000);
        con.setReadTimeout(1000);
    }
    private String Get(){
    try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        return content.toString();

    } catch (Exception e) {
        return "Нет данных";
    }
    }
    public String jsonParser() throws ParseException {
        JSONObject jo = (JSONObject) JSONValue.parseWithException(Get());
        String date = jo.get("now_dt").toString();
        jo = (JSONObject) JSONValue.parseWithException(jo.get("fact").toString());
        System.out.println(date + " temp " + jo.get("temp").toString()+"\n");
        return date + " temp " + jo.get("temp").toString()+"\n";
    }

}
