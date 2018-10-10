package com.mmohaule.simulator.syntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Syntax {
    
    private String filename;

    private ArrayList<String> fileContent = new ArrayList<String>();
    private String simulationLoop;
    private String type;
    private String name;
    private String longitude;
    private String latitude;
    private String height;
    private ArrayList<Map<String, String>> fileMapped = new ArrayList<Map<String, String>>();
    private Map<String, String> lineMapped = new HashMap<String, String>();

    String currentLine;
    int isFirstline = 1;
    String wordType = "";
    String wordName = "";
    String wordOne = "";
    String wordTwo = "";
    String wordThree = "";
    String[] words;
    Boolean notCorrect = false;
    int error = 0;
        
    public Syntax(String filename) {
        this.filename = filename;
        CheckEmptyFile();
    }
        
    private void readFileIntoString() {
        try (BufferedReader bufferReader = new BufferedReader(new FileReader(this.filename)))
        {
            String currentLine;
    
            while ((currentLine = bufferReader.readLine()) != null) {
                currentLine = currentLine.trim();
                this.fileContent.add(currentLine);
            }
            validSyntax();
        } catch (IOException exception) {
            System.out.println("Error: Could not read file content.\nClosing program.");
            System.exit(0);
        } 
    }

    private void validSyntax() {
        for (int lineNumber = 0; lineNumber < this.fileContent.size(); lineNumber++) {
            currentLine = this.fileContent.get(lineNumber); 
            while ((currentLine.equals("")) || (currentLine.charAt(0) == '#')) {
                lineNumber++;
                try {
                    currentLine = this.fileContent.get(lineNumber);
                } catch (Exception exception) {
                    System.out.println("Error: Error checking line content.\nClosing program.");
                    System.exit(0);
                }
            }

            currentLine = currentLine.replaceAll("\\s+", " ");
              
            if (isFirstline == 1) {
                isFirstline = 0;
                try {
                    if (currentLine.matches("^[0-9]*$")) {
                        int tempLoop = Integer.parseInt(currentLine.replaceAll("\\s+",""));
                       
                        if (tempLoop < 2147483647) {
                            simulationLoop = currentLine;
                        }
                    } else {
                        notCorrect = true;
                        simulationLoop = "";
                    }
                } catch (Exception exception) {
                    System.out.println("Error: Loop counter larger than integer maximum, setting counter to maximum - 1.\n");
                    this.simulationLoop = Integer.toString(2147483646);
                    currentLine = Integer.toString(2147483646);
                }
            } else {
                words = currentLine.split("\\s");
                if (words.length > 5) {
                    System.out.println("Syntax Notification: Current read line has too many values.\nSkipping...\n");
                    notCorrect = true;
                } else if (words.length < 5) {
                    System.out.println("Syntax Notification: Current read line has too few values.\nSkipping...\n");
                    notCorrect = true;
                } else {
                    words = currentLine.split("\\s");
                    wordType = words[0];
                    wordName = words[1];
                    wordOne = words[2];
                    wordTwo = words[3];
                    wordThree = words[4];

                    this.type = wordType;
                    this.name = wordName;

                    try {
                        if (wordOne.matches("^[0-9]*$")) {
                            int tempLong = Integer.parseInt(wordOne);
                            if (tempLong >= 0 && tempLong < 2147483647) {
                                this.longitude = wordOne;
                            }
                        } else {
                            System.out.println("Error: Incorrect longitude value for aircraft "+ type +" "+ name +".\nClosing program...\n");
                            System.exit(0);
                        }
                    } catch (Exception exception) {
                        
                    } try {
                        if (wordTwo.matches("^[0-9]*$")) {
                            int tempLat = Integer.parseInt(wordTwo);
                            if (tempLat >= 0 && tempLat < 2147483647) {
                                this.latitude = wordTwo;
                            }
                        } else {
                            System.out.println("Error: Incorrect latitude value for aircraft "+ type +" "+ name +".\nClosing program...\n");
                            System.exit(0);
                        }
                    } catch (Exception exception) {
                      
                        
                    } try {
                        if (wordThree.matches("^[0-9]*$")) {
                            int tempHeight = Integer.parseInt(wordThree);
                            if (tempHeight >= 0) {
                                if (tempHeight > 100) {
                                    this.height = "100";
                                } else {
                                    this.height = wordThree;
                                }
                            } 
                        } else {
                            System.out.println("Error: Incorrect height value for aircraft "+ type +" "+ name +".\nClosing program...\n");
                            System.exit(0);
                        }
                    } catch (Exception exception) {
                        
                    }
                }
            }
    
            try {
                if ((this.simulationLoop != null && this.simulationLoop != "") && (this.type != null && this.type != "") && (this.name != null && this.name != "") 
                && (this.longitude != null && this.longitude != "") && (this.latitude != null && this.latitude != "") && (this.height != null && this.height != "") && notCorrect == false) {

                    String hash = this.type + "-" + this.name + "-" + this.longitude + "-" + this.latitude + "-" + this.height + "-" + this.simulationLoop ;
                    this.lineMapped.put(Integer.toString(lineNumber) + "_", hash);
                    this.fileMapped.add(this.lineMapped);
                    lineMapped = new HashMap<String, String>();
                }
            } catch (Exception ex) {
                System.out.println("Error: Error creating list.\nClosing program...");
                System.exit(0);
            }
            flushVariables();
        }
    }

    public void flushVariables() {
        this.type = "";
        this.name = "";
        this.longitude = "";
        this.latitude = "";
        this.height = "";

        wordType = "";
        wordName = "";
        wordOne = "";
        wordTwo = "";
        wordThree = "";
        notCorrect = false;
    }

    public ArrayList<Map<String, String>> getFileContent() {
        if (this.fileMapped.size() > 0) {
            return (this.fileMapped);
        } else {
            System.out.println("Error: List returned empty.\nClosing program...\n");
            System.exit(0);
        }
        return null;
    }
    
    public String getSimulationLoopCount() {
        return (this.simulationLoop);
    }

    public void CheckEmptyFile() {
        File file = new File(this.filename);
        try {
            if (file.exists()) {
                try {
                    if (file.length() == 0) {
                        System.out.println("Error: File is empty.\nClosing program...\n");
                        System.exit(0);
                    } else {
                        readFileIntoString();
                    }
                } catch (Exception e) { }
            } else {
                System.out.println("Error: File does not exist.\nClosing program...\n");
                System.exit(0);
            }
        } catch (Exception e) { }
    }
}
