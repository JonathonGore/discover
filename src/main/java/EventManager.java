import com.google.gson.Gson;

import java.util.List;

/**
 * Created by jack on 2017-03-19.
 */
public class EventManager {
    private static EventManager em = null;
    //private DiscoverDAO discoverDAO = null;

    public static EventManager getInstance() {
        if(em == null) {
            em = new EventManager();
        }
        return em;
    }

    private EventManager() {
        //discoverDAO = DiscoverDAO.getInstance();
    }

    public Event createEvent(String eventAsJSON) {
        Gson gson = new Gson();
        Event e =  gson.fromJson(eventAsJSON, Event.class);
        // Put into database
        return e;
    }

    public List<Event> getEvents() {
       //return discoverDAO.getEvents();
        return null;
    }
}
