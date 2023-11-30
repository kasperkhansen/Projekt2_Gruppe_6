package Testing.Database;

import Controller.DatabaseController;
import Controller.MedlemController;
import Model.Medlem;

import java.io.IOException;
import java.time.LocalDate;

public class MedlemDatabaseTest {

    public static void main(String[] args) throws IOException {
        // Test the database functionality
        testSaveAndLoad();
        // test alleMedlemmer arraylist
        testAlleMedlemmer();
    }

    private static void testAlleMedlemmer() {
        // registrer, skift medlemskab, betal engangsbillet, opdater medlem, fjern medlem
        // test the members created in the testSaveAndLoad method
        Medlem medlem1 = MedlemController.alleMedlemmer.get(0);
        Medlem medlem2 = MedlemController.alleMedlemmer.get(1);

        // skift medlemskab
        MedlemController.skiftMedlemskab("TestMember1");

        // betal engangsbillet
        MedlemController.betalEngangsbillet();

        // opdater medlem
        MedlemController.opdaterMedlem(medlem1);

        // fjern medlem
        MedlemController.fjernMedlem(medlem2);
    }

    private static void testSaveAndLoad() throws IOException {
        // Clear existing members
        System.out.println("Test: Clearing MedlemController list");
        MedlemController.alleMedlemmer.clear();
        MedlemController.printAlleMedlemmer();
        System.out.println("Test: MedlemController list printed");
        System.out.println();

        // Create test members
        System.out.println("Test: Creating test members");
        Medlem medlem1 = new Medlem("paul", 1, LocalDate.of(1992, 1, 1));
        Medlem medlem2 = new Medlem("TestMember2", 2,  LocalDate.of(1997, 2, 1));
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
        DatabaseController.saveDatabase();
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
        DatabaseController.loadDatabase();
        System.out.println("Test: Members loaded from database");
        System.out.println("Loaded from database to alleMedlemmer arraylist:");
        MedlemController.printAlleMedlemmer();
        System.out.println("Test: MedlemController list printed");


        // Check if members are loaded correctly
        if (MedlemController.alleMedlemmer.size() == 2) {
            System.out.println("Test Passed: Members loaded correctly.");
        } else {
            System.out.println("Test Failed: Incorrect number of members loaded.");
        }

        // Additional checks can be added here to verify the details of the loaded members
    }
}

