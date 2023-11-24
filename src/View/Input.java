package View;

import java.time.LocalDate;
import java.util.Scanner;

public abstract class Input {

    private static Scanner scanner;

    public Input() {
        scanner = newScannerErrorHandling();
    }

    private Scanner newScannerErrorHandling () {
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
            System.out.println("Enter date in format yyyy-mm-dd: ");
            try {
                return LocalDate.parse(scanner.next());
            } catch (Exception e) {
                System.out.println("Error: " + e);
            }
        }
    }

}
