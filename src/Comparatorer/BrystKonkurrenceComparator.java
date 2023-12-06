package Comparatorer;
import Model.Konkurrenceresultat;
import Model.Medlem;
import java.util.ArrayList;
import java.util.Comparator;

public class BrystKonkurrenceComparator implements Comparator<Medlem> {

        public int compare(Medlem m1, Medlem m2) {
            if (m1.getBrystKonkurrence().get(0).getTid() > m1.getBrystKonkurrence().get(0).getTid())
                return 1;
            if (m1.getBrystKonkurrence().get(0).getTid() == m1.getBrystKonkurrence().get(0).getTid())
                return 0;
            else return -1;
        }
    }
