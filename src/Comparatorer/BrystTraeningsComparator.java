package Comparatorer;
import Model.Medlem;
import java.util.Comparator;
public class BrystTraeningsComparator implements Comparator<Medlem> {

        public int compare(Medlem m1, Medlem m2) {
            if (m1.getBrystTraening().get(0).getTid() > m1.getBrystTraening().get(0).getTid())
                return 1;
            if (m1.getBrystTraening().get(0).getTid() == m1.getBrystTraening().get(0).getTid())
                return 0;
            else return -1;
        }
    }