package org.example;

import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 *  This class serves as the controller in the MVC (Model-View-Controller) design pattern.
 *          It handles user interactions, communicates with the model, and updates the view accordingly.
 *       Diese Klasse dient als Controller im MVC (Model-View-Controller)-Designmuster.
 *    Sie verarbeitet Benutzerinteraktionen, kommuniziert mit dem Modell und aktualisiert die Ansicht entsprechend.
 */
public class Controller {

      // Reference to the model, which contains the application data and business logic.
    //Referenz auf das Modell, das die Anwendungsdaten und die Geschäftslogik enthält.
//final usage:Prevents the controller's connection to the model and view from being changed.
// This keeps the model and view consistent throughout the program.

    private final Modell model;

    // Reference to the view, which is responsible for displaying the user interface.
    // Referenz auf die Ansicht, die für die Anzeige der Benutzeroberfläche verantwortlich ist.
    private final View view;

    /**
     * Constructor for initializing the controller with the model and the view.
     * Konstruktor zur Initialisierung des Controllers mit dem Modell und der Ansicht.
     *
     * @param model The application model / Das Anwendungsmodell
     * @param view  The application view / Die Anwendungsansicht
     */
    public Controller(Modell model, View view) {
        this.model = model;
        this.view = view;
    }

    /**
     * Saves the functions selected by the user and stores them in a license file.
     * Speichert die vom Benutzer ausgewählten Funktionen und speichert sie in einer Lizenzdatei.
     *
     * @param checkBoxes List of checkboxes representing available functions
     *                   / Liste der Kontrollkästchen, die verfügbare Funktionen darstellen
     */
    public void handleSave(List<CheckBox> checkBoxes) {
        // Filters selected checkboxes and retrieves their labels.
        //Filtert ausgewählte Kontrollkästchen und ruft deren Beschriftungen ab.
        List<String> selectedFunctions = checkBoxes.stream()
                .filter(CheckBox::isSelected)
                .map(CheckBox::getText)
                .collect(Collectors.toList());

        if (!selectedFunctions.isEmpty()) {
            // Sets the serial number, start date, and end date for the license.
            // Setzt die Seriennummer, das Startdatum und das Enddatum für die Lizenz.
            model.setSerinummer("123456789"); // Example serial number / Beispiel-Seriennummer
            model.setStartDatum(LocalDate.now());
            model.setEndDatum(LocalDate.now().plusYears(1));

            // English: Saves the license file and checks if the operation was successful.
            // Deutsch: Speichert die Lizenzdatei und prüft, ob die Operation erfolgreich war.
            boolean saved = model.saveLicenceFile();
            if (saved) {
                // English: Joins selected function names into a string and updates the view with license information.
                // Deutsch: Verbindet die Namen der ausgewählten Funktionen zu einem String und aktualisiert die Ansicht mit Lizenzinformationen.
                String savedFunctions = String.join(", ", selectedFunctions);
                view.updateLicenceInfo(model.getSeriennummer(), model.getStartDatum(), model.getEndDatum());
                showAlert("Success", "Selected functions saved successfully:\n" + savedFunctions +
                        "\n\nSaved to: \nPath: C:\\Temp \\Licencekey.json" + model.getLicenceFilePath());
            }
        } else {
            //  Displays a warning if no functions were selected.
            // Zeigt eine Warnung an, wenn keine Funktionen ausgewählt wurden.
            showAlert("Warning", "No functions selected!");
        }
    }

    /**
     * Resets all selected functions to their default (unselected) state.
     *  Setzt alle ausgewählten Funktionen auf ihren Standardzustand (nicht ausgewählt) zurück.
     *a
     * @param checkBoxes List of checkboxes to be reset
     *                   / Liste der Kontrollkästchen, die zurückgesetzt werden sollen
     */
    public void handleDelete(List<CheckBox> checkBoxes) {
        //  Deselects all checkboxes.
        // Hebt die Auswahl aller Kontrollkästchen auf.
        checkBoxes.forEach(box -> box.setSelected(false));

        //  Clears license information displayed in the view.
        // Löscht die in der Ansicht angezeigten Lizenzinformationen.
        view.clearLicenceInfo();

        // Displays a notification that all selections have been cleared.
        // Zeigt eine Benachrichtigung an, dass alle Auswahlen gelöscht wurden.
        showAlert("Deleted", "All selected data has been cleared.");
    }

    /**
     *  Displays an alert dialog with a title and message.
     *       Zeigt ein Warnungsdialog mit einem Titel und einer Nachricht an.
     *
     * @par
     *
     * am title   Title of the alert / Titel des Warnungsdialogs
     * @param message Message to be displayed in the alert / Nachricht, die im Warnungsdialog angezeigt werden soll
     */
    private void showAlert(String title, String message) {

        //  Creates an information alert and displays it to the user.
        // Erstellt einen Informationsdialog und zeigt ihn dem Benutzer an.
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
