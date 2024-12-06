package Util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.ProcessBuilder.Redirect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class FileManagement {

   
    public static PrintWriter pw;
    public static final  String filePath="src\\";
    public static final String fileExtension =".json";

    //metodo para crear un archivo json
    public  String createFileJson(String fileJsonName) {
        try {
            pw = new PrintWriter(new FileWriter(filePath + fileJsonName + fileExtension, true));
            return "archivo creado";
        } catch (FileNotFoundException e) {
            return e.getMessage();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    public<T> ArrayList<T> deserealiseObjectCollectionFromJson2(String listPath, Class<T> classType) {
       Gson gson = new Gson();
        String jsonContent = readJsonFile(listPath);
        java.lang.reflect.Type collectionType = TypeToken.getParameterized(ArrayList.class, classType).getType();
        return gson.fromJson(jsonContent, collectionType);
    }

  
    public<T> String serializeObjectCollectionToJson(ArrayList<T> list)   {
        GsonBuilder gsonBuilder=new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson=gsonBuilder.create();

        return gson.toJson(list);
    }



    public String readJsonFile(String path)  {
        StringBuilder sb = new StringBuilder();
        try {
            File file = new File(path);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();// lee la primera linea del archivo
            while (line != null)    {
                sb.append(line);
                line=br.readLine();// lee la siguiente linea
            }
            br.close();
        }catch (FileNotFoundException e)    {
            System.out.println("No ha sido encontrado"+ e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return sb.toString();
    }

    public void saveData(String path, String newContentJson) {
        try (FileWriter writer = new FileWriter(path)) {
            writer.write(newContentJson);

        } catch (Exception e) {
            System.out.println("sin acceso: archivo no encontrado");
        }
    }






}
