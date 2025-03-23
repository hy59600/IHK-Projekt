package org.example;

import org.json.simple.JSONObject;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

/**
 * The `Modell` class is responsible for managing license data and functions.
 * This includes loading functions from a CSV file and managing license data saved in a JSON file.
   * Die Klasse `Modell` ist für die Verwaltung von Lizenzdaten und Funktionen verantwortlich.
   * Dies umfasst das Laden von Funktionen aus einer CSV-Datei und die Verwaltung von Lizenzdaten, die in einer JSON-Datei gespeichert sind.
 */


public class Modell {

    private String  seriennummer; // Printer serial number / Druckerseriennummer
    private LocalDate startDatum; // License start date / Lizenzstartdatum
    private LocalDate endDatum; // License end date / Lizenzenddatum
    private final String licenceFilePath = "C:\\Users\\fyurd\\Desktop\\Masaüstü\\selam\\IHK_Project\\licence.file"; // Path to the license file / Pfad zur Lizenzdatei
    private Map<String, Function> myMapOfPL6Functions; // Stores functions with their IDs as keys / Speichert Funktionen mit ihren IDs als Schlüssel

    /**
     * Constructor initializes the function map and loads the functions from a CSV file.
     * Der Konstruktor initialisiert die Funktionsmappe und lädt die Funktionen aus einer CSV-Datei.
     */
    public Modell() {
        // LinkedHashMap speichert die Schlüssel-Werte-Paare in der Reihenfolge, in der sie hinzugefügt wurden.
        this.myMapOfPL6Functions = new LinkedHashMap<>();//Dieses Objekt wird verwendet, um die Funktionen des Geräts als Schlüssel-Wert-Paare zu speichern.
        loadFunctionsFromCSV();//Daten aus einer CSV-Datei zu lesen und diese in eine geeignete Datenstruktur (z. B. LinkedHashMap) zu laden.
    }

    /**
     * Loads functions from a CSV file and populates the map of functions.
     * Each function has an ID, name, and description which are stored in the `myMapOfPL6Functions` map.
     * Funktionen aus einer CSV-Datei laden und die Funktionsmappe auffüllen.
     * Jede Funktion hat eine ID, einen Namen und eine Beschreibung, die in der `myMapOfPL6Functions`-Mappe gespeichert werden.
     */
    private void loadFunctionsFromCSV() {
        String csvFile = "C:\\Users\\fyurd\\Desktop\\Masaüstü\\selam\\IHK_Project\\functions2.csv"; // Path to the CSV file / Pfad zur CSV-Datei
        String line; // Holds each line of the CSV file / Hält jede Zeile der CSV-Datei
        String csvSplitBy = ","; // Delimiter used in the CSV file / Trennzeichen in der CSV-Datei

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {//BufferedReader kann eine Datei nicht direkt öffnen. Stattdessen benötigt es ein FileReader objekt
            br.readLine(); // Skip headers / Kopfzeile überspringen
            while ((line = br.readLine()) != null) {// Alle Zeilen in der Datei einzeln lesen.
                String[] fields = line.split(csvSplitBy); // Split each line into fields / Teilt jede Zeile in Felder auf
                myMapOfPL6Functions.put(fields[0], new Function(fields[0], fields[1], fields[2])); // Add function to the map / Funktion zur Map hinzufügen
            }
        } catch (IOException e) {
            System.err.println("Error loading functions from CSV: " + e.getMessage());
        }
    }

    /**
     * Saves the license data to a file in JSON format.
     * The license includes the printer's serial number, start date, and end date.
     * Speichert die Lizenzdaten in einer Datei im JSON-Format.
     * Die Lizenz enthält die Druckerseriennummer, das Startdatum und das Enddatum.
     *
     * @return true if the license file was saved successfully, false otherwise.
     * true, wenn die Lizenzdatei erfolgreich gespeichert wurde, andernfalls false.
     */
    public boolean saveLicenceFile() {
        if (seriennummer == null || startDatum == null || endDatum == null) {//Wenn eine dieser drei Variablen null (leer, also undefiniert) ist, schlägt der Lizenzierungsprozess aufgrund fehlender Daten fehl.
            System.err.println("Error: License data is incomplete.");
            return false;
        }

        //JSON organisiert Daten logisch mit Schlüssel-Wert-Paaren.JSON ist ein Format, das sowohl für Menschen als auch für Maschinen lesbar ist.
        JSONObject jsonObject = new JSONObject(); // Create a JSON object / Ein JSON-Objekt erstellen
        jsonObject.put("seriennummer", seriennummer); // Add serial number to JSON / Seriennummer zum JSON hinzufügen
        jsonObject.put("startDatum", startDatum.toString()); // Add start date to JSON / Startdatum zum JSON hinzufügen
        jsonObject.put("endDatum", endDatum.toString()); // Add end date to JSON / Enddatum zum JSON hinzufügen



       //Saving license information stored in JSON format to a specified path in the file system.
        try (FileWriter file = new FileWriter(licenceFilePath)) {
            file.write(jsonObject.toJSONString()); // Write JSON to file / JSON in die Datei schreiben
            file.flush(); // Ensure all data is written / Sicherstellen, dass alle Daten geschrieben wurden
            System.out.println("License saved: " + jsonObject.toJSONString());//Konvertiert das JSON-Objekt in ein Array im JSON-Format:
            return true;
        } catch (IOException e) {
            System.err.println("Error saving license file: " + e.getMessage());
            return false;
        }
    }

    /**
     * Loads license data from a file in JSON format.
     * Lädt Lizenzdaten aus einer Datei im JSON-Format.
     *
     * @return true if the license file was loaded successfully, false otherwise.
     * true, wenn die Lizenzdatei erfolgreich geladen wurde, andernfalls false.
     */

    // Getter and Setter methods...

   public String getSeriennummer() {
        return seriennummer;
    }

    public void setSerinummer(String seriennummer) {
        this.seriennummer = seriennummer;
    }

    public LocalDate getStartDatum() {
        return startDatum;
    }

    public void setStartDatum(LocalDate startDatum) {
        this.startDatum = startDatum;
    }

    public LocalDate getEndDatum() {
        return endDatum;
    }

    public void setEndDatum(LocalDate endDatum) {
        this.endDatum = endDatum;
    }

    public Map<String, Function> getPL6Functions() {
        return myMapOfPL6Functions;}//This method returns a Map object named myMapOfPL6Functions defined inside the class.
    // The Function class is a data structure that represents each function in the license management system.

    public String getLicenceFilePath() {
        return licenceFilePath;
    }

    /**
     * Inner class representing a function with an ID, name, and description.
     * Innere Klasse, die eine Funktion mit einer ID, einem Namen und einer Beschreibung darstellt.
     * /**
     *  * The `Function` class is an inner class of `Modell`, representing a single function in the license management system.
     *  * This design was chosen to encapsulate function-related data and maintain close coupling with the parent `Modell` class.
     *  *
     *  * **Why an Inner Class Instead of a Separate Class?**
     *  * 1. **Encapsulation:**
     *  *    - By defining `Function` as an inner class, its usage is tightly bound to `Modell`.
     *  *    - This ensures that `Function` objects are contextually linked to the license management logic.
     *  *
     *  * 2. **Simplified Structure:**
     *  *    - Keeping `Function` as an inner class avoids creating additional top-level classes, maintaining simplicity in the project hierarchy.
     *  *
     *  * 3. **Single Responsibility:**
     *  *    - The `Function` class is solely responsible for holding data related to individual functions, while the `Modell` class handles business logic and data processing.
     *  *
     *  * **Purpose of the `Function` Class:**
     *  * - To represent individual functions with unique attributes:
     *  *   - `id`: A unique identifier for the function.
     *  *   - `name`: The name of the function.
     *  *   - `descr`: A description of the function.
     *  *
     *  * **Key Benefits of the `Function` Class:**
     *  * - **Modularity:** Each function is treated as an independent object, allowing for easy management and updates.
     *  * - **Data Organization:** Provides a structured way to store function data in the `myMapOfPL6Functions` map.
     *  * - **Flexibility:** New functions can be added dynamically by creating new `Function` objects.
     *  *
     */
    public static class Function {
        private final String id;   // Unique identifier for the function / Eindeutiger Bezeichner für die Funktion
        private final String name; // Name of the function / Name der Funktion
        private final String descr; // Description of the function / Beschreibung der Funktion

        public Function(String id, String name, String descr) {
            this.id = id;
            this.name = name;
            this.descr = descr;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getDescr() {
            return descr;
        }

        @Override
        public String toString() {
            return "Function{id='" + id + "', name='" + name + "', description='" + descr + "'}";
        }
    }
}
