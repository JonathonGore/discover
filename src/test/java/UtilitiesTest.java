import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by jack on 2017-04-26.
 */
public class UtilitiesTest {
    private final String EMPTY_STRING = "";

    /* queryToMap tests */

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

    @Test
    public void queryToMapDoubleTest() {
        String singleField = "field1=value1&field2=value2";
        Map<String, String> map = new HashMap<>();
        map.put("field1", "value1");
        map.put("field2", "value2");
        assertEquals(Utilities.queryToMap(singleField), map);
    }

    @Test
    public void missingValueTest() {
        String onlyField = "field=";
        Map<String, String> map = new HashMap<>();
        assertEquals(Utilities.queryToMap(onlyField), map);
    }

    @Test
    public void missingKeyTest() {
        String onlyValue = "=value";
        Map<String, String> map = new HashMap<>();
        assertEquals(Utilities.queryToMap(onlyValue), map);
    }

    @Test
    public void extraSplittableTest() {
        String onlyValue = "&&&===&&&&&=&";
        Map<String, String> map = new HashMap<>();
        assertEquals(Utilities.queryToMap(onlyValue), map);
    }

    @Test
    public void singleSymbolTest1() {
        String onlyValue = "&";
        Map<String, String> map = new HashMap<>();
        assertEquals(Utilities.queryToMap(onlyValue), map);
    }

    @Test
    public void singleSymbolTest2() {
        String onlyValue = "=";
        Map<String, String> map = new HashMap<>();
        assertEquals(Utilities.queryToMap(onlyValue), map);
    }

    /* isEmpty(String s) tests  */

    @Test
    public void emptyTest() {
        assertEquals(Utilities.isEmpty(EMPTY_STRING), true);
    }

    @Test
    public void nullTest() {
        assertEquals(Utilities.isEmpty(null), true);
    }

    @Test
    public void nonEmptyTest() {
        assertEquals(Utilities.isEmpty("not empty"), false);
    }

}
