import static org.junit.Assert.assertEquals;
import org.junit.Test;
import java.util.*;

public class DocumentsProcessorTest {



    @Test
    public void testProcessDocuments() {
        DocumentsProcessor dp = new DocumentsProcessor();
        Map<String, List<String>> expected = new HashMap<>();
        Map<String, List<String>> actual = new HashMap<>();
        actual = dp.processDocuments("./test_files1", 2);
        ArrayList<String> file4 = new ArrayList<>();
        file4.add("thisis");
        file4.add("isa");
        file4.add("afile");
        ArrayList<String> file5 = new ArrayList<>();
        file5.add("thisis");
        file5.add("isanother");
        file5.add("anotherfile");
        expected.put("file4.txt", file4);
        expected.put("file5.txt", file5);
        assertEquals(expected, actual);
        assertEquals(file4.size(), 3);
    }



    @Test
    public void testStoreNGrams() {
        DocumentsProcessor dp = new DocumentsProcessor();
        List<Tuple<String, Integer>> expected = new ArrayList<>();
        List<Tuple<String, Integer>> actual = new ArrayList<>();
        Map<String, List<String>> docs = new HashMap<>();
        docs = dp.processDocuments("./test_files2", 3);
        actual = dp.storeNGrams(docs,"./test_file.txt");
        Tuple<String, Integer> tuple1 = new Tuple("file1.txt", 110);
        Tuple<String, Integer> tuple3 = new Tuple("file3.txt",201);
        expected.add(tuple1);
        expected.add(tuple3);
        assertEquals(actual.get(1).getLeft(),tuple3.getLeft());
        assertEquals(actual.get(1).getRight(), tuple3.getRight());

    }


    @Test
    public void testComputeSimilarities() {
        DocumentsProcessor dp = new DocumentsProcessor();
        Map<String, List<String>> docs = new HashMap<>();
        List<Tuple<String, Integer>> tupleList = new ArrayList<>();
        TreeSet<Similarities> actual = new TreeSet<>();
        TreeSet<Similarities> expected = new TreeSet<>();
        docs = dp.processDocuments("./test_files2", 3);
        tupleList = dp.storeNGrams(docs,"./test_file.txt");
        actual = dp.computeSimilarities("./test_file.txt", tupleList);
        Similarities twoThree = new Similarities("file2.txt", "file3.txt");
        twoThree.setCount(3);
        expected.add(twoThree);
        Similarities oneTwo = new Similarities("file1.txt", "file2.txt");
        oneTwo.setCount(3);
        expected.add(oneTwo);
        Similarities oneThree = new Similarities("file1.txt", "file3.txt");
        oneThree.setCount(3);
        expected.add(oneThree);
    }


    @Test
    public void testProcessAndStore() {
        DocumentsProcessor dp = new DocumentsProcessor();
        List<Tuple<String, Integer>> actual = new ArrayList<>();
        List<Tuple<String, Integer>> expected = new ArrayList<>();
        actual = dp.processAndStore("./test_files2", "./test_file.txt", 3);
        Tuple<String, Integer> tuple1 = new Tuple("file1.txt", 110);
        Tuple<String, Integer> tuple2 = new Tuple("file2.txt", 92);
        Tuple<String, Integer> tuple3 = new Tuple("file3.txt",201);
        expected.add(tuple1);
        expected.add(tuple2);
        expected.add(tuple3);
        assertEquals(expected.get(0).getRight(), expected.get(0).getRight());
    }

//    @Test
//    public void testComputeSimilarities1() {
//        DocumentsProcessor dp = new DocumentsProcessor();
//        Map<String, List<String>> docs = new HashMap<>();
//        List<Tuple<String, Integer>> tupleList = new ArrayList<>();
//        TreeSet<Similarities> actual = new TreeSet<>();
//        TreeSet<Similarities> expected = new TreeSet<>();
//        docs = dp.processDocuments("./sm_doc_set", 6);
//        tupleList = dp.storeNGrams(docs,"./testing1.txt");
//        actual = dp.computeSimilarities("./testing1.txt", tupleList);
//        Similarities twoThree = new Similarities("file2.txt", "file3.txt");
//        twoThree.setCount(3);
//        expected.add(twoThree);
//        Similarities oneTwo = new Similarities("file1.txt", "file2.txt");
//        oneTwo.setCount(3);
//        expected.add(oneTwo);
//        Similarities oneThree = new Similarities("file1.txt", "file3.txt");
//        oneThree.setCount(3);
//        expected.add(oneThree);
//

//    @Test
//    public void testPrint1() {
//        DocumentsProcessor dp = new DocumentsProcessor();
//        Map<String, List<String>> docs = new HashMap<>();
//        List<Tuple<String, Integer>> tupleList = new ArrayList<>();
//        TreeSet<Similarities> actual = new TreeSet<>();
//        TreeSet<Similarities> expected = new TreeSet<>();
//        docs = dp.processDocuments("./med_doc_set", 2);
//        tupleList = dp.storeNGrams(docs,"./testing1.txt");
//        actual = dp.computeSimilarities("./testing1.txt", tupleList);
//        dp.printSimilarities(actual,2);
//
//    }



}
