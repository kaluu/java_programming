package possystem;

/*
 * Import-Anweisungen, um Bibliotheken einzubinden.
 */
import java.beans.*;                        // fuer die XML-Speicherung
import java.io.*;                           // fuer das Speichern und Laden in / von Dateien
import java.util.*;                         // fuer ArrayLists
import de.htw.saarland.stl.Stdin;           // fuer Eingaben von der Konsole

/**
 * Die Klasse Verwaltung stellt den Hauptteil der Applikation ImmoPortal dar.
 * Hier wird die Programmlogik implementiert, sowie die Benutzer-Menues.
 *
 * @author Prof. Dr. Daniel F. Abawi
 * @version v1 - November 2010
 */
public class Verwaltung implements Serializable
{
    private ArrayList <Mitarbeiter> mitarbeiterListe;
    private ArrayList <Kunde> kundenListe; 

    final static int FELDLAENGE = 14;

    /**
     * Constructor for objects of class Verwaltung
     */
    public Verwaltung() {
        mitarbeiterListe = new ArrayList <Mitarbeiter>();
        kundenListe = new ArrayList <Kunde>();  
    }

    public void initData() {
        Mitarbeiter m1 = new Mitarbeiter("Max", "Musterman", "Kassierer", 45);
        Mitarbeiter m2 = new Mitarbeiter("Thomas", "Klein", "Verwalter", 50);
        Mitarbeiter m3 = new Mitarbeiter("Daniel", "Wagner", "Lagerverwalter", 37);
        Mitarbeiter m4 = new Mitarbeiter("Susanne", "Groß", "Lagerverwalter", 36);
        mitarbeiterListe.add(m1);
        mitarbeiterListe.add(m2);
        mitarbeiterListe.add(m3);
        mitarbeiterListe.add(m4);
        
        Kunde k1 = new  Kunde("Lukas", "Schneider", 23);
        Kunde k2 = new  Kunde("Lars", "Roob", 31);
        Kunde k3 = new  Kunde("Sabine", "Linn", 37);
        Kunde k4 = new  Kunde("Elisa", "Müller", 36);
        Kunde k5 = new  Kunde("Melissa", "Schneider", 28);
        kundenListe.add(k1);
        kundenListe.add(k2);
        kundenListe.add(k3);
        kundenListe.add(k4);
        kundenListe.add(k5);
    }
 
    
/***************************************************************************************************************/
/**********************************Funktionen zum Speichern der Daten*******************************************/
/***************************************************************************************************************/
    private void saveDataToXML() throws IOException {
        XMLEncoder o = new XMLEncoder(new FileOutputStream("POS-System.xml"));
        o.writeObject("POS-System");
        o.writeObject(this);
        o.close();
    }

    public Object loadDataFromXML() throws IOException {
        XMLDecoder o = new XMLDecoder(new FileInputStream("POS-System.xml"));
        o.readObject();
        Object obj = o.readObject();
        o.close();
        return obj;
    }

    private void save() {
        try {
            saveDataToXML();
        }
        catch(java.io.IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private void load() {
        try {
            Verwaltung v = (Verwaltung) loadDataFromXML();
            // hier starten wir ein neues Programm (eine neue Instanz der Klasse Verwaltung, der wir auch die Kontrolle übergeben), damit wir an die geladenen Daten kommen
            v.mainMenue();
        }
        catch(java.io.IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private void beenden() {
        System.exit(0);
    }

    public ArrayList getmitarbeiterListe() {
        return this.mitarbeiterListe;
    }

    public void setmitarbeiterListe(ArrayList liste) {
        this.mitarbeiterListe = liste;
    }
    
    public ArrayList getkundenListe() {
        return this.kundenListe;
    }

    public void setkundenListe(ArrayList liste) {
        this.kundenListe = liste;
    }

    
/***************************************************************************************************************/
/*************************************************Menüführung***************************************************/
/***************************************************************************************************************/
    public void mainMenue() {
        char eingabe;

        do {
            System.out.println("HAUPTMENUE");
            System.out.println("------------------------------------------------------------");
            System.out.println("[1] Verwaltungsmenue");
            System.out.println("[2] Mitarbeitermenue");
            System.out.println("[3] Kundenmenue");
            System.out.println("[4] Lagerverwaltung");
            System.out.println("[x] Programm beenden");

            printAuswahlTreffen();
            eingabe = Stdin.readlnChar();

            switch (eingabe) {
                case 'x': System.out.println("Vielen Dank für die Nutzung des POS-System. Das Programm wird beendet.");
                beenden();
                case '1': verwaltungsMenue();
                          break;
                case '2': mitarbeiterMenue();
                          break;
                /*case '3': kundenMenue();
                          break;
                case '4': lagerverwaltungsMenue();
                          break;*/
                default: printEingabeFehler();
            }
        } while (true);
    }

    private void verwaltungsMenue() {
        char eingabe;
        boolean menuewechsel = false;
        do {
            System.out.println("VERWALTUNGSMENUE");
            System.out.println("------------------------------------------------------------");
            System.out.println("[1] Lade Daten aus XML-Datei");
            System.out.println("[2] Speichere Daten in XML-Datei");
            System.out.println("[0] HAUPTMENUE");

            printAuswahlTreffen();
            eingabe = Stdin.readlnChar();

            switch (eingabe) {
                case '0': menuewechsel = true;
                    break;
                case '1': load();
                    break;
                case '2': save();
                    break;
                default: printEingabeFehler();
            }
        } while (!menuewechsel);
    }
    
    public void mitarbeiterMenue() {
        char eingabe;
        boolean menuewechsel = false;
        do {
            System.out.println("MITARBEITERMENUE");
            System.out.println("------------------------------------------------------------");
            System.out.println("[1] Mitarbeiter anlegen");
            System.out.println("[2] Mitarbeiter anzeigen");
            System.out.println("[3] Mitarbeiter suchen");
            System.out.println("[0] HAUPTMENUE");

            printAuswahlTreffen();
            eingabe = Stdin.readlnChar();

            switch (eingabe) {
                case '0': menuewechsel = true;
                    break;
                case '1': createEmployee();
                    break;
                case '2': showEmployeeList();
                    break;
                case '3': searchEmployee();
                    break;
                default: printEingabeFehler();
            }
        } while (!menuewechsel);
    }
    
    
/***************************************************************************************************************/
/**************************************Programmlogik (Hauptprogramm)********************************************/
/***************************************************************************************************************/  
    private void createEmployee() {
        String vorname = Stdin.readlnString("Bitte geben Sie den Vornamen des Mitarbeiters ein:");
        String nachname = Stdin.readlnString("Bitte geben Sie den Nachnamen des Mitarbeiters ein:");
        String rolle = Stdin.readlnString("Bitte geben Sie die Rolle des Mitarbeiters ein:");
        int alter = Stdin.readInt("Bitte geben Sie das Alter des Mitarbeiters ein:");
        
        Mitarbeiter m1 = new Mitarbeiter(vorname, nachname, rolle, alter);
        mitarbeiterListe.add(m1);
    }
    
    public void showEmployeeList() {
        String s;

        printZentriert("Vorname");
        printZentriert("Name");
        printZentriert("Rolle");
        printZentriert("Alter");
        printZentriert("Mitarbeiter-ID");
        printLF();
        printLinieLF(5); // Trennlinie für 6 Felder anzeigen
        
        Iterator <Mitarbeiter>iter = mitarbeiterListe.iterator();
        while(iter.hasNext()) {
            Mitarbeiter i  =  iter.next();

            printLinksbuendig(i.getVorname());
            printLinksbuendig(i.getName());
            printLinksbuendig(i.getRolle());
            s = castInt2String(i.getAlter());
            printZentriert(s);
            s = castInt2String(i.getMitarbeiterID());
            printZentriert(s);

            printLF();
        }
        printLF();
    }
    
    public void searchEmployee() {
        String s;
        boolean treffer = false;
        String nachname = Stdin.readlnString("Bitte geben Sie den Nachnamen des Mitarbeiters ein:");
        
        Iterator <Mitarbeiter>iter = mitarbeiterListe.iterator();
        while (iter.hasNext()) {
            Mitarbeiter m = iter.next();
            if (m.getName().equalsIgnoreCase(nachname)) {
                treffer = true;
            }
        }
        
        if (!treffer) {
            System.out.println("Kein Mitarbeiter mit dem Namen " + nachname + " gefunden");
        } else {
            printZentriert("Vorname");
            printZentriert("Name");
            printZentriert("Rolle");
            printZentriert("Alter");
            printZentriert("Mitarbeiter-ID");
            printLF();
            printLinieLF(5); // Trennlinie für 6 Felder anzeigen 
            
            Iterator <Mitarbeiter>iter2 = mitarbeiterListe.iterator();
            while (iter2.hasNext()) {
                Mitarbeiter e = iter2.next();
                
                if (e.getName().equalsIgnoreCase(nachname)) {
                    printLinksbuendig(e.getVorname());
                    printLinksbuendig(e.getName());
                    printLinksbuendig(e.getRolle());
                    s = castInt2String(e.getAlter());
                    printZentriert(s);
                    s = castInt2String(e.getMitarbeiterID());
                    printZentriert(s);  
                }
            }
        }
    }    
    
    
/***************************************************************************************************************/
/**********************************Funktionen zur Darstellung des Menüs*****************************************/
/***************************************************************************************************************/
    private String castInt2String(int meinInt) {
        return Integer.toString(meinInt);
        // die obige Zeile ist von der Funktion identisch zu
        // Integer i = new Integer(meinInt);
        // return i.toString();
    }

    private String castDouble2String(double meinDouble) {
        // hier nutzen wir die Format-Anweisung der Klasse String um die Nachkommastellen zu bestimmen etc.
        return String.format("%,8.2f", meinDouble);
    }

    private void printLF() {
        System.out.println();
    }

    private void printZentriert(String s) {
        System.out.print(baueZentriertenString(s, FELDLAENGE));
    }

    private void printLinksbuendig(String s) {
        System.out.print(baueLinksbuendigenString(s, FELDLAENGE));
    }

    private void printRechtsbuendig(String s) {
        System.out.print(baueRechtsbuendigenString(s, FELDLAENGE));
    }

    private void printLinieLF(int anzahlFelder) {
        /* Besonderheit: hier Nutzung des StringBuilders statt direkt mit String zu arbeiten.
         * Ist sparsamer im Umgang mit Speicher.
         */
        StringBuilder s = new StringBuilder();
        int laenge = anzahlFelder*(FELDLAENGE+3);
        for (int i=1;i<=laenge;i++) {
            s=s.append("-");
        }
        System.out.println(s);
    }

    private String baueZentriertenString(String s, int laenge) {
        // wir entfernen Leerzeichen am Anfang und Ende des Strings
        s.trim();
        // falls der String zu lang ist, kuerzen wir ihn
        if (s.length() > laenge) {
            s.substring(0, laenge);
        } else {
            int differenzLinks = (laenge - s.length())/2;
            for (int i=1;i<=differenzLinks;i++) s=" "+s+" ";
            if (s.length()<laenge) s=s+" ";
        }
        return s+" | ";
    }

    private String baueRechtsbuendigenString(String s, int laenge) {
        // wir entfernen Leerzeichen am Anfang und Ende des Strings
        s.trim();
        // falls der String zu lang ist, kuerzen wir ihn
        if (s.length() > laenge) {
            s.substring(0, laenge);
        } else {
            int differenzLinks = (laenge - s.length());
            for (int i=1;i<=differenzLinks;i++) s=" "+s;
        }
        return s+" | ";
    }

    private String baueLinksbuendigenString(String s, int laenge) {
        // wir entfernen Leerzeichen am Anfang und Ende des Strings
        s.trim();
        // falls der String zu lang ist, kuerzen wir ihn
        if (s.length() > laenge) {
            s.substring(0, laenge);
        } else {
            int differenz = (laenge - s.length());
            for (int i=1;i<=differenz;i++) s=s+" ";
        }
        return s+" | ";
    }

    private void printEingabeFehler() {
        System.out.print("Ihre Eingabe wurde nicht erkannt.\n");
    }

    private void printAuswahlTreffen() {
        System.out.print("Bitte treffen Sie eine Auswahl ...\n");
    }

    private void printProgrammInfo() {
        System.out.println("************************************************************");
        System.out.println("* POS-System by Lukas Kornbrust, ...                       *");
        System.out.println("*                                                          *");
        System.out.println("************************************************************");
    }

    /**
     * 
     * @param args
     */
    public static void main(String[] args) {
        Verwaltung v = new Verwaltung();

        v.initData();
        v.printProgrammInfo();
        v.mainMenue();        
    }
}