import java.util.Comparator;

public class SimilaritiesComparator implements Comparator<Similarities> {

    @Override
    public int compare(Similarities o1, Similarities o2) {
        return o2.getCount() - o1.getCount();
    }
}
