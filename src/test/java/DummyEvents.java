/**
 * Created by jack on 2017-04-29.
 */
public class DummyEvents {

    private static final Event event0 = new Event.EventBuilder()
            .setEventID(Utilities.generateUUID())
            .setBeginsAt(0L)
            .setEndsAt(0L)
            .setName("First Event")
            .setDuration("1 Hour")
            .setCreatedAt(Utilities.getCurrentTime())
            .setCoordinates(0L, 0L).build();
    private static final Event event1 = new Event.EventBuilder()
            .setEventID(Utilities.generateUUID())
            .setBeginsAt(0L)
            .setEndsAt(0L)
            .setName("Second Event")
            .setDuration("2 Hours")
            .setCreatedAt(Utilities.getCurrentTime())
            .setCoordinates(0L, 0L).build();

    /**
     * Gets the event represented internally by 'id'
     * @requires 0 <= id <= 1
     * @param id the event to get
     * @return the consistent event
     */
    public static Event getEvent(int id) {
        Event e;
        switch(id) {
            case 0:     e = event0;
                        break;
            case 1:     e = event1;
                        break;
            default:    e = null;
                        break;
        }
        return e;
    }
}
