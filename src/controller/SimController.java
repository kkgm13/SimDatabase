package controller;

import model.Sim;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * SIM Database Application Logic
 *
 * @version 1.0     - Initial Creation
 * @version 1.0.1   - Add, Edit, Remove, Similar Number Check, and List methods
 * @version 1.0.2   - Migrated to IntelliJ
 *
 */
public class SimController {
    private Sim sim;            //Sim Model
    //Data Structure
    private Map<String, Sim> simDatabase; // Preferred
    // Database
    final private String dataFile = "~/Desktop/info.data";

    public SimController() {
        simDatabase = new HashMap<>();
    }

    /**
     * Add the Sim to database
     * @param sim Newly made Sim
     */
    public void addSim(Sim sim) {
        if (sim != null) {
            simDatabase.put(sim.getSimNumber(), sim);
            System.out.println("Sim Added");
        }
    }

    /**
     * Get the Selected Sim
     *
     * @param phoneNumber Key Phone Number
     * @return Sim Object Value
     */
    public Sim getSim(String phoneNumber){
        return simDatabase.get(phoneNumber);
    }

    /**
     * Edit Sim
     *
     * @param oldKey    Old SIM number Identifier
     * @param sim       Updated Sim
     */
    public void editSim(String oldKey, Sim sim) {
        if (keyInUse(oldKey) && sim != null) {
            removeSim(oldKey);
            addSim(sim);
            System.out.println(sim.getSimName() + "has some information changed.");
        } else {
            System.out.println(oldKey+" is not found");
        }
    }

    /**
     * Update SIM???
     *
     * @param phoneNumber
     * @param newCarrier
     * @param newOwnerName
     */
    public void updateSim(String phoneNumber, String newCarrier, String newOwnerName) {
        Sim sim = simDatabase.get(phoneNumber);
        if (sim != null) {
            sim.setSimCarrier(newCarrier);
            sim.setSimName(newOwnerName);
        }
    }

    /**
     * Remove Sim from list
     * @param phoneNumber   Sim Number
     */
    public void removeSim(String phoneNumber) {
        if (!keyInUse(phoneNumber)) {
            System.out.println("No SIM number with " + phoneNumber + " is known in the DB");
        } else {
            String tempName = simDatabase.get(phoneNumber).getSimName();
            String tempNum = simDatabase.get(phoneNumber).getSimNumber();
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
     * @return Matching Sim
     */
    public List<Sim> searchSimByName(String name) {
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
     * @return Matching Sim
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
     * Search for specified SIM by number
     * @param phoneNumbers List of numbers
     * @return Matched List
     */
    public List<Sim> searchSIMByNumber(List<String> phoneNumbers) {
        List<Sim> matchingSIMCards = new ArrayList<>();

        for (String phoneNumber : phoneNumbers) {
            Sim simCard = simDatabase.get(phoneNumber);
            if (simCard != null) {
                matchingSIMCards.add(simCard);
            }
        }

        return matchingSIMCards;
    }

    /**
     * Find if Current Key in yse
     *
     * @param key Selected SIM number
     * @return Sim Number Key information
     */
    private boolean keyInUse(String key) {
        return simDatabase.containsKey(key);
    }

    /**
     * Activate a SIM
     *
     * @param phoneNumber Phone Number Key
     */
    public void activateSIM(String phoneNumber) {
        Sim simCard = simDatabase.get(phoneNumber);
        if (simCard != null) {
            simCard.activate();
        }
    }

    /**
     * Deactivate a SIM
     *
     * @param phoneNumber Phone Number Key
     */
    public void deactivateSIM(String phoneNumber) {
        Sim simCard = simDatabase.get(phoneNumber);
        if (simCard != null) {
            simCard.deactivate();
        }
    }

    /**
     * Save Database to File
     */
    public void save() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(dataFile))) {
            outputStream.writeObject(simDatabase);
            System.out.println("Data saved to file: " + dataFile);
        } catch (IOException e) {
            System.err.println("Error saving data to file: " + e.getMessage());
        }
    }

    /**
     * Load Database from File
     */
    public void load() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(dataFile))) {
            simDatabase = (Map<String, Sim>) inputStream.readObject();
            System.out.println("Data loaded from file: " + dataFile);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading data from file: " + e.getMessage());
        }
    }
}
