import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DocumentIterator implements Iterator<String> {

    private Reader r;
    private int    c = -1;
    // add a new field n
    private int n;
    // add a tempBuffer
    private String[] tempBuffer;

    public DocumentIterator(Reader r, int n) {
        this.r = r;
        this.n = n;
        this.tempBuffer = new String[n];
        skipNonLetters();
    }


    private void skipNonLetters() {
        try {
            this.c = this.r.read();
            while (!Character.isLetter(this.c) && this.c != -1) {
                this.c = this.r.read();
            }
        } catch (IOException e) {
            this.c = -1;
        }
    }


    @Override
    public boolean hasNext() {
        return (c != -1);
    }


    @Override
    public String next() {

        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        StringBuilder resultString = new StringBuilder();
        StringBuilder lastElem = new StringBuilder();

        try {
            if (tempBuffer[0] == null) {
                for (int i = 0; i < n; i++) {
                    StringBuilder answer = new StringBuilder();
                    while (Character.isLetter(this.c)) {
                        answer.append(Character.toLowerCase((char)this.c));
                        this.c = this.r.read();
                    }
                    skipNonLetters();
                    tempBuffer[i] = answer.toString();
                }
            } else {
                for (int i = 1; i < n; i++) {
                    tempBuffer[i - 1] = tempBuffer[i];
                }
                while (Character.isLetter(this.c)) {
                    lastElem.append((char)this.c);
                    this.c = this.r.read();
                }
                skipNonLetters();
                tempBuffer[n - 1] = lastElem.toString();
            }
        } catch (IOException e) {
            throw new NoSuchElementException();
        }

        for (int i = 0; i < n; i++) {
            resultString.append(tempBuffer[i]);
        }
        return resultString.toString().toLowerCase();
    }

}
