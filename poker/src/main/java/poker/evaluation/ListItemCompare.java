package poker.evaluation;

import java.util.Comparator;
import java.util.List;

public class ListItemCompare<T extends Comparable<T>> implements Comparator<List<T>> {

    @Override
    public int compare(List<T> l1, List<T> l2) {
        int minLen = Math.min(l1.size(), l2.size());

        for (int i = 0; i < minLen; i++) {
            int comparison = l1.get(i).compareTo(l2.get(i));
            if (comparison != 0) {
                return comparison;
            }
        }

        return Integer.compare(l1.size(), l2.size());
    }
}
