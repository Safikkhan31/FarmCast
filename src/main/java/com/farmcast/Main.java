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
import com.farmcast.weather_message.weather_message;

public class Main {
    public static void main(String[] args) {
        boolean done = false;
        LocalTime routine_time = LocalTime.of(5,0);

        while(true){
            if(!done && ( (int) Duration.between(routine_time, LocalTime.now()).toMinutes() > 0)){
                routine();
                done = true;
            }
            if(done && ((int) Duration.between(LocalTime.of(0,0), LocalTime.now()).toMinutes() > 0) && ((int) Duration.between(routine_time, LocalTime.now()).toMinutes() < 0)){
                done = false;
            }
        }
    }

    public static void routine(){
        System.out.println(".......................................routine started.........................................");

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
            Gson gson = new Gson();

            while(result.next()){
                data_generator data_generator = new data_generator();
                double lat = result.getDouble("latitude");
                double lon = result.getDouble("longitude");

                String json_data = data_generator.get_data(lat, lon);

                psat.setString(1,json_data);
                psat.setDouble(2,lat);
                psat.setDouble(3,lon);

                psat.addBatch();

                data data = gson.fromJson(json_data, data.class);

                System.out.println("updated data for "+ lat + "  " + lon + " with code " + data.cod +"\n");
            }

            psat.executeBatch();
            psat.close();

            ResultSet result2 = sat.executeQuery("Select weather_json, phone_number from locations join people where locations.address = people.address");
            

            String message;
            double temp;
            double feels_like;
            String weatherDesc;
            double windSpeed;
            int humidity;

            while(result2.next()){
                String phone = result2.getString("phone_number");
                
                System.out.println("sending message to "+phone + "......");

                data data = gson.fromJson(result2.getString("weather_json"),data.class);

                if(data.cod.equals("200")){
                    temp = data.instance_data_list.get(0).main.temp;
                    feels_like = data.instance_data_list.get(0).main.feels_like;
                    weatherDesc = data.instance_data_list.get(0).weather.get(0).description;
                    windSpeed = data.instance_data_list.get(0).wind.speed;
                    humidity = data.instance_data_list.get(0).main.humidity;


                    message = weather_message.createWeatherMessage(temp, feels_like, weatherDesc, windSpeed, humidity);

                    messenger messenger = new messenger();
                    messenger.send(phone, message);
                }
                else{
                    System.out.println("failed to send message to " + phone);;
                }
                
            }

        }catch(SQLException e){
            e.printStackTrace();
        }


        System.out.println("........................................rountine ended ............................................\n");
    }
}