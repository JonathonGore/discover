import java.util.LinkedList;
import java.util.List;

/**
 * Created by jack on 2017-04-29.
 */
public class DummyDataClient implements IEventDao {

    /**
     * Dummy method for testing
     * @return a list of events
     */
    public List<String> getEvents() {
        List<Event> events = new LinkedList<>();
        Event event1 = DummyEvents.getEvent(0);
        Event event2 = DummyEvents.getEvent(1);
        events.add(event1);
        events.add(event2);
        return null;
    }

    public List<String> searchEvents(String q) {
        return null;
    }

    /**
     * Dummy IMPL for testing
     * Gets the event with the corresponding eventId or NULL
     */
    public Event getEvent(String eventId) {
        Event event = new Event.EventBuilder()
                .setEventID(eventId)
                .setBeginsAt(0L)
                .setEndsAt(0L)
                .setName("Event")
                .setDescription("2 Hours")
                .setCreatedAt(Utilities.getCurrentTime())
                .setCoordinates(0L, 0L).build();
        return event;
    }

    /**
     * Dummy IMPL for testing
     * Deletes the event with the corresponding eventId
     * if it exists.
     */
    public boolean deleteEvent(String eventId) {
        return true;
    }

    /**
     * Dummy IMPL for testing
     * Inserts the event with the corresponding eventId.
     */
    public boolean insertEvent(Event event) {
        return true;
    }
}
