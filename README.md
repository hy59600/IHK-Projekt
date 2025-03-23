İşte İngilizce versiyonu:

```markdown
# IHK Project - JavaFX License Manager

This project is a desktop license management application developed with JavaFX. The software allows managing license information of a device and displays functions loaded from a CSV file, saving data in JSON format. The project follows the Model-View-Controller (MVC) architecture.

## 🚀 Features

- ✅ JavaFX GUI
- ✅ Load functions from CSV file
- ✅ Save license information in JSON format
- ✅ Display function descriptions as tooltips
- ✅ MVC architecture
- ✅ Maven build system

## 📁 Project Structure

```
├── src
│   └── main
│       └── java
│           └── org.example
│               ├── Controller.java
│               ├── Modell.java
│               └── View.java
├── functions.csv
├── pom.xml
└── licence.file (generated at runtime)
```

## 🖼️ UI Overview
> The user interface consists of license information at the top, functions with checkboxes in the center, and buttons at the bottom.

## ⚙️ Requirements

- Java 19
- Maven
- JavaFX SDK (already included via Maven dependencies)

## 🏁 How to Run

1. Clone the repository:
   ```bash
   git clone https://github.com/hy59600/IHK-Projekt.git
   ```

2. Build the project with Maven:
   ```bash
   mvn clean install
   ```

3. Run the JavaFX application:
   ```bash
   mvn exec:java -Dexec.mainClass="org.example.View"
   ```

> Note: Make sure JavaFX runtime modules are correctly set up for your system.

## 📄 License
This project is for educational purposes only and is not distributed under any specific license.

---

### 💡 Additional Notes

- `functions.csv` should contain `id,name,descr` columns.
- The `licence.file` is generated in JSON format and contains the serial number, start date, and end date.

---

📬 Feel free to contact for any questions or contributions!
```
