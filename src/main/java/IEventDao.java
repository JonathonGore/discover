import java.util.List;

/**
 * Created by jack on 2017-04-19.
 */
public interface IEventDao {
    // Gets all events
    List<Event> getEvents();

    // Insert an event
    boolean insertEvent(Event event);
}
