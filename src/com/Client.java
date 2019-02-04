package com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.json.JSONObject;

public class Client {
	public static void main(String[] args) {
		try {
			long startTimeInMillis = System.currentTimeMillis();
			
			String endpoint = "http://192.168.0.13:8080/SOAPExample/services/DemoClass";
			DemoClassProxy dcp = new DemoClassProxy(endpoint);
			String response = dcp.getServerTime();
			
			long endTimeInMillis = System.currentTimeMillis();
			
			
			System.out.println(response);
			
			JSONObject jsonObject = new JSONObject(response);
			long serverTimeInMillis = Long.parseLong((String) jsonObject.get("timeInMillis"));
			
			long calculatedClientTime = (endTimeInMillis - startTimeInMillis) / 2 + serverTimeInMillis;
			
			Calendar newClientTime = Calendar.getInstance();
            newClientTime.setTimeInMillis(calculatedClientTime);
            
            System.out.println("calculated Client Time in Milliseconds is: " + calculatedClientTime);
            System.out.println("calculated Client Time is: " + newClientTime.getTime());

            SimpleDateFormat dateFormatter = new SimpleDateFormat("mmddHHMMyy");

            String strDateToSet = dateFormatter.format(newClientTime.getTime());
            
            setMacTime(strDateToSet);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	static void setMacTime(String strDateToSet) throws IOException, InterruptedException {
		String command = "date " + strDateToSet;

        Process proc = Runtime.getRuntime().exec(command);

        // Read the output

        BufferedReader reader =  
              new BufferedReader(new InputStreamReader(proc.getInputStream()));

        String line = "";
        while((line = reader.readLine()) != null) {
            System.out.print(line + "\n");
        }

        proc.waitFor();   
	}
}
