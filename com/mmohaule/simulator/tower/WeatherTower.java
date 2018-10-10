package com.mmohaule.simulator.tower;

import com.mmohaule.simulator.aircraft.Coordinates;
import com.mmohaule.simulator.tower.Tower;
import com.mmohaule.simulator.weather.WeatherProvider;

public class WeatherTower extends Tower{

    public String getWeather(Coordinates coordinates) {
        return WeatherProvider.getProvider().getCurrentWeather(coordinates);
    }

    void changeWeather() {
        conditionsChanged();
    }

    public void runSimulation(int count) {
        while (count-- > 0) {
            changeWeather();
        }
        
        writeToFile();
    }
}