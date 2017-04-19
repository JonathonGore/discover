import java.util.LinkedList;
import java.util.List;

/**
 * Created by jack on 2017-04-19.
 */
public class ElasticDataClient implements IEventDao {

    /**
     * Gets all events in ElasticSearch
     * @return List of events
     */
    public List<Event> getEvents() {
        Event event = new Event.EventBuilder()
                .setName("Super cool event")
                .setEndsAt(32434242L)
                .setBeginsAt(3423424L)
                .setDuration("2 months").build();

        List<Event> events =  new LinkedList<Event>();
        events.add(event);
        return events;
    }

    /**
     * Inserts the given event into ElasticSearch
     * @param event: the event to insert into ElasticSearch
     * @return whether or not the insertion was successful
     */
    public boolean insertEvent(Event event) {
        return true;
    }
}
