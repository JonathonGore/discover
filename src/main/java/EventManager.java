import com.google.gson.Gson;

import java.util.List;

/**
 * Created by jack on 2017-03-19.
 */
public class EventManager {
    private static EventManager em = null;
    private IEventDao eventDao;
    //private DiscoverDAO discoverDAO = null;

    public EventManager(IEventDao eventDao) {
        this.eventDao = eventDao;
    }

    public Event createEvent(String eventAsJSON) {
        Gson gson = new Gson();
        Event event =  gson.fromJson(eventAsJSON, Event.class);
        // Put into database
        eventDao.insertEvent(event);
        return event;
    }

    public List<Event> getEvents() {
       return eventDao.getEvents();
    }
}
