package org.example;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * This class represents the View in the MVC design pattern.
 * It manages the graphical user interface (GUI) for interacting with the license manager.
 * Diese Klasse repräsentiert die Ansicht (View) im MVC-Designmuster.
 * Sie verwaltet die grafische Benutzeroberfläche (GUI) zur Interaktion mit dem Lizenzmanager.
 */
public class View extends Application {

    // Model object to manage license and function data
    // Modellobjekt zur Verwaltung von Lizenz- und Funktionsdaten
    private final Modell myModel = new Modell();

    // Controller object to handle user interactions and coordinate between the Model and View
    // Controller-Objekt zur Verarbeitung von Benutzerinteraktionen und Koordination zwischen Modell und View
    private final Controller controller = new Controller(myModel, this);

    // A list to store all checkboxes for available functions
    // Eine Liste zum Speichern aller Checkboxen für verfügbare Funktionen
    private final List<CheckBox> checkBoxes = new ArrayList<>();//ArrayList provides fast index-based access to elements.ArrayList is especially fast when adding elements to the end of the list.

    // A label to display license information
    // Ein Label zur Anzeige von Lizenzinformationen
    private final Label licenceInfoLabel = new Label("License Information: Not Available");//final clearly indicates that this Label object will be assigned once and will not be changed.

    /**
     * The main method to launch the application.
     * Die Hauptmethode zum Starten der Anwendung.
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Initializes and displays the GUI.
     * Initialisiert und zeigt die grafische Benutzeroberfläche (GUI).
     */
    @Override
    public void start(Stage stage) {
        // Main layout for the application
        // Hauptlayout für die Anwendung
        BorderPane mainLayout = new BorderPane();
        mainLayout.setPadding(new Insets(20));//Represents the surrounding space (padding or margin).

        // Top Section: Displays license information
        // Oberer Abschnitt: Zeigt Lizenzinformationen an
        VBox topSection = new VBox();//Aligns the inserted components vertically (top to bottom).
        topSection.setSpacing(10);
        topSection.setPadding(new Insets(10));
        topSection.setStyle("-fx-background-color: #f9f9f9; -fx-border-color: #dcdcdc;");

        // Label for the license information title
        // Label für den Titel der Lizenzinformationen
        Label licenseInfoTitle = new Label("License Information:");
        licenseInfoTitle.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        licenceInfoLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #555555;");
        topSection.getChildren().addAll(licenseInfoTitle, licenceInfoLabel);//Adds two components (licenseInfoTitle and licenceInfoLabel) to the previously defined VBox layout manager.

        // Scrollable pane for the top section
        // Scrollbereich für den oberen Abschnitt
        ScrollPane topScroll = new ScrollPane(topSection);
        topScroll.setFitToWidth(true);

        // Center Section: Displays available functions with checkboxes
        // Mittelteil: Zeigt verfügbare Funktionen mit Checkboxen an
        GridPane centerSection = new GridPane();//A grid creates a layout similar to a table structure. Components are placed in rows and columns in the grid.
        centerSection.setPadding(new Insets(10));
        centerSection.setHgap(20);
        centerSection.setVgap(10);
        centerSection.setStyle("-fx-background-color: #ffffff; -fx-border-color: #dcdcdc;");

        // Label for the function selection section
        // Label für den Abschnitt zur Funktionsauswahl
        Label functionLabel = new Label("Available Functions:");
        functionLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
        centerSection.add(functionLabel, 0, 0, 3, 1); // Title spans three columns / Titel erstreckt sich über drei Spalten.0 (Column Index):0 (Row Index):3 (Column Span):1 (Row Span):

        // Dynamically add functions with checkboxes and descriptions
        // Dynamisches Hinzufügen von Funktionen mit Checkboxen und Beschreibungen
       // int rowIndex = 1;
        int rowIndex = 1; //
        for (Map.Entry<String, Modell.Function> entry : myModel.getPL6Functions().entrySet()) {//The loop processes each key-value pair in the map, allowing access to:
           // Key: entry.getKey()
           // Value: entry.getValue() (a Modell.Function object).
            //Map.Entry is an interface that represents a key-value pair in Java's Map data structure.




            // Label for the function ID
            // Label für die Funktions-ID
            Label idLabel = new Label(entry.getValue().getId());
            idLabel.setStyle("-fx-font-size: 12px; -fx-font-weight: bold; -fx-text-fill: #333333;");

            // Checkbox for enabling/disabling a function
            // Checkbox zum Aktivieren/Deaktivieren einer Funktion
            CheckBox checkBox = new CheckBox(entry.getValue().getName());
            checkBox.setTooltip(new Tooltip(entry.getValue().getDescr()));
            checkBoxes.add(checkBox);

            // Label for the function description
            // Label für die Funktionsbeschreibung
            Label descriptionLabel = new Label(entry.getValue().getDescr());
            descriptionLabel.setStyle("-fx-padding: 5; -fx-border-color: lightgray; -fx-border-width: 1; -fx-background-color: #f9f9f9;");
            descriptionLabel.setWrapText(true);
            descriptionLabel.setPrefWidth(300);

            // Add components to the grid
            // Komponenten zum Raster hinzufügen
            centerSection.add(idLabel, 0, rowIndex);
            centerSection.add(checkBox, 1, rowIndex);
            centerSection.add(descriptionLabel, 2, rowIndex);
            rowIndex++;
        }

        // Scrollable pane for the center section
        // Scrollbereich für den Mittelteil
        //If the content in the centerSection exceeds the dimensions of the screen, this content becomes scrollable both horizontally and vertically.
        ScrollPane centerScroll = new ScrollPane(centerSection);
        centerScroll.setFitToWidth(true);
        centerScroll.setFitToHeight(true);

        // Bottom Section: Buttons for saving and deleting selections
        // Unterer Abschnitt: Schaltflächen zum Speichern und Löschen von Auswahlen
        HBox buttonBox = new HBox();//HBox is a layout manager that aligns components horizontally within
        buttonBox.setSpacing(20);
        buttonBox.setPadding(new Insets(15));
        buttonBox.setStyle("-fx-background-color: #f4f4f4; -fx-border-color: #dcdcdc;");
        buttonBox.setAlignment(javafx.geometry.Pos.CENTER);//Allows the components within to be centered horizontally and vertically.

        // Button to save selected functions
        // Schaltfläche zum Speichern ausgewählter Funktionen
        Button saveButton = new Button("Save");
        saveButton.setStyle("-fx-font-size: 14px; -fx-background-color: linear-gradient(#4CAF50, #2E7D32); -fx-text-fill: white; -fx-border-radius: 5px;");
        saveButton.setPrefWidth(120);

        // Button to delete selected functions
        // Schaltfläche zum Löschen ausgewählter Funktionen
        Button deleteButton = new Button("Delete");
        deleteButton.setStyle("-fx-font-size: 14px; -fx-background-color: linear-gradient(#f44336, #c62828); -fx-text-fill: white; -fx-border-radius: 5px;");
        deleteButton.setPrefWidth(120);

        buttonBox.getChildren().addAll(saveButton, deleteButton);//used to add subcomponents

        // Connect buttons to Controller actions
        // Schaltflächen mit Controller-Aktionen verbinden
        saveButton.setOnAction(e -> controller.handleSave(checkBoxes));//e is an ActionEvent object representing the click event.
        // However, the event object is not used directly here. Instead, the controller.handleSave(checkBoxes) method is called.
        deleteButton.setOnAction(e -> controller.handleDelete(checkBoxes));

        // Add sections to the main layout
        // Abschnitte zum Hauptlayout hinzufügen
        mainLayout.setTop(topScroll);
        mainLayout.setCenter(centerScroll);
        mainLayout.setBottom(buttonBox);

        // Create and display the scene
        // Szene erstellen und anzeigen
        Scene scene = new Scene(mainLayout, 1000, 700);
        stage.setScene(scene);
        stage.setTitle("License Manager");
        stage.show();
    }

    /**
     * Updates the license information displayed in the top section.
     * Aktualisiert die im oberen Abschnitt angezeigten Lizenzinformationen.
     */
    public void updateLicenceInfo(String seriennummer, LocalDate startDatum, LocalDate endDatum) {
        licenceInfoLabel.setText("License Info: Seriennummer: " + seriennummer +
                ", Start: " + startDatum +
                ", End: " + endDatum);
    }

    /**
     * Clears the license information displayed in the top section.
     * Löscht die im oberen Abschnitt angezeigten Lizenzinformationen.
     */
    public void clearLicenceInfo() {
        licenceInfoLabel.setText("License Information: Cleared");
    }
}
