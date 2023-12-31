package View;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.regex.Pattern;

public abstract class Input {

    private static final Scanner scanner = newScannerErrorHandling();

    /*public static int getAgeInput() {
        return 0;
    }*/

    public static int getIdInput() {
        return intInput("Indtast medlemmets tlf nr: ");
    }

    public static LocalDate getBirthDateInput() {
        return dateInput(" Indtast fødselsdato i dette format yyyy-mm-dd: ");
    }

    public static int medlemskabInput() {
        while (true) {
            System.out.println(" Indtast medlemskab: ");
            System.out.println(" --------------------");

            System.out.println(" Tast 1 for Aktiv + konkurrence");
            System.out.println(" Tast 2 for Aktiv + motionist");
            System.out.println(" Tast 3 for Passiv");
            int choice = intInput("Valg: ");


            if (choice < 4 && choice > 0) {
                return choice;
            } else {
                System.out.println("Ugyldigt input, prøv igen");
            }

        }

    }

    public static int svoemmeDisciplinValg() {
        while (true) {
            System.out.println(" Vælg svømmedisciplin: ");
            System.out.println(" --------------------");

            System.out.println(" Tast 1 for Crawl");
            System.out.println(" Tast 2 for Bryst");
            System.out.println(" Tast 3 for Butterfly");
            int disciplinValg = intInput("Valg: ");

            if (disciplinValg < 4 && disciplinValg > 0) {
                return disciplinValg;
            } else {
                System.out.println("Ugyldigt input, prøv igen");
            }
        }
    }

    public static int traeningEllerKonkurrenceValg() {
        while (true) {
            System.out.println(" Vælg Konkurrence eller Træning: ");
            System.out.println(" --------------------");

            System.out.println(" Tast 1 for Konkurrence");
            System.out.println(" Tast 2 for Træning");
            int konkurrenceValg = intInput("Valg: ");

            if (konkurrenceValg < 3 && konkurrenceValg > 0) {
                return konkurrenceValg;
            } else {
                System.out.println("Ugyldigt input, prøv igen");
            }
        }
    }

    public static double svoemmeTid() {
        while (true) {
            try {
                String tidInput = stringInput(" Indtast tid i format 00.00");

                if (Pattern.matches("\\d+\\.\\d+", tidInput)) {
                    return Double.parseDouble(tidInput);
                } else {
                    System.out.println("Ugyldig format, prøv igen.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Ugyldig format, prøv igen.");
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

    public static String getNameInput(String message, String errorMessage) {
        while(true) {
            System.out.println(message);

            try {
                String input = scanner.nextLine();
                if (input.contains(" ")) {
                    System.out.println("Indtast kun et fornavn. Prøv igen.");
                    return scanner.next();
                }

                return input;
            } catch (Exception e) {
                System.out.println(errorMessage);
            }
        }
    }

    // datatype input
    public static int intInput(String message) {
        while (true) {
            try {
                System.out.println(message);
                int input = scanner.nextInt();
                scanner.nextLine();

                return input;

            } catch (Exception e) {
                System.out.println("Indtast et gyldigt heltal");
                scanner.nextLine();
            }
        }
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
            try {
                return LocalDate.parse(scanner.next());
            } catch (Exception e) {
                System.out.println(" Indtast fødselsdato i dette format yyyy-mm-dd:  ");
            }
        }
    }

    public static boolean booleanInput(String prompt) {
        System.out.print(prompt + " ");
        while (true) {
            String userInput = scanner.nextLine().toLowerCase();
            if (userInput.equals("ja") || userInput.equals("j")) {
                return true;
            } else if (userInput.equals("nej") || userInput.equals("n")) {
                return false;
            } else {
                System.out.println("Ugyldigt input. Indtast venligst Ja/Nej: ");
            }
        }
    }



}
