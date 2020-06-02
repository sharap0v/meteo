import org.json.simple.parser.ParseException;

import java.io.*;

public class Main {
    private static Double lat = null;
    private static Double lan = null;
    private static int interval;
    private static boolean exit = true;
    public static void main(String[] args) throws IOException {
        ConsoleHelper.writeMessage("lat\n" +
                "Широта в градусах. Обязательное поле.\n"+
                "Для Череповца 59.12014887911697");
        lat = ConsoleHelper.readDouble();
        ConsoleHelper.writeMessage("lon\n" +
                "Долгота в градусах. Обязательное поле.\n"+
                "Для Череповца 37.900858623389674");
        lan = ConsoleHelper.readDouble();
        ConsoleHelper.writeMessage("interval\n" +
                "Интервал запроса в минутах. Обязательное поле.");
        interval = ConsoleHelper.readInteger();
        File file = new File("meteoinfo.txt");
        file.createNewFile();
         Thread thread = new Thread(new Runnable() {
             @Override
             public void run() {
                 while(!Thread.currentThread().isInterrupted()){
                     try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))){
                         bufferedWriter.write(new MeteoClient(lat,lan).jsonParser());
                         bufferedWriter.flush();
                         Thread.sleep(60000*interval);
                     }
                     catch (IOException | InterruptedException | ParseException e){
                         Thread.currentThread().interrupt();
                     }}
             }
         });
         thread.start();
         while(true) {
             if(ConsoleHelper.readString().equals("exit")){
                 exit = false;
                 thread.interrupt();
                 break;
             }
         }
    }

}
