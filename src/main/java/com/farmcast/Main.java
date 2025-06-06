package com.farmcast;

import java.time.LocalTime;
import java.time.Duration;
import io.github.cdimascio.dotenv.Dotenv;
import java.sql.*;
import com.farmcast.data_generetor.data_generator;
import com.mysql.cj.jdbc.Driver;
import com.google.gson.*;
import com.farmcast.ddata.*;
import com.farmcast.messenger.messenger;

public class Main {
    public static void main(String[] args) {
        boolean done = false;
        LocalTime routine_time = LocalTime.of(5,0);

        while(true){
            if(!done && ( (int) Duration.between(routine_time, LocalTime.now()).toMinutes() > 0)){
                System.err.println("done task");
                done = true;
            }
            if(done && ((int) Duration.between(LocalTime.of(0,0), LocalTime.now()).toMinutes() > 0) && ((int) Duration.between(routine_time, LocalTime.now()).toMinutes() < 0)){
                done = false;
            }
        }
    }

    public void routine(){
        System.out.println("routine stated.........................................");

        String query = "Update locations set weather_json=? where latitude=? and longitude=?";
        Dotenv dotenv = Dotenv.load();

        String url = dotenv.get("url");
        String user = dotenv.get("user");
        String password = dotenv.get("password");
        

        try{
            DriverManager.registerDriver(new Driver());
        
            Connection con = DriverManager.getConnection(url, user, password);

            Statement sat = con.createStatement();
            PreparedStatement psat = con.prepareStatement(query);

            ResultSet result = sat.executeQuery("Select latitude, longitude from locations");

            while(result.next()){
                data_generator data_generator = new data_generator();
                double lat = result.getDouble("latitude");
                double lon = result.getDouble("longitude");

                psat.setString(1,data_generator.get_data(lat, lon));
                psat.setDouble(2,lat);
                psat.setDouble(3,lon);

                psat.addBatch();
            }

            psat.executeBatch();
            psat.close();

            ResultSet result2 = sat.executeQuery("Select weather_json, phone_number from locations join people where locations.address = people.address");
            Gson gson = new Gson();

            while(result2.next()){
                String phone = result2.getString("phone_number");
                
                System.out.println("sending message to "+phone);

                data data = gson.fromJson(result2.getString("weather_json"),data.class);

                messenger messenger = new messenger();
                messenger.send(phone, "send");
            }

        }catch(SQLException e){
            e.printStackTrace();
        }


        System.out.println("rountine ended .......................................................");
    }
}