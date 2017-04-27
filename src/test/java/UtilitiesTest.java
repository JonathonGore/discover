import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by jack on 2017-04-26.
 */
public class UtilitiesTest {
    private final String EMPTY_STRING = "";

    @Test
    public void queryToMapEmptyTest() {
        // Given an empty string we should get an empty map back
        assertEquals(Utilities.queryToMap(EMPTY_STRING), new HashMap<String, String>());
    }

    @Test
    public void queryToMapSingleTest() {
        String singleField = "field=value";
        Map<String, String> map = new HashMap<>();
        map.put("field", "value");
        assertEquals(Utilities.queryToMap(singleField), map);
    }
}
