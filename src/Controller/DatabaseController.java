package Controller;

import Model.Medlem;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class DatabaseController {

    public static final String DATABASE_PATH = "src/Database/";
    private static File[] listOfFiles;

    // instance variables of medlem
    private static String navn;
    private static int medlemskabNr;
    private static int alder;
    private static LocalDate foedselsdato;

    static {
        // Create the database directory if it does not exist
        new File(DATABASE_PATH).mkdirs();
    }

    // Save alleMedlemmer arraylist of MedlemController to files for each member
    public static void saveDatabase() {
        for (Medlem medlem : MedlemController.alleMedlemmer) {
            try {
                File file = new File(DatabaseController.DATABASE_PATH + medlem.getId() + ".txt");
                if (file.createNewFile()) {
                    System.out.println("File created: " + medlem.getId()+ ".txt");
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
    public static void loadDatabase() throws IOException {
        MedlemController.alleMedlemmer.clear();

        try {
            loadFiles();
        } catch (IOException e) {
            System.err.println("Error loading files");
        }

    }

    private static void loadFiles() throws IOException {
        File folder = new File(DATABASE_PATH);
        File[] listOfFiles = folder.listFiles();

        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                if (file.isFile()) {

                    Medlem loadedMedlem = loadFileToMedlem(file);
                    if (loadedMedlem != null && !MedlemController.alleMedlemmer.contains(loadedMedlem)) {
                        MedlemController.alleMedlemmer.add(loadedMedlem);
                    }
                }
            }
        }
    }

    private static Medlem loadFileToMedlem(File file) throws IOException {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line;
            String navn = "";
            int medlemskabNr = 0;
            int alder = 0;
            LocalDate foedselsdato = null;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("navn: ")) {
                    navn = line.substring(6);
                } else if (line.startsWith("medlemskabsNr: ")) {
                    medlemskabNr = Integer.parseInt(line.substring(15));
                } else if (line.startsWith("alder: ")) {
                    alder = Integer.parseInt(line.substring(7));
                } else if (line.startsWith("foedselsdato: ")) {
                    foedselsdato = LocalDate.parse(line.substring(14));
                }
            }

            return new Medlem(navn, medlemskabNr, alder, foedselsdato);
        } finally {
            if (reader != null) {
                reader.close();
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
    public static void saveMedlemAsFile(Medlem m, String fileNameMedlemID) throws IOException {

        File file = new File(fileNameMedlemID);
        FileWriter fileWriter = new FileWriter(file);

        // write file with structure to preserve all Medlem instance variables as strings
        fileWriter.write("navn: "+m.getNavn() + "\n");
        fileWriter.write("medlemskabsNr: "+m.getMedlemskabNr() + "\n");
        fileWriter.write("alder: "+m.getAlder() + "\n");
        fileWriter.write("foedselsdato: "+m.getFoedselsdato() + "\n");

        fileWriter.close();

    }

    // Load a Medlem object from a file
    public static Medlem loadFileToMedlemByMedlemID(int ID) throws IOException {
        getListOfFiles();
        for (File file : listOfFiles) {
            if (file.getName().equals(ID + ".txt")) {
                getValues(file);
                return new Medlem(navn, medlemskabNr, alder, foedselsdato);
            }
        }
        return null;
    }

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
            if (line.startsWith("alder: ")) {
                alder = Integer.parseInt(line.substring(7));
            }
            if (line.startsWith("foedselsdato: ")) {
                foedselsdato = LocalDate.parse(line.substring(14));
            }
        }
    }
}
