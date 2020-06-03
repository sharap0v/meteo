import org.json.simple.parser.ParseException;

import java.io.*;

public class Main {
    public static File file;
    public static void main(String[] args) throws IOException {
        try{
            ReadSettings.readSettings();
            file= new File(ReadSettings.getPathname());
            file.createNewFile();
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true));
            bufferedWriter.write(new MeteoClient(ReadSettings.getLat(),ReadSettings.getLan()).jsonParser());
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch(ParseException | NullPointerException e){
            System.out.println("Не валидные настройки");
            System.out.println("пример валидных настроек");
            System.out.println("{\"lat\": \"59.12014887911697\", \"lon\": \"37.900858623389674\", \"file_path\": \"meteolog.txt\"}");
        }
    }

}
