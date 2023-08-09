package controller;

import model.Sim;
import view.CLI;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * SIM Database Application Logic
 *
 * @version 1.0     - Initial Creation
 * @version 1.0.1   - Add, Edit, Remove, Similar Number Check, and List methods
 * @version 1.0.2   - Migrated to IntelliJ
 * @version 1.0.3   - Revamped the Entire Controller
 *
 */
public class SimController {
    private final Map<String, Sim> simDatabase; //Data Structure
    // Database Path and FileName
    final private String dataPath = System.getProperty("user.home")+"/Desktop/";
    final private String dataFile = "info.data";
    public SimController() {
        simDatabase = new HashMap<>();
    }

    /**
     * Add the Sim to database
     * @param sim Newly Created Sim
     */
    public void addSIM(Sim sim) {
        if(sim != null && numberInUse(sim.getSimNumber())){
            System.out.println("SIM number is known in the database already");
        } else if (sim != null && !numberInUse(sim.getSimNumber())) {
            simDatabase.put(sim.getSimNumber(), sim);
            System.out.println("SIM Added");
        } else {
            System.out.println("Error in Saving SIM");
        }
    }

    /**
     * Get the Selected Sim
     *
     * @param phoneNumber Key Phone Number
     * @return Sim Object Value
     */
    public Sim getSIM(String phoneNumber){
        return simDatabase.get(phoneNumber);
    }

    /**
     * Edit Sim
     *
     * @param oldNumber     Old SIM number Identifier
     * @param sim           Updated Sim
     */
    public void editSIM(String oldNumber, Sim sim) {
        if (!numberInUse(oldNumber) && sim != null) {
            removeSIM(oldNumber);
            addSIM(sim);
            System.out.println(sim.getSimName() + "has some information changed.");
        } else {
            System.out.println(oldNumber+" is not found");
        }
    }

    /**
     * Remove Sim from list
     * @param phoneNumber   Sim Number
     */
    public void removeSIM(String phoneNumber) {
        if (!numberInUse(phoneNumber)) {
            System.out.println("No SIM number with " + phoneNumber + " is known in the DB");
        } else {
            String tempName = getSIM(phoneNumber).getSimName();
            String tempNum = getSIM(phoneNumber).getSimNumber();
            simDatabase.remove(phoneNumber);
            System.out.println(tempName + "SIM with the number" + tempNum + " has been deleted.");
        }
    }

    /**
     * Get Sim Database
     *
     * @return Database
     */
    public Map<String, Sim> getAllSIMs() {
        return simDatabase;
    }

    /**
     * Search for specified SIM by Name
     *
     * @param name Provided Name
     * @return Matching SIMs
     */
    public List<Sim> searchSIMByName(String name) {
        List<Sim> matchingSIMCards = new ArrayList<>();
        for (Sim sim : simDatabase.values()) {
            if (sim.getSimName().equalsIgnoreCase(name)) {
                matchingSIMCards.add(sim);
            }
        }
        return matchingSIMCards;
    }

    /**
     * Search for specified SIM by Provider
     * @param carrier   Sim Carrier Name
     * @return Matching SIMs
     */
    public List<Sim> getSIMCardsByCarrier(String carrier) {
        List<Sim> matchingSIMCards = new ArrayList<>();
        for (Sim sim : simDatabase.values()) {
            if (sim.getSimCarrier().equalsIgnoreCase(carrier)) {
                matchingSIMCards.add(sim);
            }
        }
        return matchingSIMCards;
    }

    /**
     * Search for Specific SIM by Phone Number
     * @param partialNumber SIM Number
     * @return  Matching SIMS
     */
    public List<Sim> searchSIMByNumber(String partialNumber) {
        List<Sim> matchingSIMCards = new ArrayList<>();
        for (Sim sim : simDatabase.values()) {
            if (sim.getSimNumber().contains(partialNumber)) {
                matchingSIMCards.add(sim);
            }
        }
        return matchingSIMCards;
    }

    /**
     * Find if Current Number in the database
     *
     * @param key   Selected SIM number
     * @return Boolean if SIM Exists in Database
     */
    private boolean numberInUse(String key) {
        return simDatabase.containsKey(key);
    }

    /**
     * Activate a SIM
     *
     * @param phoneNumber Phone Number Key
     */
    public void activateSIM(String phoneNumber) {
        Sim simCard = simDatabase.get(phoneNumber);
        if(simCard != null && simCard.isActive()){
            System.out.println(phoneNumber+" is already deactivated.");
        } else if (simCard != null) {
            simCard.activate();
            System.out.println(phoneNumber+" is now activated.");
        } else {
            System.out.println("Number is not registered");
        }
    }

    /**
     * Deactivate a SIM
     *
     * @param phoneNumber Phone Number Key
     */
    public void deactivateSIM(String phoneNumber) {
        Sim simCard = simDatabase.get(phoneNumber);
        if(simCard != null && !simCard.isActive()){
            System.out.println(phoneNumber+" is already deactivated.");
        } else if (simCard != null) {
            simCard.deactivate();
            System.out.println(phoneNumber+" is now deactivated.");
        } else {
            System.out.println("Number is not registered");
        }
    }

    /**
     * Save Database to File
     */
    public void saveToFile() {
        try {
            Path filePath = Path.of(dataPath, dataFile);    //Path File
            if (!Files.exists(filePath.getParent())) {      // Validate if file exists or not
                Files.createFile(filePath);                 // Create file if none
            }
            // Start File writer and buffer writer
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath.toFile()))) {
                for (Sim sim : simDatabase.values()) {
                    writer.write(sim.write2DB());   // Write the format to file
                    writer.newLine();               // Create new line
                }
                System.out.println("SIM database saved to " + filePath);
            } catch (IOException e) {
                System.out.println("Error writing SIM database to file: " + e.getMessage());
            }
        } catch (IOException e) {
            System.out.println("Error creating file: " + e.getMessage());
        }
    }

    /**
     * Load Database from File
     */
    public void loadFromFile() {
        Path filePath = Path.of(dataPath, dataFile);    // Path File
        if (!Files.exists(filePath)) {                  // Does the file exist?
            System.out.println("File not found: " + filePath);
            return;
        }
        // Start Reader to get from file
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath.toFile()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("#");
                boolean isRoaming = Boolean.parseBoolean(parts[7]);
                boolean isActive = Boolean.parseBoolean(parts[9]);
                String notes = null;
                if (!parts[10].isBlank()) {
                    notes = parts[10];
                }
                Sim sim = new Sim(parts[0],parts[1],parts[2],parts[3], parts[4],parts[5],Double.parseDouble(parts[6]),isRoaming,parts[8],isActive, notes, LocalDateTime.parse(parts[11]));
                simDatabase.put(sim.getSimNumber(), sim);
            }
            System.out.println("SIM database loaded from " + filePath);
        } catch (IOException e) {
            System.out.println("Error reading SIM data file from : " + e.getMessage());
        }
    }
}
