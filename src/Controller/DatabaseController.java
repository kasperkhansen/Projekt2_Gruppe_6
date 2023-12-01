package Controller;

import Model.Medlem;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DatabaseController {

    public static final String DATABASE_PATH = "src/Database/";
    private static File[] listOfFiles;

    // instance variables of medlem
    private static String navn;
    private static int medlemskabNr;
    private static int alder;
    private static int id;
    private static LocalDate foedselsdato;

    static {
        // Create the database directory if it does not exist
        new File(DATABASE_PATH).mkdirs();
    }

    // Save alleMedlemmer arraylist of MedlemController to files for each member
    public static void saveArrToFileDatabase() {
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
    public static void loadFilesToArr() {
        try {
            List<Medlem> loadedMembers = loadFiles(); // Load members from files
            if (!loadedMembers.isEmpty()) {
                MedlemController.alleMedlemmer.clear(); // Clear list only if files are loaded successfully
                MedlemController.alleMedlemmer.addAll(loadedMembers);
            }
        } catch (IOException e) {
            System.err.println("Error loading files: " + e.getMessage());
        }
    }


    private static List<Medlem> loadFiles() throws IOException {
        updateListOfFiles();
        List<Medlem> loadedMembers = new ArrayList<>();
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



    // Get a Medlem object from a file
    private static Medlem getMedlemFromFile(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String navn = "";
            int medlemskabNr = 0;
            LocalDate foedselsdato = null;
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
    public static Medlem getMedlemByID(int ID) throws IOException {
        getListOfFiles();
        for (File file : listOfFiles) {
            if (file.getName().equals(ID + ".txt")) {
                getValues(file);
                return new Medlem(navn, medlemskabNr, foedselsdato, id);
            }
        }
        return null;
    }

    // Save a Medlem as a file
    public static void saveMedlemAsFile(Medlem m, String fileNameMedlemID) {

        try {
            File file = new File(fileNameMedlemID);

            // check if file exists
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
                FileWriter fileWriter = new FileWriter(file);

                // write file with structure to preserve all Medlem instance variables as strings
                fileWriter.write("navn: "+m.getNavn() + "\n");
                fileWriter.write("medlemskabsNr: "+m.getMedlemskabNr() + "\n");
                fileWriter.write("alder: "+m.getAlder() + "\n");
                fileWriter.write("foedselsdato: "+m.getFoedselsdato() + "\n");

                fileWriter.close();
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.err.println("Error saving member: " + m.getNavn());
        }
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



    // Get list of files in the database directory
    private static File[] getListOfFiles() {
        File folder = new File(DATABASE_PATH);
        return folder.listFiles();
    }
    // Update the list of files
    private static void updateListOfFiles() {
        listOfFiles = getListOfFiles();
    }

    private static File getFileBy(int id) {
        getListOfFiles();
        for (File file : listOfFiles) {
            if (file.getName().equals(id + ".txt")) {
                return file;
            }
        }
        return null;
    }




    // print methods
    public static void printDatabase () {
        System.out.println("DatabaseController.printDatabase");
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

    public static void printMedlemByID(int ID) {
        try {
            File file = getFileBy(ID);
            getValues(file);
            System.out.println("File: " + file.getName());
            System.out.println("--------------------");
            System.out.println("navn: " + navn);
            System.out.println("medlemskabsNr: " + medlemskabNr);
            System.out.println("foedselsdato: " + foedselsdato);
            System.out.println("--------------------");
            System.out.println();
        } catch (IOException e) {
            System.err.println("Error loading member");
        }
    }

}