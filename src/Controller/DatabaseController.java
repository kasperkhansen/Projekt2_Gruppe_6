package Controller;

// class for saving alleMedlemmer arraylist of MedlemController to files for each member

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class DatabaseController {

    // method for saving alleMedlemmer arraylist of MedlemController to files for each member
    public static void saveDatabase() {
        for (int i = 0; i < MedlemController.alleMedlemmer.size(); i++) {
            MedlemController.alleMedlemmer.get(i).saveMedlem();
        }
    }

    // method for loading alleMedlemmer arraylist of MedlemController from files for each member
    public static void loadDatabase() {
        MedlemController.alleMedlemmer.clear();
        MedlemController.getAlleMedlemmer();
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




}
