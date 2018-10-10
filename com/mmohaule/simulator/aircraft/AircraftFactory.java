package com.mmohaule.simulator.aircraft;

import com.mmohaule.simulator.aircraft.Baloon;
import com.mmohaule.simulator.aircraft.Helicopter;
import com.mmohaule.simulator.aircraft.JetPlane;
import com.mmohaule.simulator.interfaces.Flyable;

public class AircraftFactory {

    public static Flyable newAircraft(String type, String name, int longitude, int latitude, int height) {
        Coordinates coordinates = new Coordinates(longitude, latitude, height);

        if (type.equalsIgnoreCase("Helicopter")) {
            return new Helicopter(name, coordinates);
        } else if (type.equalsIgnoreCase("Baloon")) {
            return new Baloon(name, coordinates);
        } else if (type.equalsIgnoreCase("JetPlane")) {
            return new JetPlane(name, coordinates);
        } else {
            return null;
        }
    }
}