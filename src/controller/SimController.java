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
    // Entires Counter
    private int numEntries;
    //Sim Model
    private Sim sim;
    //Data Structure
//    private List<Sim> simDatabase = null;
    private Map<String, Sim> simDatabase = null; // Preferred
    // Database
    final private String dataFile = "~/Desktop/info.data";

    public SimController() {
        simDatabase = new HashMap<>();
//        simDatabase = new ArrayList<>();
        numEntries = 0;
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
     * Find if Current Key in yse
     *
     * @param key Selected SIM number
     * @return Sim Number Key information
     */
    public boolean keyInUse(String key) {
        return simDatabase.containsKey(key);
    }

    /**
     *
     */
    public Sim findbySimName(Sim sim){
        return null;
    }

    /**
     *
     */
    public Sim findbySimNumber(Sim sim){
        return null;
    }

    /**
     * Edit Sim
     * @param oldKey    Old SIM number Identifier
     * @param sim       Updated Sim
     */
    public void editSim(String oldKey, Sim sim) {
        if (keyInUse(oldKey) && sim != null) {
            removeSim(oldKey);
            addSim(sim);
            System.out.println(sim.getSimName() + "has some information changed.");
        }
    }

    /**
     * Remove Sim from list
     * @param key   Sim Number Identifier
     */
    public void removeSim(String key) {
        if (!keyInUse(key)) {
            System.out.println("No SIM number with " + key + " is known in the DB");
        } else {
            Sim targetSim = simDatabase.get(key);
            String tempName = targetSim.getSimName();
            String tempNum = targetSim.getSimNumber();
            simDatabase.remove(targetSim.getSimName());
            simDatabase.remove(targetSim.getSimNumber());
            simDatabase.remove(key);
            numEntries--;
            System.out.println(tempName + "SIM with the number" + tempNum + " has been deleted");
        }
    }

    public String listSim() {
        StringBuilder allEntries = new StringBuilder();
        Set<Sim> sortedDetails = new TreeSet<>(simDatabase.values());
        Iterator<Sim> it = sortedDetails.iterator();
        while (it.hasNext()) {
            Sim sims = it.next();
            allEntries.append(sims);
            allEntries.append('\n');
        }
        return allEntries.toString();
    }

//    @SuppressWarnings("unchecked")
    public Sim[] searchByKey(String keyPrefix) {
        List<Sim> matches = new ArrayList<>();
        if (keyPrefix != null) {
            for(Sim s: simDatabase.values()){
                if(s.getSimNumber().equalsIgnoreCase(keyPrefix)){
                    matches.add(s);
                }
            }
        }
        Sim[] results = new Sim[matches.size()];
        matches.toArray(results);
        return results;
    }

    public void load() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(dataFile));
        String currentLine;
        try {
//            StringBuilder sb = new StringBuilder();
//            String line = reader.readLine();
//            while(line != null){
//                sb.append(line);
//                sb.append(System.lineSeparator());
//                line = reader.readLine();
//            }
//            reader.close();
            boolean first = false;
            String[] fields = new String[0];
            while((currentLine = reader.readLine()) !=null) {//for each line in txt file
                if(!first) {//if it is the first line the line is the fields and we save them into an array
                    fields = currentLine.split(",");
                    first = true;
                }
                else {//for the rest lines we print the information
                    System.out.println("-------------------");
                    String[] info=currentLine.split(",");
                    for (int i = 0; i < fields.length; i++ ) {
                        System.out.println(fields[i] +": "+ info[i]);
                    }
                }
            }
        } catch (IOException ex) {
            System.out.println("Error: Data File is not found.");
            System.out.println("Error Log: "+ex);
        }
        Properties properties = new Properties();
        properties.load(new FileInputStream(dataFile));

        for(String key : properties.stringPropertyNames()){
//            simDatabase.put(key, properties.getProperty(key, dataFile)); //Check for incompatible
        }
    }

    public void save() throws IOException {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(dataFile));
            for (Map.Entry<String, Sim> entry: simDatabase.entrySet()) {
                Sim sim = simDatabase.get(entry);
                bw.write(sim.write2DB());
                bw.newLine();
            }
            bw.close();
        } catch (IOException ex){
            System.out.println("Database File not Found");
        }
    }


    /**
     * Get Number of entries avaliable
     *
     * @return Number of entries
     */
    public int getNumEntries() {
        return numEntries;
    }

    /**
     * Get Database
     *
     * @return Database
     */
    public Map<String, Sim> getDatabase() {
        return simDatabase;
    }
}
