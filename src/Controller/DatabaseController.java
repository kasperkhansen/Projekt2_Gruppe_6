package Controller;

import Model.Konkurrenceresultat;
import Model.Medlem;
import Model.Traeningsresultat;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DatabaseController {

    public static final String DATABASE_PATH = "src/Database/";
    public static File[] listOfFiles;

    // instance variables of medlem
    private static String navn;
    private static int medlemskabNr;
    private static int alder;
    private static int id;
    private static LocalDate foedselsdato;

    // Create the database directory if it does not exist
    static {
        new File(DATABASE_PATH).mkdirs();
    }

    // Save alleMedlemmer arraylist of MedlemController to files for each member
    public static void saveArrToFileDatabase() {
        for (Medlem medlem : MedlemController.alleMedlemmer) {
            saveMedlemAsFile(medlem);
        }
        updateListOfFiles();
    }

    // Save a Medlem as a file
    public static void saveMedlemAsFile(Medlem m) {

        try {
            File file = new File(DatabaseController.DATABASE_PATH + m.getId() + ".txt");

            // check if file exists
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
                FileWriter fileWriter = new FileWriter(file);

                // write file with structure to preserve all Medlem instance variables as strings
                fileWriter.write("navn: "+m.getNavn() + "\n");
                fileWriter.write("medlemskabsNr: "+m.getMedlemskabNr() + "\n");
                fileWriter.write("alder: "+m.getAlder() + "\n");
                fileWriter.write("foedselsdato: "+m.getFoedselsdato() + "\n");

                // document Traening and Konkurrence results of Medlem
                fileWriter.write("traening resultater: \n");
                writeTraeningsTiderToFile(m.getButterflyTraening(), fileWriter);
                writeTraeningsTiderToFile(m.getCrawlTraening(), fileWriter);
                writeTraeningsTiderToFile(m.getBrystTraening(), fileWriter);

                fileWriter.write("konkurrence resultater: \n");
                writeKonkurrenceTiderToFile(m.getButterflyKonkurrence(), fileWriter);
                writeKonkurrenceTiderToFile(m.getCrawlKonkurrence(), fileWriter);
                writeKonkurrenceTiderToFile(m.getBrystKonkurrence(), fileWriter);


                System.out.println("Member data saved to file: " + file.getName());
                fileWriter.close();
            } else {

            }
        } catch (IOException e) {
            System.err.println("Error saving member: " + m.getNavn());
        }
    }



    // Load alleMedlemmer arraylist of MedlemController from files for each member
    public static void loadFilesToArr() {
        try {
            ArrayList<Medlem> loadedMembers = loadFiles(); // Load members from files
            if (!loadedMembers.isEmpty()) {
                MedlemController.alleMedlemmer.clear(); // Clear list only if files are loaded successfully
                MedlemController.alleMedlemmer.addAll(loadedMembers);
            }
        } catch (IOException e) {
            System.err.println("Error loading files: " + e.getMessage());
        }
    }

    // 1
    private static ArrayList<Medlem> loadFiles() throws IOException {
        updateListOfFiles();
        ArrayList<Medlem> loadedMembers = getMembersOfFiles();
        for (File file : listOfFiles) {
            try {
                Medlem medlem = getMedlemFromFile(file); // Directly get Medlem object
                if (medlem != null) {
                    loadedMembers.add(medlem);
                }
            } catch (Exception e) {
                System.err.println("Error loading file: " + file.getName() + " - " + e.getMessage());
                // Hvis error ved fil, så fortsættes til andre filer
            }
        }
        return loadedMembers;
    }



    // Update the list of files
    private static void updateListOfFiles() {
        File folder = new File(DATABASE_PATH);
        listOfFiles = folder.listFiles((dir, name) -> name.endsWith(".txt"));
    }

    public static void updaterMedlemFile(Medlem medlem) {
        System.out.println("Opdaterer medlem file: " + medlem.getNavn());
        String IDtxt = medlem.getId() + ".txt";
        Medlem m = getMedlemFromFile(new File(DatabaseController.DATABASE_PATH + IDtxt));
        deleteFile(IDtxt);
        m.setMedlemskab(m.getMedlemsskabsNr());
        saveMedlemAsFile(m);
    }


    // 2 Get a Medlem object from a file
    public static Medlem getMedlemFromFile(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String navn = "";
            int medlemskabNr = 0;
            LocalDate foedselsdato = null;
            int id = Integer.parseInt(file.getName().substring(0, file.getName().length() - 4));
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("navn: ")) {
                    navn = line.substring(6);
                }
                if (line.startsWith("medlemskabsNr: ")) {
                    medlemskabNr = Integer.parseInt(line.substring(15));
                }
                if (line.startsWith("foedselsdato: ")) {
                    foedselsdato = LocalDate.parse(line.substring(14));
                }
            }

            return new Medlem(navn, medlemskabNr, foedselsdato, id);

        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + file.getName());
            return null;
        } catch (IOException e) {
            System.err.println("Error reading from file: " + file.getName());
            return null;
        }
    }

    // Get a Medlem object from ID
    public static Medlem getMedlemByMobileNumber(int mobileNummber) {
        updateListOfFiles();
        try {
            for (File file : listOfFiles) {
                if (file.getName().equals(mobileNummber + ".txt")) {
                    getValues(file);
                    return new Medlem(navn, medlemskabNr, foedselsdato, id);
                }
            }
            return null;
        } catch (IOException e) {
            System.err.println("Error loading member");
            return null;
        }
    }

    private static ArrayList<Medlem> getMembersOfFiles() {
        ArrayList<Medlem> loadedMembers = new ArrayList<>();
        for (File file : DatabaseController.listOfFiles) {
            loadedMembers.add(DatabaseController.getMedlemFromFile(file));
        }
        return loadedMembers;
    }

    // Get the values from a file
    private static void getValues (File file) throws IOException {
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        // read the first line of the file
        while ((line = bufferedReader.readLine()) != null) {
            // if the line starts with "navn: " then return the rest of the line
            if (line.startsWith("navn: ")) {
                navn = line.substring(6);
            }
            if (line.startsWith("medlemskabsNr: ")) {
                medlemskabNr = Integer.parseInt(line.substring(15));
            }
            if (line.startsWith("foedselsdato: ")) {
                foedselsdato = LocalDate.parse(line.substring(14));
            }
        }
    }


    // Write the Traeningsresultat and Konkurrenceresultat to file
    private static void writeTraeningsTiderToFile(ArrayList<Traeningsresultat> resultater, FileWriter fileWriter) throws IOException {
        try {
            for (Traeningsresultat resultat : resultater) {
                Double tid = resultat.getTid();
                fileWriter.write(tid + ", ");
            }
            fileWriter.write("\n");
        } catch (NullPointerException e) {

        }
    }
    private static void writeKonkurrenceTiderToFile(ArrayList<Konkurrenceresultat> resultater, FileWriter fileWriter) throws IOException {
        try {
            for (Konkurrenceresultat resultat : resultater) {
                Double tid = resultat.getTid();
                fileWriter.write(tid + ", ");
            }
            fileWriter.write("\n");
        } catch (NullPointerException e) {

        }
    }



    private static void deleteFile(String iDtxt) {
        File file = new File(DatabaseController.DATABASE_PATH + iDtxt);
        if (file.delete()) {
            System.out.println();
        } else {
            System.out.println("Error deleting file: " + file.getName());
        }
    }

    public static void printDatabase () {
        System.out.println("Printer database...");
        try {
            loadFiles();
            for (File file : listOfFiles) {
                getValues(file);
                System.out.println("File: " + file.getName());
                System.out.println("--------------------");
                System.out.println("navn: " + navn);
                System.out.println("medlemskabsNr: " + medlemskabNr);
                System.out.println("foedselsdato: " + foedselsdato);
                System.out.println("--------------------");
                System.out.println();
            }
        } catch (IOException e) {
            System.err.println("Error loading files");
        }

    }
}