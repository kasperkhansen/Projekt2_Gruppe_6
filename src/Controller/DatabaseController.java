package Controller;

// class for saving alleMedlemmer arraylist of MedlemController to files for each member

import Model.Medlem;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class DatabaseController {

    ArrayList<File> files = new ArrayList<>();
    static File[] listOfFiles;

    private static File currentFile;

    // method for saving alleMedlemmer arraylist of MedlemController to files for each member
    public static void saveDatabase() {
        for (int i = 0; i < MedlemController.alleMedlemmer.size(); i++) {
            MedlemController.alleMedlemmer.get(i).saveMedlem();
        }
        updateListOfFiles();
    }



    // method for loading alleMedlemmer arraylist of MedlemController from files for each member
    public static void loadDatabase() {

        MedlemController.alleMedlemmer.clear();
        listOfFiles = getListOfFiles();

        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                if (file.isFile()) {
                    MedlemController.alleMedlemmer.add(loadFileToMedlem(file.getName()));
                }
            }
        }

    }

    // listOfFiles methods
    private static File[] getListOfFiles() {
        File folder = new File("src/Database");
        return listOfFiles = folder.listFiles();
    }
    private static void updateListOfFiles() {
        listOfFiles = getListOfFiles();
    }

    // get Medlem from file
    public static Medlem getMedlemFromFile(String fileName) {
        return loadFileToMedlem(fileName);
    }

    public static Medlem loadFileToMedlem(String fileName) {
        try {
            for (File file : listOfFiles) {
                if (file.getName().equals(fileName)) {
                    currentFile = file;
                    return new Medlem(getMedlemName(), getMedlemMedlemskabNr(), getMedlemAlder(), getMedlemFoedselsdato());
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();

        } return null;

    }



    public static void saveObjectAsFile(Object object, String fileName) {
        try {
            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(object);
            objectOut.close();
            System.out.println("The Medlem  was succesfully saved in Database");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // get Medlem values from file
    public static String getMedlemName() {
        return
    }
    public static int getMedlemMedlemskabNr() {
        return
    }
    public static int getMedlemAlder() {
        return
    }
    public static String getMedlemFoedselsdato() {
        return
    }





}
