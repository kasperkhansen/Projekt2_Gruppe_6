package Testing.Database;

import Controller.DatabaseController;
import Controller.MedlemController;
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
        MedlemController.printAlleMedlemmer();
        System.out.println("Test: MedlemController list printed");
        System.out.println();

        // Create test members
        System.out.println("Test: Creating test members");
        MedlemController.tilfoejMedlem(new Medlem("paul", 1, LocalDate.of(1992, 1, 1)));
        MedlemController.tilfoejMedlem(new Medlem("TestMember2", 2,  LocalDate.of(1997, 2, 1)));
        System.out.println("Test: Test members created");
        System.out.println();

        // Add members to MedlemController
        // test if medlemmer automatically added to alleMedlemmer in MedlemController
        System.out.println("Test: Adding test members to MedlemController");
        MedlemController.printAlleMedlemmer();
        System.out.println("Test: MedlemController list printed");
        System.out.println();


        // Save members to database
        System.out.println("Test: Saving members to database");
        DatabaseController.saveArrToFileDatabase();
        DatabaseController.printDatabase();
        System.out.println("Test: Members saved to database");
        System.out.println();

        // Clear the MedlemController list to simulate a fresh start
        System.out.println("Test: Clearing MedlemController list to simulate fresh start");
        MedlemController.alleMedlemmer.clear();
        System.out.println("Test: MedlemController list cleared");
        MedlemController.printAlleMedlemmer();
        System.out.println("Test: MedlemController list printed");
        System.out.println();

        // Load members from database
        System.out.println("Test: Loading members from database");
        DatabaseController.loadFilesToArr();
        System.out.println("Test: Members loaded from database\n");

        System.out.println("Loaded from database to alleMedlemmer arraylist:");
        MedlemController.printAlleMedlemmer();
        System.out.println("Test: MedlemController list printed");


        // Check if members are loaded correctly
        if (MedlemController.alleMedlemmer.size() == 2) {
            System.out.println("Test Passed: Members loaded correctly.");
            System.out.println("\n");
        } else {
            System.out.println("Test Failed: Incorrect number of members loaded.");
            System.out.println("\n");
        }

        // Additional checks can be added here to verify the details of the loaded members
    }

    private static void testAlleMedlemmer() {
        // registrer, skift medlemskab, betal engangsbillet, opdater medlem, fjern medlem
        // test the members created in the testSaveAndLoad method
        System.out.println("Test: Testing alleMedlemmer arraylist");
        System.out.println("Test: Testing registrerMedlem, skiftMedlemskab, betalEngangsbillet, opdaterMedlem, fjernMedlem");

        // if IndexOutOfBoundsException is thrown, the test fails
        // problem would be that the MedlemController.alleMedlemmer arraylist is not updated
        //
        Medlem medlem1 = MedlemController.alleMedlemmer.get(0);
        Medlem medlem2 = MedlemController.alleMedlemmer.get(1);

        UserMenu.displayMenu();

        // skift medlemskab
        System.out.println("Test: Skift medlemskab\n");

        MedlemController.skiftMedlemskabMedInputScan();
        System.out.println("\n");

        // betal engangsbillet
        System.out.println("Test: Betal engangsbillet\n");
        MedlemController.betalEngangsbillet();
        System.out.println("\n");

        // opdater medlem
        System.out.println("Test: Opdater medlem\n");
        MedlemController.opdaterMedlem(medlem1);
        System.out.println("\n");

        // fjern medlem
        System.out.println("Test: Fjern medlem\n");
        MedlemController.fjernMedlem(medlem2);
        System.out.println("\n");
    }
}

