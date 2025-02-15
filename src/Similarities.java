/**
 * @author ericfouh
 */
public class Similarities implements Comparable<Similarities> {
    /**
     * 
     */
    private String file1;
    private String file2;
    private int    count;


    /**
     * @param file1
     * @param file2
     */
    public Similarities(String file1, String file2) {
        this.file1 = file1;
        this.file2 = file2;
        this.setCount(0);
    }


    /**
     * @return the file1
     */
    public String getFile1() {
        return file1;
    }


    /**
     * @return the file2
     */
    public String getFile2() {
        return file2;
    }


    /**
     * @return the count
     */
    public int getCount() {
        return count;
    }


    /**
     * @param count the count to set
     */
    public void setCount(int count) {
        this.count = count;
    }


    @Override
    public int compareTo(Similarities o) {
        //TODO

//        if (this.getFile1().equals(o.getFile2()) && this.getFile2().equals(o.getFile1())
//                || this.getFile1().equals(o.getFile1()) && this.getFile2().equals(o.getFile2())) {
//            return 0;
//        } else {
//            if (this.count == o.count) {
//                int result = this.getFile1().compareTo(o.getFile1());
//                if (result == 0) {
//                    return this.getFile2().compareTo(o.getFile2());
//                } else {
//                    return result;
//                }
//            } else {
//                return this.count - o.count;
//            }
//        }
        int countDifference = o.count - this.count;
        int result1 = this.getFile1().compareTo(o.getFile1());
        int result2 = this.getFile2().compareTo(o.getFile2());
//
//        if (this.getFile1().equals(o.getFile1()) && this.getFile2().equals(o.getFile2())) {
//            if (o.count != 0) {
//                return 0;
//            }
//        }
//        if (countDifference != 0){
//            return countDifference;
//        }
//
//        if (result1 != 0) {
//            return result1;
//        } else {
//            return result2;
//        }
//    }
//        if (this.getFile1().equals(o.getFile1()) && this.getFile2().equals(o.getFile2())) {
//            return 0;
//        }

        if (result1 != 0) {
            return result1;
        } else {
            if (result2 != 0) {
                return result2;
            } else {
                return countDifference;
            }
        }
    }

}
