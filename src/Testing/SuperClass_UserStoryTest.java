package Testing;




public class SuperClass_UserStoryTest {

    public static void printDoDTest() {
        System.out.println("DoD til User Stories");
        System.out.println("\t- Unit tests Bestået");
        System.out.println("\t\t* Til hver klasse laver vi en test klasse");
        System.out.println("\t\t* White Box tests");
        System.out.println("\t- Kode gennemgået");
        System.out.println("\t\t* Koden bliver kigget igennem af en anden udvikler, som kigger både kode og tests igennem for at tjekke om koden bliver testet rigtigt og om funktionaliteten er der.");
        System.out.println("\t\t* En checkliste bliver lavet som bliver kørt igennem under code review. (check for GRASP: Creator, Information Expert, High Cohesion og Low Coupling)");
        System.out.println("\t- Acceptance kriterier mødt");
        System.out.println("\t\t* Opfylder funktionaliteten de krav som User Storyen stiller.");
        System.out.println("\t- Funktionelle tests bestået");
        System.out.println("\t\t* Black Box tests (kun funktionalitet)");
        System.out.println("\t- Non-Functional krav mødt");
        System.out.println("\t\t* Tjek FURPS");
        System.out.println("\t\t* Bliver data opdateret (bliver ArrayList medlemsliste opdateret efter hver ændring)");
        System.out.println("\t- Product Owner accepterer User Story");
        System.out.println("\t\t* Product Owner tjekker om DoD gennem");
        System.out.println();
        System.out.println();
    }

    static class Test {

        public Test () {
            printDoDTest();
        }


    }
}
