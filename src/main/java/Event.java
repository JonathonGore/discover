import com.google.gson.Gson;
import java.util.List;

/**
 * Created by jack on 2017-03-17.
 * Events are the basics of this whole application
 * Example of creating an event:
 *
 * EventBuilder eb = new EventBuild();
 * Event e = eb.setName("Awesome Party")
 *             .setDuration("42 Years")
 *             .buildEvent();
 *
 */
public class Event {

    private String name;
    private String duration;

    private final long createdAt;
    private final long beginsAt;
    private final long endsAt;
    private final long latitude;
    private final long longitude;

    private final List<AdminPerson> admins;
    private final List<HostPerson> hosts;
    private final List<NormalPerson> attendees;

    private Event(EventBuilder eb) {

        this.name = eb.name;
        this.duration = eb.duration;
        this.createdAt = eb.createdAt;
        this.beginsAt = eb.beginsAt;
        this.endsAt = eb.endsAt;
        this.admins = eb.admins;
        this.hosts = eb.hosts;
        this.attendees = eb.attendees;
        this.latitude = eb.latitude;
        this.longitude = eb.longitude;
    }

    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    // For now because toString and toJSON might change and don't want them to be dependant on each other
    public String toJSON() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    // Getters

    public String getName() { return this.name; }

    public String getDuration() { return this.duration; }

    public long getCreatedAt() { return this.createdAt; }

    public long getBeginsAt() { return this.beginsAt; }

    public long getEndsAt() { return this.endsAt; }

    public long getLongitude() { return this.longitude; }

    public long getLatitude() { return this.latitude; }

    public List<AdminPerson> getAdmins() { return this.admins; }

    public List<HostPerson> getHosts() { return this.hosts; }

    public List<NormalPerson> getAttendees() { return this.attendees; }

    /**
     * Created by jack on 2017-03-17.
     * Builder class used to more concisely create events;
     */
    public static class EventBuilder {

        private String name;
        private String duration;
        private long createdAt;
        private long beginsAt;
        private long endsAt;
        private long latitude;
        private long longitude;

        private List<AdminPerson> admins;
        private List<HostPerson> hosts;
        private List<NormalPerson> attendees;

        /**
         * Constructor for creating a NEW event (no previous data)
         */
        public EventBuilder() {}

        /**
         * Constructor for updating an event (previous data)
         */
        public EventBuilder(Event event) {
            // Use getters to update EventBuilder with all fields from the event..
            this.name = event.getName();
            this.duration = event.getDuration();
            this.createdAt = event.getCreatedAt();
            this.beginsAt = event.getBeginsAt();
            this.endsAt = event.getEndsAt();
            this.longitude = event.getLongitude();
            this.latitude = event.getLatitude();
        }

        // Setters

        public EventBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public EventBuilder setDuration(String duration) {
            this.duration = duration;
            return this;
        }

        public EventBuilder setCreatedAt(long createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public EventBuilder setBeginsAt(long beginsAt) {
            this.beginsAt = beginsAt;
            return this;
        }

        public EventBuilder setEndsAt(long endsAt) {
            this.endsAt = endsAt;
            return this;
        }

        public EventBuilder setAdmins(List<AdminPerson> admins) {
            this.admins = admins;
            return this;
        }

        public EventBuilder setHost(List<HostPerson> hosts) {
            this.hosts = hosts;
            return this;
        }

        public EventBuilder setAttendees(List<NormalPerson> attendees) {
            this.attendees = attendees;
            return this;
        }

        public EventBuilder setPosition(long latitude, long longitude) {
            this.latitude = latitude;
            this.longitude = longitude;
            return this;
        }

        /**
         * Create an event from the event builder
         * @return the new Event
         */
        public Event build() {
            return new Event(this);
        }
    }

}
