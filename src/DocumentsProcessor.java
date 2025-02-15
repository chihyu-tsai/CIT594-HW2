import java.io.*;
import java.util.*;

public class DocumentsProcessor implements IDocumentsProcessor {

    @Override
    public Map<String, List<String>> processDocuments(String directoryPath, int n) {
        Map<String, List<String>> result = new HashMap<>();
        File directory = new File(directoryPath);
        File[] files = null;
        try {
            if (directory.exists() && directory.isDirectory()) {
                files = directory.listFiles();
            }
            for (int i = 0; i < files.length; i++) {
                ArrayList<String> tempList = new ArrayList<>();
                FileInputStream fis = new FileInputStream(files[i]);
                BufferedInputStream bis = new BufferedInputStream(fis);
                BufferedReader br = new BufferedReader(new InputStreamReader(bis));
                DocumentIterator iter = new DocumentIterator(br, n);
                while (iter.hasNext()) {
                    tempList.add(iter.next());
                }
                result.put(files[i].getName(), tempList);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Tuple<String, Integer>> storeNGrams(Map<String, List<String>> docs,
                                                    String nwordFilePath) {
        List<Tuple<String, Integer>> outputList = new ArrayList<>();
        try {
            // first part - write all the nGrams
            BufferedWriter bw = new BufferedWriter(new FileWriter(nwordFilePath));
            for (String key : docs.keySet()) {
                for (String word : docs.get(key)) {
                    bw.write(word + " ");
                }
            }
            bw.close();
            // the second part that will generate the output
            for (String key : docs.keySet()) {
                List<String> valueList = docs.get(key);
                int nGramCount = 0;
                int characterCount = 0;
                int totalCount = 0;
                for (int i = 0; i < valueList.size(); i++) {
                    nGramCount += 1;
                    characterCount += valueList.get(i).length();
                }
                totalCount = nGramCount + characterCount;
                Tuple<String, Integer> tempTuple = new Tuple<>(key, totalCount);
                outputList.add(tempTuple);
            }
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        return outputList;
    }

    @Override
    public TreeSet<Similarities> computeSimilarities(String nwordFilePath,
                                                     List<Tuple<String, Integer>> fileindex) {
        TreeSet<Similarities> outcomeSet = new TreeSet<>();
        TreeSet<Similarities> new0utcomeSet = new TreeSet<>();
        Map<String, Set<String>> tempMap = new HashMap<>();

        try {
            /* place everything into a map - having nGram as key and the
            files associated with that nGrams as value
             */
            FileReader fr = new FileReader(nwordFilePath);
            // add the buffer reader
            BufferedReader br = new BufferedReader(fr);
            // make similarity object
            for (int i = 0; i < fileindex.size(); i++) {
                for (int j = i + 1; j < fileindex.size(); j++) {
                    String firstFile = fileindex.get(i).getLeft();
                    String secondFile = fileindex.get(j).getLeft();
                    if (firstFile.compareTo(secondFile) < 0) {
                        Similarities sim = new Similarities(firstFile, secondFile);
                        outcomeSet.add(sim);
                    } else {
                        Similarities sim = new Similarities(secondFile, firstFile);
                        outcomeSet.add(sim);
                    }
                }
            }
            for (int i = 0; i < fileindex.size(); i++) {
                String name = fileindex.get(i).getLeft();
                Integer bytes = fileindex.get(i).getRight();
                char[] charArray = new char[bytes];
                br.read(charArray, 0, bytes);
                String keyString = String.valueOf(charArray);
                String[] nGrams = keyString.split(" ");
                Set<String> keys = new HashSet<>(Arrays.asList(nGrams));
                for (String s: keys) {
                    if (!tempMap.containsKey(s)) {
                        Set<String> strArr = new HashSet<>();
                        strArr.add(name);
                        tempMap.put(s, strArr);
                    } else {
                        Set<String> alreadyInSet = tempMap.get(s);
                        Similarities sim;
                        for (String file: alreadyInSet) {
                            if (!file.equals(name)) {
                                if (file.compareTo(name) <= 0) {
                                    sim = new Similarities(file, name);
                                } else {
                                    sim = new Similarities(name, file);
                                }
                                Similarities exist = outcomeSet.floor(sim);
                                if (exist == null) {
                                    sim.setCount(1);
                                    outcomeSet.add(sim);
                                } else {
                                    exist.setCount(exist.getCount() + 1);
                                }
                            }
                        }
                        alreadyInSet.add(name);
                    }
                }
            }
            for (Similarities s: outcomeSet) {
                if (s.getCount() != 0) {
                    new0utcomeSet.add(s);
                }
            }
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        return new0utcomeSet;
    }

    @Override
    public void printSimilarities(TreeSet<Similarities> sims, int threshold) {
        TreeSet<Similarities> newSims = new TreeSet<>(new SimilaritiesComparator());
        for (Similarities s : sims) {
            if (s.getCount() > threshold) {
                newSims.add(s);
            }
        }
        for (Similarities s2: newSims) {
            System.out.println("[" + s2.getFile1() + " and " + s2.getFile2() + "]" +
                    " count is " + s2.getCount());
        }

    }

    public List<Tuple<String, Integer>> processAndStore(String directoryPath,
                                                        String sequenceFile, int n) {
        List<Tuple<String, Integer>> finalList = new ArrayList<>();
        File dir = new File(directoryPath);
        File[] files = null;

        try {
            if (dir.exists() && dir.isDirectory()) {
                files = dir.listFiles();
            }
            BufferedWriter bw = new BufferedWriter(new FileWriter(sequenceFile));
            for (int i = 0; i < files.length; i++) {
                ArrayList<String> tempList = new ArrayList<>();
                FileInputStream fis = new FileInputStream(files[i]);
                BufferedInputStream bis = new BufferedInputStream(fis);
                BufferedReader br = new BufferedReader(new InputStreamReader(bis));
                DocumentIterator iter = new DocumentIterator(br, n);
                while (iter.hasNext()) {
                    tempList.add(iter.next());
                }
                int count = 0;
                for (String item : tempList) {
                    bw.write(item + " ");
                    count += item.length();
                    count += 1;
                }
                Tuple<String, Integer> tup = new Tuple(files[i].getName(), count);
                finalList.add(tup);
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();

        }
        return finalList;
    }
}
