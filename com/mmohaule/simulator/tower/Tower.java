package com.mmohaule.simulator.tower;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import com.mmohaule.simulator.interfaces.Flyable;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.PrintWriter;

public abstract class Tower {
    private static List<Flyable> observers = new CopyOnWriteArrayList<Flyable>();
    private static ArrayList<String> list = new ArrayList<String>();
    private File file;
    private FileWriter writer;

    public void register(Flyable flyable) {
        try {
            observers.add(flyable);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void unregister(Flyable flyable) {
        try {
            observers.remove(flyable);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void createPrintList(String line) {
        try {
            list.add(line);
        } catch (Exception e) {

        }
    }

    protected void conditionsChanged() {
        for (Flyable observer: observers) {
            observer.updateConditions();
        }
    }

    public void writeToFile() {
        list.toArray().toString();
        try {
            this.file = new File("simulation.txt");
            this.writer = new FileWriter(file);
            this.file.createNewFile();
            for(int i = 0; i < list.size(); i++) {
                writer.write(list.get(i) + "\n");
                writer.flush();
            }
        } catch (IOException e) {
            System.out.println("Couldn't create or write to file.\nClosing program...");
            System.exit(1);
        } finally {
            try {
                writer.close();
            } catch (Exception e) {
                System.out.println("Couldn't close file.\nClosing program...");
                System.exit(1);
            } 
        }
    }
}