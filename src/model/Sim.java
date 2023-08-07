package model;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * SIM Class
 * This is the core information of a SIM
 *
 * @version 1.0     - Initial Creation
 * @version 1.0.1   - Add Roaming Parameter and in Use Parameter
 * @version 1.0.2   - Add SIM Size
 * @version 1.0.3   - Added Last Updated & Renamed isActive
 * @version 1.0.4   - Migrated to IntelliJ
 *
 */
public class Sim {
//    private UUID simID;                     // Unique Identifier
    private String simName;                 // User Defined Statement to define a SIM
    private String simNumber;               // Phone Number registered to this SIM
    private int simPIN;                     // SIM PIN code assigned to this SIM
    private String simCountry;              // Country of Origin of this SIM
    private String simProvider;             // Phone Company Provider of this SIM in the Country
    private String simType;                 // Type of SIM associated (Pay as You Go, Contract, etc...)
    private String simSize;                 // The size of the SIM (Standard Mini, Micro or Nano)
    private double simCredit;               // Amount of Credit in this SIM
    private boolean isRoaming;              // Whether if this SIM is currently active on Roaming
    private boolean isActive;               // Whether if this SIM is currently active and in use
    private String simNotes;                // User Defined Notes of the SIM
    private LocalDateTime lastUpdated;      // Last Updated Date Time

    /**
     * Existing SIM Constructor
     *  Get the selected SIM
     *
     * @param simName
     * @param simNumber
     * @param simPIN
     * @param simCountry
     * @param simProvider
     * @param simType
     * @param simCredit
     * @param isRoaming
     * @param isActive
     * @param simNotes
     * @param lastUpdated
     */
    public Sim(String simName, String simNumber, int simPIN, String simCountry,
               String simProvider, String simType, double simCredit, boolean isRoaming, boolean isActive, String simNotes, LocalDateTime lastUpdated) {
        this.simName = simName;
        this.simNumber = simNumber;
        this.simPIN = simPIN;
        this.simCountry = simCountry;
        this.simProvider = simProvider;
        this.simType = simType;
        this.simCredit = simCredit;
        this.isRoaming = false;
        this.isActive = isActive;
        this.simNotes = simNotes;
        this.lastUpdated = lastUpdated;
    }

    //Encapsulation (Getter and Setters)
//    public UUID getSimID(){return simID;}
    public String getSimName() {
        return simName;
    }
    public void setSimName(String simName) {
        this.simName = simName;
    }
    public String getSimNumber() {
        return simNumber;
    }
    public void setSimNumber(String simNumber) {
        this.simNumber = simNumber;
    }
    public int getSimPIN() {
        return simPIN;
    }
    public void setSimPIN(int simPIN) {
        this.simPIN = simPIN;
    }
    public String getSimCountry() {
        return simCountry;
    }
    public void setSimCountry(String simCountry) {
        this.simCountry = simCountry;
    }
    public String getSimProvider() {
        return simProvider;
    }
    public void setSimProvider(String simProvider) {
        this.simProvider = simProvider;
    }
    public String getSimType() {
        return simType;
    }
    public void setSimType(String simType) {
        this.simType = simType;
    }
    public double getSimCredit() {
        return simCredit;
    }
    public void setSimCredit(double simCredit) {
        this.simCredit = simCredit;
    }
    public boolean isRoaming() {
        return isRoaming;
    }
    public void setIsRoaming(boolean isRoaming) {
        this.isRoaming = isRoaming;
    }
    public boolean isActive(){
        return isActive;
    }
    public void setIsActive(boolean isActive){
        this.isActive = isActive;
    }
    public String getSimNotes() {
        return simNotes;
    }
    public void setSimNotes(String simNotes) {
        this.simNotes = simNotes;
    }
    public String getSimSize() {return simSize;}
    public void setSimSize(String simSize) {this.simSize = simSize;}
    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }
    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    // Extra Model Methods
    /**
     * ToString Method
     * @return SIM Data Format
     */
    @Override
    public String toString() {
        String s = "";
//        s += getSimID() + "\t";
        s += getSimName() + "\t" + getSimCountry() + "\t";
        // Roaming Checks
        if (isRoaming()) {
            s += "ON ROAM\t";
        }
        // Active Sim Check
        s += isActive() ? "IN USE \t": "";
        // Balance Check
        s += "(" + getSimCredit();
        if (getSimCredit() <= 10.00) {
            s += ": TopUp Required!)";
        } else if (getSimCredit() == 0.00) {
            s += ": TopUp IMMEDIATELY!)";
        } else {
            s += ")";
        }
        return s;
    }

    /**
     * Write to the Database
     * @return
     * @todo Serialize this ASAP
     */
    public String write2DB() {
        String s = "";
//        s += getSimID();
        s += "#" + getSimName();
        s += "#" + getSimNumber();
        s += "#" + getSimCountry();
        s += "#" + getSimProvider();
        s += "#" + getSimType();
        s += "#" + getSimPIN();
        s += "#" + getSimCredit();
        s += "#" + isRoaming();
        s += "#" + getSimSize();
        s += "#" + isActive();
        s += "#" + getSimNotes();
        s += "#" + getLastUpdated();
        return s;
    }
}
