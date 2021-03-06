import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by jack on 2017-03-17.
 */
public class Utilities {
    /**
     * Generates a UUID
     * @return the generated UUID
     */
    public static String generateUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * Gets the current time since EPOCH in milliseconds.
     * @return the time in milliseconds
     */
    public static Long getCurrentTime() {
        return System.currentTimeMillis();
    }

    /**
     * Converts input stream to a string
     * @param is: The input stream to convert
     * @return
     */
    public static String toString(InputStream is) {
        try(java.util.Scanner s = new java.util.Scanner(is)) { return s.useDelimiter("\\A").hasNext() ? s.next() : ""; }
    }

    /**
     * TODO: TEST
     * Consumes a list of strings where each string is a JSON object and produces a single
     * string that represents a JSON array of the objects.
     * @param objs
     * @return
     */
    public static String objectsToJSONArray(List<String> objs) {
        StringBuilder sb = new StringBuilder("[");

        for(int i = 0; i < objs.size(); ++i) {
            if(i > 0) {
                sb.append(", ");
            }
            sb.append(objs.get(i));
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * Determines if a given String s is empty: NULL or of length 0
     * @param s the String to test
     * @return True is the String is empty false otherwise
     */
    public static boolean isEmpty(String s) { return s == null || s.isEmpty(); }

    /**
     * Converts the parameter list from a URI to a Map of Keys->Values
     * Ex: field1=value1&field2=value2 -> ['field1'->'value1', 'field2'->'value2']
     * @param query
     * @return
     */
    public static Map<String, String> queryToMap(String query){
        Map<String, String> result = new HashMap<>();

        // TODO: Make this below line better
        if(Utilities.isEmpty(query)) return result;

        for (String param : query.split("&")) {
            String pair[] = param.split("=");
            // Only if we split the String into two non-empty keys/values do we put it in the map
            if (pair.length > 1 && !isEmpty(pair[0]) && !isEmpty(pair[1])) {
                result.put(pair[0], pair[1]);
            }
        }
        return result;
    }
}
