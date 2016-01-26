package possystem;

/**
 *
 * @author Lukas Kornbrust, ...
 */
public class Mitarbeiter {
    private String name;
    private String vorname;
    private String rolle;                   // Mitarbeiter kann verschiedene Rollen einnehmen (Kassierer, Lagerverwalter, Geschäftsführer)
    private int alter;
    private int mitarbeiterID;
    private static int idCounter = 2000;
    
    //Standartkonstruktor
    public Mitarbeiter() {
    }
    
    // Konstruktor
    public Mitarbeiter(String vorname, String name, String rolle, int alter) {
        this.vorname = vorname;
        this.name = name;
        this.rolle = rolle;
        this.alter = alter;
        this.mitarbeiterID = idCounter;
        idCounter++;
    }
    
    // Get und Set Methoden
    public void setRolle(String rolle) {
        this.rolle = rolle;
    }
    public void setAlter(int alter) {
        this.alter = alter;
    }
    public void setMitarbeiterID(int mitarbeiterID) {
        this.mitarbeiterID = mitarbeiterID;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setVorname(String vorname) {
        this.vorname = vorname;
    }
    
    public String getRolle() {
        return this.rolle;
    }
    public int getAlter() {
        return this.alter;
    }
    public int getMitarbeiterID() {
        return this.mitarbeiterID;
    }
    public String getName() {
        return this.name;
    }
    public String getVorname() {
        return this.vorname;
    }
}
