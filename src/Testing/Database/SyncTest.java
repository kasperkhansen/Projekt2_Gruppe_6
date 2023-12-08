package Testing.Database;

import Model.Medlem;

import static Controller.MedlemController.getMedlemMedId;

public class SyncTest {

    public static void test(Medlem m, Medlem oldMedlem) {
        System.out.println("newMedlem: " + m.toString());
        System.out.println(" resultater: ");
        System.out.println(" 1 konkurrence resultater");
        m.printKonkurrenceresultater();
        System.out.println();
        System.out.println(" 2 trænings resultater");
        m.printTraeningsresultater();
        System.out.println();
        System.out.println("oldMedlem:" + getMedlemMedId(oldMedlem.getId()).toString());
        System.out.println(" resultater: ");
        System.out.println(" 1 konkurrence resultater");
        getMedlemMedId(oldMedlem.getId()).printKonkurrenceresultater();
        System.out.println();
        System.out.println(" 2 trænings resultater");
        getMedlemMedId(oldMedlem.getId()).printTraeningsresultater();
    }
}
