import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

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

    /**
     * Converts the parameter list from a URI to a Map of Keys->Values
     * Ex: field1=value1&field2=value2 -> ['field1'->'value1', 'field2'->'value2']
     * @param query
     * @return
     */
    public static Map<String, String> queryToMap(String query){
        Map<String, String> result = new HashMap<>();
        // TODO: Make this below line better
        if(query == null) return result;
        for (String param : query.split("&")) {
            String pair[] = param.split("=");
            if (pair.length>1) {
                result.put(pair[0], pair[1]);
            }else{
                result.put(pair[0], "");
            }
        }
        return result;
    }
}
