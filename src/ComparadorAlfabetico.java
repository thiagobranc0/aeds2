import java.util.Comparator;

public class ComparadorAlfabetico implements Comparator<String> {
    @Override
    public int compare(String s1, String s2) {
        return s1.compareTo(s2); // ordem crescente (A → Z)
    }
}
