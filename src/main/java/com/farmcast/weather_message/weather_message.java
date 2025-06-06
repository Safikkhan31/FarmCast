package com.farmcast.weather_message;

public class weather_message {
    public static String createWeatherMessage(double temp, double feelsLike, String weatherDesc, double windSpeed, int humidity) {
        return String.format(
            "आज का तापमान %.1f°C है, जो महसूस होता है %.1f°C जैसा। मौसम '%s' है, हवा की रफ्तार %.1f किमी/घंटा है और आर्द्रता %d%% है।",
            temp, feelsLike, weatherDesc, windSpeed, humidity
        );
    }
}
