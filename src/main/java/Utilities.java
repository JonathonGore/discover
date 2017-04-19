import java.io.InputStream;

/**
 * Created by jack on 2017-03-17.
 */
public class Utilities {
    /**
     * Generates a UUID
     */
    public static String generateUUID() {
        return "";
    }

    /**
     * Converts input stream to a string
     * @param is: The input stream to convert
     * @return
     */
    public static String toString(InputStream is) {
        try(java.util.Scanner s = new java.util.Scanner(is)) { return s.useDelimiter("\\A").hasNext() ? s.next() : ""; }
    }
}
