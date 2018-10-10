package com.mmohaule.simulator.interfaces;

import com.mmohaule.simulator.tower.WeatherTower;

public interface Flyable {

    public void updateConditions();
    public void registerTower(WeatherTower weatherTower);
}