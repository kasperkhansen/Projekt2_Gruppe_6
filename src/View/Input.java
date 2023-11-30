package View;

import Model.Medlem;

import java.time.LocalDate;
import java.util.Scanner;

public abstract class Input {

    private static Scanner scanner = newScannerErrorHandling();

    public static int getAgeInput() {
        return 0;
    }

    public static LocalDate getBirthDateInput() {
        return dateInput("Indtast fødselsdato: ");
    }

    public static int medlemskabInput() {
        while (true) {
            System.out.println("Indtast medlemskab: ");
            System.out.println("--------------------");

            System.out.println("tast 1 for Aktiv + konkurrence");
            System.out.println("tast 2 for Aktiv + motionist");
            System.out.println("tast 3 for Passiv");
            int choice = intInput("Valg: ");
            System.out.println("--------------------\n");

            if (choice < 4 && choice > 0) {
                return choice;
            } else {
                System.out.println("Ugyldigt input, prøv igen");
            }

        }

    }



    private static Scanner newScannerErrorHandling() {
        try {
            return new Scanner(System.in);
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return null;
        }
    }

    public static String getNameInput(String message) {
        while (true) {
            System.out.println(message);
            try {
                return scanner.next();
            } catch (Exception e) {
                System.out.println("Error: " + e);
            }
        }
    }

    // datatype input
    public static int intInput(String message) {
        System.out.println(message);
        try {
            return scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return 0;
    }

    public static double doubleInput(String message) {
        System.out.println(message);
        try {
            return scanner.nextDouble();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return 0;
    }

    public static String stringInput(String message) {
        System.out.println(message);
        try {
            return scanner.next();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return null;
    }

    public static LocalDate dateInput(String message){
        System.out.println(message);
        return scanDate();
    }

    private static LocalDate scanDate() {

        while (true) {
            System.out.println("Indtast fødselsdato i dette format yyyy-mm-dd: ");
            try {
                return LocalDate.parse(scanner.next());
            } catch (Exception e) {
                System.out.println("Indtast fødselsdato i dette format yyyy-mm-dd:  ");
            }
        }
    }


}
