import com.google.gson.Gson;

import java.util.List;

/**
 * Created by jack on 2017-03-19.
 */
public class EventManager {
    private IEventDao eventDao;

    /**
     * Creates a new event dao
     * @param eventDao The object that will act as a datastore
     */
    public EventManager(IEventDao eventDao) {
        this.eventDao = eventDao;
    }


    // TODO: Not sure if it makes sense to return the inserted event, lets change this to a boolean throughout
    /**
     * Creates a new event and inserts into our datastore
     * @param eventAsJSON The event to create as JSON
     * @return the newly created event
     */
    public Event createEvent(String eventAsJSON) {
        Gson gson = new Gson();
        Event event = gson.fromJson(eventAsJSON, Event.class);
        // Put into datastore
        eventDao.insertEvent(event);
        return event;
    }

    public List<String> searchEvents(String query) { return eventDao.searchEvents(query); }

    public List<String> getEvents() {
       return eventDao.getEvents();
    }

    public Event getEvent(String id) { return eventDao.getEvent(id); }

    public boolean deleteEvent(String id) { return eventDao.deleteEvent(id); }
}
