package com.example;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;  
import java.text.SimpleDateFormat;  
import java.util.Date;
import java.util.Scanner;
import java.util.Calendar;
import org.apache.tomcat.util.http.fileupload.FileUploadBase.IOFileUploadException;
//import org.apache.naming.java.javaURLContextFactory;
//import org.apache.tomcat.jni.Time;
//import org.h2.util.IntArray;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.core.env.SystemEnvironmentPropertySource;
import com.fasterxml.jackson.core.JsonParseException;
import com.example.Clients;
import com.example.Rooms;


public class Book {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        String room, response;
        Boolean flag = true;
        Rooms.readHotelR();
        room = sc.next();
        Clients.readClients();
        System.out.println("Which client?");
        JSONArray customers = new JSONArray();
        do{
            customers.add(sc.next());
            System.out.println("Otro Cliente?");
            response = sc.next();
            if (response.equals("N")|| response.equals("n")) {
                flag = false;
            }
        } while (flag);
        bookRoom(Integer.parseInt(room), customers);
    }

    public static void bookRoom(int room, JSONArray customers){
        try {
            String jsonCustomersPath = "C:/Users/LIGHTDESA_14/Documents/ex/ejemplo/rooms.json";
            JSONParser parser = new JSONParser();
            JSONArray a = (JSONArray) parser.parse(new FileReader(jsonCustomersPath));
            //Read from file
            for (Object jo : a) {
                JSONObject customer = (JSONObject) jo;
                //JSONObject root = mapper.readValue(parser, JSONParser.class);
                //String val_older = (String) customer.get(key);
                Long indexID = (Long) customer.get("id");
                //Compare values
                if(room == Math.toIntExact(indexID))
                {
                    
                    a.remove(customer);
                    //Update value in object
                    customer.put("name", customer.get("name"));
                    customer.put("price", customer.get("price"));
                    customer.put("floor", customer.get("floor"));
                    customer.put("available", false);
                    customer.put("customer", customers);
                    customer.put("id", customer.get("id"));
                    customer.put("size", customer.get("size"));
                    customer.put("beds", customer.get("beds"));
                    //a.add(customer);
                    if (indexID > a.size()) {
                        a.add(customer);
                    } else {
                        a.add(Math.toIntExact(indexID), customer);
                    }
                    //System.out.println(a);
                    //Write into the file
                    try (FileWriter file = new FileWriter(jsonCustomersPath)) 
                    {
                        file.write(a.toJSONString());
                        System.out.println("Successfully updated json object to file...!!" + customer.toString());
                    }
                }
        }
            
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
        }
    }
}
