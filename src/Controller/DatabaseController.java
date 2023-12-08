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
    public static ArrayList<Medlem> loadedMembers = new ArrayList<>();
    private static Medlem processedMedlem;

    // Create the database directory if it does not exist
    static {
        new File(DATABASE_PATH).mkdirs();
    }


    // ---------------------------------------- Methods---------------------------------------------------
    public static void saveAndLoad () {
        saveArrToFileDatabase();
        load();
    }
    public static void load() {

        loadedMembers = getMedlemmerFraFiles(); // Load members from files

        if (loadedMembers != null && !loadedMembers.isEmpty()) {

            if (MedlemController.alleMedlemmer.isEmpty()) {
                MedlemController.alleMedlemmer = loadedMembers;
            } else {
                for (Medlem medlem : loadedMembers) {
                    MedlemController.opdaterMedlem(medlem);
                }
            }


        } else {
            System.err.println("No members loaded from files or error occurred."); // Error message
        }
    }



    public static void saveArrToFileDatabase() {

        List<Medlem> toRemove = new ArrayList<>();
        List<Medlem> toAdd = new ArrayList<>();

        for (Medlem medlem : MedlemController.getAlleMedlemmer()) {

            // check if file exists
            if (loadedMembers.contains(medlem)) {

                // check data difference between file and medlem
                // if difference, update file
                if (!checkDataDifference(medlem)) {
                    deleteFile(medlem.getId() + ".txt");
                    saveMedlemAsFile(medlem);
                }
                // if no difference, do nothing

            } else {
                System.out.println("File does not exist. Creating file: " + medlem.getId() + ".txt");
                saveMedlemAsFile(medlem);
            }
        }

        MedlemController.alleMedlemmer.removeAll(toRemove);
        MedlemController.alleMedlemmer.addAll(toAdd);
    }
    // Save a Medlem as a file
    public static void saveMedlemAsFile(Medlem m) {
        File file = new File(DatabaseController.DATABASE_PATH + m.getId() + ".txt");

        // Check if file exists and only overwrite if the same ID
        if (file.exists()) {
            Medlem existingMedlem = getMedlemFromFile(file);
            if (existingMedlem != null && m.getId() != existingMedlem.getId()) {
                System.err.println("Error saving member: " + m.getNavn() + " - ID does not match file name");
                return;
            }
        }

        try {
            // Overwrite the file if it exists, create a new one if it doesn't
            try (FileWriter fileWriter = new FileWriter(file, false)) {
                writeFile(m, fileWriter);
            }
        } catch (IOException e) {
            System.err.println("Error saving member: " + m.getNavn() + " - " + e.getMessage());
        }
    }




    // ----------------------------------------update Methods---------------------------------------------------
    private static ArrayList<File> getListOfFiles() {
        try {
            File directory = new File(DatabaseController.DATABASE_PATH);
            ArrayList<File> files = new ArrayList<>();
            System.out.println(directory.listFiles().length);
            // get all the files from a directory
            for (File file : directory.listFiles()) {
                if (file.getName().endsWith(".txt")) {
                    System.out.println("File found: " + file.getName());
                }
                files.add(file);
            }
            return files;
        } catch (NullPointerException e) {
            System.err.println("Error loading members");
            return null;
        }
    }


    public static void updaterMedlemFile(Medlem nytMedlem) {
        String IDtxt = nytMedlem.getId() + ".txt";
        File file = new File(DatabaseController.DATABASE_PATH + IDtxt);

        if (file.exists()) {
            Medlem oldMedlem = getMedlemFromFile(file);
            Medlem mergedMedlem = MedlemController.mergeMedlemmer(oldMedlem, nytMedlem);

            try (FileWriter fileWriter = new FileWriter(file, false)) {
                writeFile(mergedMedlem, fileWriter);
            } catch (IOException e) {
                System.err.println("Error editing file: " + e.getMessage());
            }
        } else {
            System.err.println("File not found: " + file.getName());
        }
    }


    // ----------------------------------------get Methods---------------------------------------------------
    private static ArrayList<Medlem> getMedlemmerFraFiles() {
        ArrayList<Medlem> loadedMembers = new ArrayList<>();
        ArrayList<File> listOfFiles = getListOfFiles();

        if (listOfFiles == null || listOfFiles.isEmpty()) {
            System.err.println("No files found or error in fetching files.");
            return new ArrayList<>(); // Return an empty list instead of null
        }

        for (File file : listOfFiles) {

            Medlem member = getMedlemProcess(file);
            if (member != null) {

                loadedMembers.add(member);
            } else {
                System.err.println("Error processing file: " + file.getName());
            }
        }
        return loadedMembers;
    }


    private static Medlem getMedlemProcess(File file) {
        Medlem m = getMedlemFromFile(file);

        return m;
    }

    private static Medlem getMedlemFromFile(File file) {
        if (file == null || !file.exists() || !file.canRead()) {
            System.err.println("Cannot read file: " + (file != null ? file.getName() : "null"));
            return null;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String navn = "";
            int medlemskabNr = 0;
            LocalDate foedselsdato = null;

            int id = Integer.parseInt(file.getName().substring(0, file.getName().length() - 4));
            String line;

            Medlem m = new Medlem(id);

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("navn: ")) {
                    navn = line.substring(6);
                    m.setNavn(navn);
                } else if (line.startsWith("medlemskabsNr: ")) {
                    medlemskabNr = Integer.parseInt(line.substring(15));
                    m.setMedlemskab(medlemskabNr);
                } else if (line.startsWith("foedselsdato: ")) {
                    foedselsdato = LocalDate.parse(line.substring(14));
                    m.setFoedselsdato(foedselsdato);
                } else if (line.startsWith("traening resultater: ")) {
                    processTrainingResults(reader, m);
                } else if (line.startsWith("konkurrence resultater: ")) {
                    processCompetitionResults(reader, m);
                }
            }

            processedMedlem = m;

            OpdateretMedlemMedResultaterFraFile(file, m);
            m = processedMedlem;

            return m;

        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + file.getName());
            return null;
        } catch (IOException e) {
            System.err.println("Error reading from file: " + file.getName());
            return null;
        }
    }

    public static Medlem getMedlemByID(int mobileNummber) {
        ArrayList<File> listOfFiles = getListOfFiles();
        try {
            for (File file : listOfFiles) {
                if (file.getName().startsWith(mobileNummber + "")) {
                    Medlem m = getMedlemFromFile(file);
                    OpdateretMedlemMedResultaterFraFile(file, m);
                    return processedMedlem;
                }
            }
            return null;
        } catch (NullPointerException e) {
            System.err.println("Error loading member");
            return null;
        }
    }




    //----------------------------------------read Methods---------------------------------------------------


    private static void OpdateretMedlemMedResultaterFraFile(File file, Medlem m) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            boolean isTrainingResultsSection = false;
            boolean isCompetitionResultsSection = false;
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("traening resultater: ")) {
                    isTrainingResultsSection = true;
                    isCompetitionResultsSection = false;
                    continue; // Skip the header line
                } else if (line.startsWith("konkurrence resultater: ")) {
                    isCompetitionResultsSection = true;
                    isTrainingResultsSection = false;
                    continue; // Skip the header line
                }

                if (isTrainingResultsSection) {
                    processTrainingResultsLine(line, m);
                } else if (isCompetitionResultsSection) {
                    processCompetitionResultsLine(line, m);
                }
            }

        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
    }

    private static void processTrainingResults(BufferedReader reader, Medlem m) {
        boolean isTrainingResultsSection = true;
        boolean isCompetitionResultsSection = false;
        String line;

        try {
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("konkurrence resultater: ")) {
                    isTrainingResultsSection = false;
                    isCompetitionResultsSection = true;
                    continue; // Skip the header line
                }

                if (isTrainingResultsSection) {
                    processTrainingResultsLine(line, m);
                } else if (isCompetitionResultsSection) {
                    processCompetitionResultsLine(line, m);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
    }

    private static void processCompetitionResults(BufferedReader reader, Medlem m) {
        boolean isTrainingResultsSection = false;
        boolean isCompetitionResultsSection = true;
        String line;

        try {
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("traening resultater: ")) {
                    isTrainingResultsSection = true;
                    isCompetitionResultsSection = false;
                    continue; // Skip the header line
                } else if (line.startsWith("konkurrence resultater: ")) {
                    isCompetitionResultsSection = true;
                    isTrainingResultsSection = false;
                    continue; // Skip the header line
                }

                if (isTrainingResultsSection) {
                    processTrainingResultsLine(line, m);
                } else if (isCompetitionResultsSection) {
                    processCompetitionResultsLine(line, m);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
    }
    private static void processTrainingResultsLine(String line, Medlem m) {
        int colonIndex = line.indexOf(":");
        if (colonIndex != -1) {
            String disciplin = line.substring(0, colonIndex);
            String resultString = line.substring(colonIndex + 1).trim();
            processResultString(resultString, disciplin, m, true); // true for training results
        }
    }

    private static void processCompetitionResultsLine(String line, Medlem m) {
        int colonIndex = line.indexOf(":");
        if (colonIndex != -1) {
            String disciplin = line.substring(0, colonIndex);
            String resultString = line.substring(colonIndex + 1).trim();
            processResultString(resultString, disciplin, m, false); // false for competition results
        }
    }

    private static void processResultString(String resultString, String disciplin, Medlem m, boolean isTraining) {
        if (!resultString.isEmpty()) {
            String[] results = resultString.split(", ");
            for (String result : results) {

                if (result.isEmpty()) {
                    continue;
                }
                try {

                    int openParenIndex = result.indexOf("(");
                    int closeParenIndex = result.indexOf(")");
                    if (isTraining) {
                        // process træning result
                        if (openParenIndex != -1 && closeParenIndex != -1) {
                            try {
                                Double tid = Double.parseDouble(result.substring(0, openParenIndex));
                                LocalDate dato = LocalDate.parse(result.substring(openParenIndex + 1, closeParenIndex));
                                Traeningsresultat traeningsresultat = new Traeningsresultat(tid, dato);
                                m.addIfNewTraeningsresultat(traeningsresultat, disciplin);
                            } catch (NumberFormatException e) {
                                System.err.println("Error parsing Traeningsresultat from file: " + e.getMessage());
                            }
                        }
                    } else {
                        // process konkurrence result
                        int plusIndex = result.indexOf("+");

                        if (openParenIndex != -1 && plusIndex != -1 && closeParenIndex != -1) {
                            Double tid = Double.parseDouble(result.substring(0, openParenIndex));
                            String staevne = result.substring(openParenIndex + 1, plusIndex);
                            int placering = Integer.parseInt(result.substring(plusIndex + 1, closeParenIndex));
                            Konkurrenceresultat konkurrenceresultat = new Konkurrenceresultat(tid, staevne, placering);
                            m.addIfNewKonkurrenceresultat(konkurrenceresultat, disciplin);
                        }
                    }
                   setProcessedMedlem(m);
                } catch (NumberFormatException e) {
                    System.err.println("Error parsing result from file: " + e.getMessage());
                }
            }
        }
    }

    private static void setProcessedMedlem(Medlem nytProcessedMedlem) {
        processedMedlem = nytProcessedMedlem;
    }


    //----------------------------------------write Methods---------------------------------------------------



    private static void writeFile(Medlem m, FileWriter fileWriter) throws IOException {

        // write file with structure to preserve all Medlem instance variables as strings
        fileWriter.write("navn: "+ m.getNavn() + "\n");
        fileWriter.write("medlemskabsNr: "+ m.getMedlemsskabsNr() + "\n");
        fileWriter.write("alder: "+ m.getAlder() + "\n");
        fileWriter.write("foedselsdato: "+ m.getFoedselsdato() + "\n");

        // document Traening and Konkurrence results of Medlem
        fileWriter.write("traening resultater: \n");
        fileWriter.write("Butterfly: ");
        writeTraeningsTiderToFile(m.getButterflyTraening(), fileWriter);
        fileWriter.write("Crawl: ");
        writeTraeningsTiderToFile(m.getCrawlTraening(), fileWriter);
        fileWriter.write("Bryst: ");
        writeTraeningsTiderToFile(m.getBrystTraening(), fileWriter);

        fileWriter.write("konkurrence resultater: \n");
        fileWriter.write("Butterfly: ");
        writeKonkurrenceTiderToFile(m.getButterflyKonkurrence(), fileWriter);
        fileWriter.write("Crawl: ");
        writeKonkurrenceTiderToFile(m.getCrawlKonkurrence(), fileWriter);
        fileWriter.write("Bryst: ");
        writeKonkurrenceTiderToFile(m.getBrystKonkurrence(), fileWriter);
        fileWriter.close();
    }

    // Write the Traeningsresultat and Konkurrenceresultat to file
    private static void writeTraeningsTiderToFile(ArrayList<Traeningsresultat> resultater, FileWriter fileWriter) throws IOException {
        try {
            for (Traeningsresultat resultat : resultater) {
                Double tid = resultat.getTid();
                String datoStr = resultat.getTraeningsDato().toString();
                fileWriter.write(tid + "("+ datoStr +")"+ ", ");
            }
            fileWriter.write("\n");
        } catch (NullPointerException e) {

        }
    }
    private static void writeKonkurrenceTiderToFile(ArrayList<Konkurrenceresultat> resultater, FileWriter fileWriter) throws IOException {
        try {
            for (Konkurrenceresultat resultat : resultater) {
                Double tid = resultat.getTid();
                String staevne = resultat.getStaevne();
                int placering = resultat.getPlacering();

                fileWriter.write(tid + "("+ staevne+"+"+placering+ ")"+ ", ");
            }
            fileWriter.write("\n");
        } catch (NullPointerException e) {

        }
    }

    public static void setMedlemskabsNr(int id, int nytMedlemskabNr) {

        Medlem m = getMedlemFromFile(getFile(id));
        m.setMedlemskab(nytMedlemskabNr);
        editFile(m, getFile(id));
    }



    private static boolean checkDataDifference(Medlem medlem) {
        Medlem medlemFromFile = getMedlemFromFile(new File(DatabaseController.DATABASE_PATH + medlem.getId() + ".txt"));
        boolean same = true;
        if (!medlem.getNavn().equals(medlemFromFile.getNavn())) {
            same = false;
        }
        if (medlem.getMedlemsskabsNr() != medlemFromFile.getMedlemsskabsNr()) {
            same = false;
        }
        if (medlem.getAlder() != medlemFromFile.getAlder()) {
            same = false;
        }
        if (!medlem.getFoedselsdato().equals(medlemFromFile.getFoedselsdato())) {
            same = false;
        }
        if (!medlem.getButterflyTraening().equals(medlemFromFile.getButterflyTraening())) {
            same = false;
        }
        if (!medlem.getCrawlTraening().equals(medlemFromFile.getCrawlTraening())) {
            same = false;
        }
        if (!medlem.getBrystTraening().equals(medlemFromFile.getBrystTraening())) {
            same = false;
        }
        if (!medlem.getButterflyKonkurrence().equals(medlemFromFile.getButterflyKonkurrence())) {
            same = false;
        }
        if (!medlem.getCrawlKonkurrence().equals(medlemFromFile.getCrawlKonkurrence())) {
            same = false;
        }
        if (!medlem.getBrystKonkurrence().equals(medlemFromFile.getBrystKonkurrence())) {
            same = false;
        }
        return same;
    }

    //----------------------------------------file Methods---------------------------------------------------
    private static void deleteFile(String iDtxt) {
        File file = new File(DatabaseController.DATABASE_PATH + iDtxt);
        if (file.delete()) {
            System.out.println();
        } else {
            System.out.println("Error deleting file: " + file.getName());
        }
    }

    private static void editFile(Medlem m, File file) {
        try {
            FileWriter fileWriter = new FileWriter(file, false);
            writeFile(m, fileWriter);
        } catch (IOException e) {
            System.err.println("Error editing file");
        }
    }

    private static File getFile(int id) {

        try {
            for (File file : listOfFiles) {
                if (file.getName().startsWith(id + "")) {
                    return file;
                }
            }
            return null;
        } catch (NullPointerException e) {
            System.err.println("Error loading member");
            return null;
        }

    }
}