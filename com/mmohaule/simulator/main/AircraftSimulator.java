package com.mmohaule.simulator.main;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.*;
import java.io.IOException;

import com.mmohaule.simulator.aircraft.AircraftFactory;
import com.mmohaule.simulator.interfaces.Flyable;
import com.mmohaule.simulator.syntax.Syntax;
import com.mmohaule.simulator.tower.Tower;
import com.mmohaule.simulator.tower.WeatherTower;

public class AircraftSimulator {
    
    public static void main(String args[]){
        if (args.length > 1) {
            System.out.println("Too many arguments, try again...\n");
        } else if (args.length < 1) {
            System.out.println("Too few arguments, try again...\n");
        } else if (args.length == 1) {
            Syntax syntax = new Syntax(args[0]);
            runProgram(syntax.getFileContent());
        }
    }

    public static void runProgram(ArrayList<Map<String, String>> new_list) {
        
        int simulationLoopCount = 0;
        AircraftFactory factory = new AircraftFactory();
        WeatherTower weatherTower = new WeatherTower();
        Flyable flyable = null;
        
        for (Map<String, String> coordinateHashes : new_list) {
        
            Iterator<Map.Entry<String, String>> coordinateHashesEntries = coordinateHashes.entrySet().iterator();
        
            while (coordinateHashesEntries.hasNext()) {
                String[] splittedHashes;
        
                Map.Entry<String, String> coordinateHashesEntry = coordinateHashesEntries.next();
        
                splittedHashes  = coordinateHashesEntry.getValue().split("-");
        
                String type = splittedHashes[0];
                String name = splittedHashes[1];
                Integer latitude = Integer.parseInt(splittedHashes[2]);
                Integer longitude = Integer.parseInt(splittedHashes[3]);
                Integer height = Integer.parseInt(splittedHashes[4]);
                simulationLoopCount = Integer.parseInt(splittedHashes[5]);
        
                    if (AircraftFactory.newAircraft(type, name, longitude, latitude, height) == null) {
                        System.out.println("Error: No aircraft of type " + type + ".\nClosing program... Bye!");
                        System.exit(0);
                    }
            }
        }
        weatherTower.runSimulation(simulationLoopCount);
    }
}