package org.seleniummonster.com;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Text2TestData {
	
		private String fileLocation;
	
		public Text2TestData(String fileLocation) {
			super();
			this.fileLocation = fileLocation;
		}

	
	
	public HashMap<String,String[]> getData(){
		
		 FileInputStream fs;
		 HashMap<String,String[]> keyValuePair=new HashMap<String,String[]>();
		try {
			
			  fs = new FileInputStream(fileLocation);
			  DataInputStream in = new DataInputStream(fs);
			  BufferedReader br = new BufferedReader(new InputStreamReader(in));
			  
			  String stringLine;
			  //Read File Line By Line
			  while ((stringLine = br.readLine()) != null)   {
			  // Print the content on the console
			  String[] keyValue=stringLine.split("=");
			  keyValuePair.put(keyValue[0],keyValue[1].split(","));
              }
			  //Close the input stream
			  in.close();
			  
		} catch (Exception e) {
			e.printStackTrace();
		}

	  
	     return keyValuePair;
		
		
	}

}
