package com.mmohaule.simulator.weather;

import java.util.Random;
import com.mmohaule.simulator.aircraft.Coordinates;

public class WeatherProvider {

    private static WeatherProvider weatherProvider = new WeatherProvider();
    private static String[] weather = {"SUN", "RAIN", "FOG", "SNOW"};
    Random number = new Random();
    
    private WeatherProvider() {

    }

    public static WeatherProvider getProvider() {
        return weatherProvider;
    }

    public String getCurrentWeather(Coordinates coordinates) {
        int value = coordinates.getLongitude() + coordinates.getLatitude() + coordinates.getHeight() + number.nextInt(100);
        return (weather[value % 4]);
    }
}