package com.farmcast.data_generetor;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.*;

import io.github.cdimascio.dotenv.Dotenv;

import com.farmcast.ddata.*;

public class data_generator {
    public String get_data(double lat, double lon){
        
        Dotenv dotenv = Dotenv.load();
        String apikey = dotenv.get("APIKEY");

        String language = "hi";
        
        String url = String.format("http://api.openweathermap.org/data/2.5/forecast?lat=%s&lon=%s&lang=%s&appid=%s&units=metric",lat,lon,language,apikey);


        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(url))
        .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            Gson gson = new Gson();
            data data = gson.fromJson(response.body(), data.class);

            return gson.toJson(data);

        } catch (Exception e) {
            System.out.println("sending request failed " + e);
            return "failed";
        }
    }
}
