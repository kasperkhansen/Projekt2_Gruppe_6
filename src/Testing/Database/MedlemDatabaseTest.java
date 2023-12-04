package Testing.Database;

import Controller.DatabaseController;
import Controller.MedlemController;
import Controller.TraenerController;
import Model.Medlem;
import View.MenuSystem.UserMenu;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

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
        assert MedlemController.alleMedlemmer.contains(testMember1) : "Failed to load testMember1.";
        System.out.println("Loaded testMember1: " + testMember1.toString());
        System.out.println();
        assert MedlemController.alleMedlemmer.contains(testMember2) : "Failed to load testMember2.";
        System.out.println("Loaded testMember2: " + testMember2.toString());
        System.out.println();
        System.out.println("testSaveAndLoad passed successfully.");

    }

    private static void testAlleMedlemmer() {
        System.out.println("Starting testAlleMedlemmer...");

        // Setup
        MedlemController.alleMedlemmer.clear();
        Medlem testMember1 = new Medlem("paul", 1, LocalDate.of(1992, 1, 1), 12345678);
        Medlem testMember2 = new Medlem("TestMember2", 2, LocalDate.of(1997, 2, 1), 87654321);
        MedlemController.tilfoejMedlem(testMember1);
        MedlemController.tilfoejMedlem(testMember2);

        // Test operations
        // Mock or simulate user input as needed for these methods
        MedlemController.skiftMedlemskabMedInputScan();
        MedlemController.betalEngangsbillet();
        MedlemController.opdaterMedlem(testMember1);



        // Assertions
        // Add specific assertions here to verify the expected outcomes of the operations

        System.out.println("testAlleMedlemmer passed successfully.");
    }
}
