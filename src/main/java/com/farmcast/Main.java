package com.farmcast;

import java.time.LocalTime;
import java.time.Duration;
import io.github.cdimascio.dotenv.Dotenv;
import java.sql.*;

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
        System.out.println("done");

        Dotenv dotenv = Dotenv.load();

        String url = dotenv.get("url");
        String user = dotenv.get("user");
        String password = dotenv.get("password");

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(ClassNotFoundException e){
            System.out.println(e);
        }

        try{
            Connection con = DriverManager.getConnection(url, user, password);
        }catch(SQLException e){
            System.out.println(e);
        }
    }
}