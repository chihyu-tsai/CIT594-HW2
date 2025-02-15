import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

public class tes {

    public static void main(String[] args) throws FileNotFoundException {
        Reader r = new FileReader("/Users/chihyutsai/cit594/hw2/src/test.txt");
        DocumentIterator d = new DocumentIterator(r, 4);

        System.out.println(d.next());
        System.out.println(d.next());
        System.out.println(d.next());
        System.out.println(d.next());

    }
}
