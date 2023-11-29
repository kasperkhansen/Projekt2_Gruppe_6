package Testing.Database;

import Controller.DatabaseController;
import Controller.MedlemController;
import Model.Medlem;
import java.time.LocalDate;

public class MedlemDatabaseTest {

    public static void main(String[] args) {
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

    private static void testSaveAndLoad() {
        // Clear existing members
        MedlemController.alleMedlemmer.clear();

        // Create test members
        Medlem medlem1 = new Medlem("TestMember1", 1, 30, LocalDate.of(1992, 1, 1));
        Medlem medlem2 = new Medlem("TestMember2", 2, 25, LocalDate.of(1997, 2, 1));

        // Add members to MedlemController
        MedlemController.alleMedlemmer.add(medlem1);
        MedlemController.alleMedlemmer.add(medlem2);

        // Save members to database
        DatabaseController.saveDatabase();

        // Clear the MedlemController list to simulate a fresh start
        MedlemController.alleMedlemmer.clear();

        // Load members from database
        DatabaseController.loadDatabase();

        // Check if members are loaded correctly
        if (MedlemController.alleMedlemmer.size() == 2) {
            System.out.println("Test Passed: Members loaded correctly.");
        } else {
            System.out.println("Test Failed: Incorrect number of members loaded.");
        }

        // Additional checks can be added here to verify the details of the loaded members
    }
}

