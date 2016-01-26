package possystem;

/**
 *
 * @author Lukas Kornbrust, ...
 */
public class Kunde {
    private String name;
    private String vorname;
    private int alter;
    private int kundenID;
    private static int idCounter= 2000;
    
    //Standartkonstruktor
    public Kunde() {
    }
    
    // Konstruktor
    public Kunde(String name, String vorname, int alter) {
        this.vorname = vorname;
        this.name = name;
        this.alter = alter;
        this.kundenID = idCounter;
        idCounter++;
    }
    
    // Get und Set Methoden
    public void setAlter(int alter) {
        this.alter = alter;
    }
    public void setKundenID(int kundenID) {
        this.kundenID = kundenID;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setVorname(String vorname) {
        this.vorname = vorname;
    }
    
    public int getAlter() {
        return this.alter;
    }
    public int getKundenID() {
        return this.kundenID;
    }
    public String getName() {
        return this.name;
    }   
    public String getVorname() {
        return this.vorname;
    } 
}
