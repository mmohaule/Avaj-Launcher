package com.mmohaule.simulator.aircraft;

import java.util.HashMap;
import com.mmohaule.simulator.interfaces.Flyable;
import com.mmohaule.simulator.tower.WeatherTower;

public class Baloon extends Aircraft implements Flyable {
    
    private WeatherTower weatherTower = new WeatherTower();

    Baloon(String name, Coordinates coordinates) {
        super(name, coordinates);
        registerTower(weatherTower);
    }

    public void updateConditions() {

        HashMap<String, String> reactions = new HashMap<String, String>();
    
        reactions.put("SUN", "Let's enjoy the good weather and take some pics.");
        reactions.put("RAIN", "Damn you rain! You messed up my balloon.");
        reactions.put("FOG", "Shit, I cant see a thing");
        reactions.put("SNOW", "It's snowing. We're gonna crash.");

        String weather = weatherTower.getWeather(this.coordinates);
        weatherTower.createPrintList("Baloon#"+ name + "(" + id + "): " + reactions.get(weather));

        if (weather == "SUN") {
           coordinates.setHeight(coordinates.getHeight() + 4);
           coordinates.setLongitude(coordinates.getLongitude() + 2);
        }
        else if (weather == "RAIN") {
            coordinates.setHeight(coordinates.getHeight() - 5);
        }
        else if (weather == "FOG") {
            coordinates.setHeight(coordinates.getHeight() - 3);
        }
        else if (weather == "SNOW") {
            coordinates.setHeight(coordinates.getHeight() - 15);
        }
        if (coordinates.getHeight() == 0) {
            weatherTower.createPrintList("Baloon#"+ name + "(" + id + "): " + "landing");
            weatherTower.unregister(this);
            weatherTower.createPrintList("Tower says: " +"Baloon#"+ name + "(" + id + ") unregistered from weather tower.");
        }
    }

    public void registerTower(WeatherTower weatherTower) {
        weatherTower.register(this);
        weatherTower.createPrintList("Tower says: " +"Baloon#"+ name + "(" + id + ") registered to weather tower.");
    }
}