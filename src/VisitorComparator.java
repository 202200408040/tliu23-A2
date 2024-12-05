import java.util.Comparator;

public class VisitorComparator implements Comparator<Visitor> {

    /**
     * sorted in dictionary order (alphabetical order)
     * @param v1
     * @param v2
     * @return
     */
    @Override
    public int compare(Visitor v1, Visitor v2) {
        return v1.getName().compareTo(v2.getName());
    }
}