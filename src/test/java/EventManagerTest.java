import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by jack on 2017-04-29.
 */
public class EventManagerTest {

    private EventManager eventManager;

    /**
     * Sets up the test fixture.
     * (Called before every test case method.)
     */
    @Before
    public void setUp() {
        eventManager = new EventManager(new DummyDataClient());
    }


    @Test
    public void createEventTest() {
        // We should get back the same event as we pass.
        Event e = DummyEvents.getEvent(0);
        assertEquals(eventManager.createEvent(e.toJSON()), e);
    }
}
