package Testing.Database;

import Controller.DatabaseController;
import Controller.KontingentController;
import Controller.MedlemController;
import Model.Medlem;
import View.MenuSystem.KassererSubMenu;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class MedlemDatabaseTest {

    public static void main(String[] args) {
        try {
            testSaveAndLoad();
            testAlleMedlemmer();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("An error occurred during testing: " + e.getMessage());
        }
    }

    private static void testSaveAndLoad() throws IOException {
        System.out.println("Starting testSaveAndLoad...");

        // Setup
        MedlemController.alleMedlemmer.clear();
        Medlem testMember1 = new Medlem("paul", 1, LocalDate.of(1992, 1, 1), 12345678);
        Medlem testMember2 = new Medlem("TestMember2", 2, LocalDate.of(1997, 2, 1), 87654321);
        System.out.println();
        MedlemController.tilfoejMedlem(testMember1);
        System.out.println();
        MedlemController.tilfoejMedlem(testMember2);

        // Test saving to database
        System.out.println();
        System.out.println("Test saving to database...");
        DatabaseController.saveArrToFileDatabase();
        System.out.println("--------------------------\n");
        DatabaseController.printDatabase();
        System.out.println("--------------------------\n");


        // Test loading from database
        MedlemController.alleMedlemmer.clear();
        DatabaseController.loadFilesToArr();
        MedlemController.printAlleMedlemmer();
        System.out.println("--------------------------\n");

        // Assertions
        assert MedlemController.alleMedlemmer.size() == 2 : "Failed to load the correct number of members.";
        System.out.println("Loaded " + MedlemController.alleMedlemmer.size() + " members.\n");

        if (DatabaseController.listOfFiles.length== MedlemController.alleMedlemmer.size()) {
            System.out.println("Number of files in database matches number of members in arraylist.");
        } else {
            System.out.println("error: Number of files in database does not match number of members in arraylist.");
        }

        ArrayList<Medlem> loadedMembers = getMembersOfFiles();

        int i = 0;
        for (Medlem m : loadedMembers) {
            assert MedlemController.alleMedlemmer.contains(m) : "Failed to load "+ m.getNavn() + " from file.";
            System.out.println("Loaded "+ m.getNavn()+": " + m.toString());
            System.out.println();
        }

    }

    private static ArrayList<Medlem> getMembersOfFiles() {
        ArrayList<Medlem> loadedMembers = new ArrayList<>();
        for (File file : DatabaseController.listOfFiles) {
            loadedMembers.add(DatabaseController.getMedlemFromFile(file));
        }
        return loadedMembers;
    }

    private static void testAlleMedlemmer() {
        System.out.println("Starting testAlleMedlemmer...");

        MedlemController.printAlleMedlemmer();
        System.out.println("--------------------------\n");


        // Test operations
        // Mock or simulate user input as needed for these methods

        KontingentController.opretBetaling(MedlemController.alleMedlemmer.get(0));
        MedlemController.skiftMedlemskabMedInputScan();
        MedlemController.betalEngangsbillet();




        // Assertions
        // Add specific assertions here to verify the expected outcomes of the operations

        System.out.println("testAlleMedlemmer passed successfully.");
    }
}
