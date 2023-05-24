package controller;

import model.Sim;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * SIM Database Application Logic
 *
 * @version 1.0     - Initial Creation
 * @version 1.0.1   - Add, Edit, Remove, Similar Number Check, and List methods
 * @version 1.0.2   - Migrated to IntelliJ
 *
 */
public class SimController {
    private int numEntries;
    private Sim sim;
    private Map<String, Sim> simDatabase = null;
    final private String dataFile = "~/Desktop/info.data";

    public SimController() {
        simDatabase = new TreeMap<>();
        numEntries = 0;
    }

    public void addSim(Sim sim) {
        if (sim != null) {
            simDatabase.put(sim.getSimNumber(), sim);
            System.out.println("Sim Added");
        }
    }

    public boolean keyInUse(String key) {
        return simDatabase.containsKey(key);
    }

    public void editSim(String oldKey, Sim sim) {
        if (keyInUse(oldKey) && sim != null) {
            removeSim(oldKey);
            addSim(sim);
            System.out.println(sim.getSimName() + "has some information changed.");
        }
    }

    public void removeSim(String key) {
        if (!keyInUse(key)) {
            System.out.println("No logic.SIM number with " + key + " is known in the DB");
        } else {
            Sim targetSim = simDatabase.get(key);
            String tempName = targetSim.getSimName();
            String tempNum = targetSim.getSimNumber();
            simDatabase.remove(targetSim.getSimName());
            simDatabase.remove(targetSim.getSimNumber());
            simDatabase.remove(key);
            numEntries--;
            System.out.println(tempName + "logic.SIM with the number" + tempNum + " has been deleted");
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

    @SuppressWarnings("unchecked")
    public Sim[] searchByKey(String keyPrefix) {
        LinkedList<Sim> matches = new LinkedList<>();
        if (keyPrefix != null) {
            // Find keys equal-to/greater-than prefix.
//            SortedMap tail = simDatabase.tailMap(keyPrefix);
//            Iterator<String> it = tail.keySet().iterator();
//            // Stop for mismatch
//            boolean endOfSearch = false;
//            while (!endOfSearch && it.hasNext()) {
//                String key = it.next();
//                try {
//                    if (key.startsWith(keyPrefix)) {
//                        matches.add(simDatabase.get(key));
//                    } else {
//                        endOfSearch = true;
//                    }
//                } catch (IllegalArgumentException e) {
//                    System.out.println();
//                }
//            }
        }
        Sim[] results = new Sim[matches.size()];
        matches.toArray(results);
        return results;
    }

    public void load() throws IOException {
//        BufferedReader reader = new BufferedReader(new FileReader(dataFile));
//        try {
//            StringBuilder sb = new StringBuilder();
//            String line = reader.readLine();
//            while(line != null){
//                sb.append(line);
//                sb.append(System.lineSeparator());
//                line = reader.readLine();
//            }
//            reader.close();
//        } catch (IOException ex) {
//            System.out.println("Error: Data File is not found.");
//        }
        Properties properties = new Properties();
        properties.load(new FileInputStream(dataFile));

        for(String key : properties.stringPropertyNames()){
//            simDatabase.put(key, properties.getProperty(key, dataFile)); //Check for incompatible
        }
    }

    public void save() throws IOException {
        try (FileWriter file = new FileWriter(dataFile)) {
            //        PrintWriter output = new PrintWriter(dataFile);
//        for (int i = 0; i < numEntries; i = i + 1) {
//            output.println(simDatabase<sim.getSimName(), sim>);
//        }
//        output.close();
//        file.close();
//        Map<String, logic.SIM> savedDB = new HashMap<>();
//        Properties properties = new Properties();
//
//        for(Map.Entry<String, logic.SIM> simEntry : savedDB.entrySet()){
//            properties.put(sim.getSimName(), sim);
//        }
//
//        properties.store(new FileOutputStream(dataFile), null);
            BufferedWriter bw = new BufferedWriter(file);

            for(Map.Entry<String, Sim> entry: simDatabase.entrySet()){
                System.out.println(
                        "Key: " + entry.getKey()
                                + "\nValue: " + entry.getValue() + "\n"
                );
                bw.write(entry.getKey() +"\t"+ entry.getValue());
                bw.flush(); //Flush Buffer and Changes to disk
            }
            bw.close();
            file.close();
        } catch (IOException ex){
            System.out.println("Database File not Found");
        }
    }

    public void print(){

    }

    public int getNumEntries() {
        return numEntries;
    }

    public Map<String, Sim> getDatabase() {
        return simDatabase;
    }
}
