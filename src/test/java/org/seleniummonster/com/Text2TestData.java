package org.seleniummonster.com;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Text2TestData {

    private String fileLocation;

    public Text2TestData(String fileLocation) {
        this.fileLocation = fileLocation;
    }



    public HashMap<String,String[]> getData(){
        FileInputStream fs;
        HashMap<String,String[]> keyValuePair=new HashMap<String,String[]>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileLocation))){
            String stringLine;
            //Read File Line By Line
            while ((stringLine = br.readLine()) != null)   {
                // Print the content on the console
                String[] keyValue=stringLine.split("=");
                keyValuePair.put(keyValue[0],keyValue[1].split(","));
            }


        } catch (Exception e) {
            e.printStackTrace();
        }


        return keyValuePair;


    }

}
