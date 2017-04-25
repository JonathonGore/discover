import java.util.List;

/**
 * Created by jack on 2017-04-19.
 */
public interface IEventDao {
    // Gets all events - make sure its paginated or returns a partial response
    List<Event> getEvents();

    /**
     * Gets the event with the corresponding eventId or NULL
     * if it doesn't exist.
     */
    Event getEvent(String eventId);

    // Insert an event
    boolean insertEvent(Event event);
}
