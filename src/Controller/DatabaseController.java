package Controller;

import Model.Medlem;
import java.io.*;
import java.util.ArrayList;

public class DatabaseController {

    public static final String DATABASE_PATH = "src/Database/";
    private static File[] listOfFiles;

    static {
        // Create the database directory if it does not exist
        new File(DATABASE_PATH).mkdirs();
    }

    // Save alleMedlemmer arraylist of MedlemController to files for each member
    public static void saveDatabase() {
        for (Medlem medlem : MedlemController.alleMedlemmer) {
            try {
                File file = new File(DATABASE_PATH + medlem.getNavn() + ".dat");
                if (file.createNewFile()) {
                    System.out.println("File created: " + file.getName());
                } else {
                    System.out.println("File already exists.");
                }
            } catch (IOException e) {
                System.err.println("Error saving member: " + medlem.getNavn());

            }

        }
        updateListOfFiles();
    }

    // Load alleMedlemmer arraylist of MedlemController from files for each member
    public static void loadDatabase() {
        MedlemController.alleMedlemmer.clear();
        File folder = new File(DATABASE_PATH);
        File[] listOfFiles = folder.listFiles();

        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                if (file.isFile()) {
                    Medlem loadedMedlem = loadFileToMedlem(file.getName());
                    if (loadedMedlem != null) {
                        MedlemController.alleMedlemmer.add(loadedMedlem);
                    }
                }
            }
        }
    }

    // Get list of files in the database directory
    private static File[] getListOfFiles() {
        File folder = new File(DATABASE_PATH);
        return folder.listFiles();
    }

    // Update the list of files
    private static void updateListOfFiles() {
        listOfFiles = getListOfFiles();
    }

    // Save an object as a file
    public static void saveObjectAsFile(Object object, String fileName) {
        try (FileOutputStream fileOut = new FileOutputStream(fileName);
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
            objectOut.writeObject(object);
            System.out.println("The Medlem was successfully saved in Database");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Load a Medlem object from a file
    public static Medlem loadFileToMedlem(String fileName) {
        try (FileInputStream fileIn = new FileInputStream(DATABASE_PATH + fileName);
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
            return (Medlem) objectIn.readObject();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
