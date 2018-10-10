package com.mmohaule.simulator.aircraft;

public abstract class Aircraft {

    protected long id;
    protected String name;
    protected Coordinates coordinates;
    private static long idCounter = 1;

    protected Aircraft(String name, Coordinates coordinates) {
        this.id = idCounter;
        idCounter = nextID();
        this.name = name;
        this.coordinates = coordinates;
    }

    private long nextID() {
        return ++idCounter;
    }
}