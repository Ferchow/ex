package com.example;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Rooms {
    public static void main(String[] args) {
        delRoom();
    }

    public static void readHotelR(){
        String path = "C:/Users/LIGHTDESA_14/Documents/ex/ejemplo/rooms.json";
        try {
            JSONParser parser = new JSONParser();
            JSONArray a = (JSONArray) parser.parse(new FileReader(path));
            for (Object o : a ){
                JSONObject room = (JSONObject) o;
                String name = (String) room.get("name");
                double price = (double) room.get("price");
                Long floor = (Long) room.get("floor");
                boolean availability = (boolean) room.get("available");
                JSONArray Customer = (JSONArray) room.get("customer"); 
                Long idRoom = (Long) room.get("id");
                Long sizeR = (Long) room.get("size");
                Long beds = (Long) room.get("beds");

                System.out.println(name + " $" + price);
                System.out.println(idRoom + " piso" + floor);
                System.out.println(Customer + " para" + sizeR + "personas y " + beds + "camas");
                System.out.println(availability);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //Object obj = parser.parse(new FileReader("C:/Users/LIGHTDESA_14/Documents/ex/ejemplo/customers.json"));
    };

    public static void editHotelR()
    {
        try {
            //ObjectMapper mapper = new ObjectMapper();
            String name = "LoftRoom";
            double price = 1500;
            int floor = 10;
            boolean availability = true;
            JSONArray clients = new JSONArray();
            clients.add(1);
            int idRoom = 3;
            int sizeR = 2;
            int beds = 1; //whatever
            int idCstmr = 2;
            String jsonCustomersPath = "C:/Users/LIGHTDESA_14/Documents/ex/ejemplo/rooms.json";
            JSONParser parser = new JSONParser();
            JSONArray a = (JSONArray) parser.parse(new FileReader(jsonCustomersPath));
            //System.out.println(a);
            //Read from file
            for (Object jo : a) {
                JSONObject customer = (JSONObject) jo;
                //JSONObject root = mapper.readValue(parser, JSONParser.class);
                //String val_older = (String) customer.get(key);
                Long indexID = (Long) customer.get("id");
                //Compare values
                if(idCstmr == Math.toIntExact(indexID))
                {
                    a.remove(customer);
                    //Update value in object
                    customer.put("name", name);
                    customer.put("price", price);
                    customer.put("floor", floor);
                    customer.put("available", availability);
                    customer.put("customer", clients);
                    customer.put("id", idRoom);
                    customer.put("size", sizeR);
                    customer.put("beds", beds);
                    //a.add(customer);
                    a.add(Math.toIntExact(indexID), customer);
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

    public static void newRoom()
    {
        //ejemplo
        String name = "FamilyRoom";
        double price = 1500;
        int floor = 5;
        boolean availability = true;
        JSONArray clients = new JSONArray();
        clients.add(0);
        int sizeR = 5;
        int beds = 3; //whatever
        
        try {
            String jsonCustomersPath = "C:/Users/LIGHTDESA_14/Documents/ex/ejemplo/rooms.json";
            JSONParser parser = new JSONParser();
            JSONArray a = (JSONArray) parser.parse(new FileReader(jsonCustomersPath));
            JSONObject customer = new JSONObject();
            customer.put("name", name);
            customer.put("price", price);
            customer.put("floor", floor);
            customer.put("available", availability);
            customer.put("customer", clients);
            customer.put("id", a.size()+1);
            customer.put("size", sizeR);
            customer.put("beds", beds);

            a.add(customer);
            try (FileWriter file = new FileWriter(jsonCustomersPath))
                {
                    file.write(a.toJSONString());
                    System.out.println("Successfully updated json object to file...!!" + customer.toString());
                }

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
        }
    }

    public static void delRoom()
    {
        int idClient = 5;
        try {
            String jsonCustomersPath = "C:/Users/LIGHTDESA_14/Documents/ex/ejemplo/rooms.json";
            JSONParser parser = new JSONParser();
            JSONArray a = (JSONArray) parser.parse(new FileReader(jsonCustomersPath));

            if(idClient > a.size()){
                System.out.println("no se encuentra el cuarto " + idClient);
            }
            else{
                a.remove(idClient);
                try (FileWriter file = new FileWriter(jsonCustomersPath)) 
                {
                    file.write(a.toJSONString());
                    System.out.println("Successfully updated json object to file...!!");
                }
            }
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
