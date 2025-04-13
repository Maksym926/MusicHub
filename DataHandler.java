import java.io.*;

import com.google.gson.Gson;
import json.*;
public class DataHandler {
    public static void updateLastAPICallCache(Stats stats){

//        File last_api_call_cache = new File("last_api_call_cache.json");
//        if(!last_api_call_cache.exists()){
//            createFile(last_api_call_cache);
//        }
        try{
            Gson gson = new Gson();
            FileWriter writer = new FileWriter("last_api_call_cache.json");
            gson.toJson(stats, writer);
            writer.close();

        }catch (IOException e){
            e.getMessage();
        }



    }
    public static void readCashedData(){
        try{
            Gson gson = new Gson();
            FileReader reader = new FileReader("last_api_call_cache.json");

            Stats stats1 = gson.fromJson(reader, Stats.class);
            if(stats1.getNumberOfListeners() != null){
                System.out.println(stats1.getNumberOfListeners());
            }
            else if(stats1.getPlayCount()!= null){
                System.out.println(stats1.getPlayCount());
            }
        }catch (IOException e){
            e.getMessage();
        }

    }


//    private void createFile(File last_api_call_cache){
//        try{
//            last_api_call_cache.createNewFile();
//        }catch (IOException e){
//            e.getMessage();
//        }
//
//
//    }

}
