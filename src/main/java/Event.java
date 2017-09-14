import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
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

    private static final Type T_EVENT = new TypeToken<Event>(){}.getType();

    private String name;
    private String duration;
    private String eventID;

    private final long createdAt;
    private final long beginsAt;
    private final long endsAt;
    private final double latitude;
    private final double longitude;

    private final List<AdminPerson> admins;
    private final List<HostPerson> hosts;
    private final List<NormalPerson> attendees;

    public static Event eventFromJSON(String json) {
        Gson gson = new Gson();
        Event e = gson.fromJson(json, T_EVENT);
        return e;
    }

    private Event(EventBuilder eb) {

        this.name = eb.name;
        this.duration = eb.duration;
        this.eventID = eb.eventID;
        this.createdAt = eb.createdAt;
        this.beginsAt = eb.beginsAt;
        this.endsAt = eb.endsAt;
        this.admins = eb.admins;
        this.hosts = eb.hosts;
        this.attendees = eb.attendees;
        this.latitude = eb.latitude;
        this.longitude = eb.longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Event event = (Event) o;

        if (createdAt != event.createdAt) return false;
        if (beginsAt != event.beginsAt) return false;
        if (endsAt != event.endsAt) return false;
        if (latitude != event.latitude) return false;
        if (longitude != event.longitude) return false;
        if (name != null ? !name.equals(event.name) : event.name != null) return false;
        if (duration != null ? !duration.equals(event.duration) : event.duration != null) return false;
        if (eventID != null ? !eventID.equals(event.eventID) : event.eventID != null) return false;
        if (admins != null ? !admins.equals(event.admins) : event.admins != null) return false;
        if (hosts != null ? !hosts.equals(event.hosts) : event.hosts != null) return false;
        return attendees != null ? attendees.equals(event.attendees) : event.attendees == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name != null ? name.hashCode() : 0;
        result = 31 * result + (duration != null ? duration.hashCode() : 0);
        result = 31 * result + (eventID != null ? eventID.hashCode() : 0);
        result = 31 * result + (int) (createdAt ^ (createdAt >>> 32));
        result = 31 * result + (int) (beginsAt ^ (beginsAt >>> 32));
        result = 31 * result + (int) (endsAt ^ (endsAt >>> 32));
        temp = Double.doubleToLongBits(latitude);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(longitude);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (admins != null ? admins.hashCode() : 0);
        result = 31 * result + (hosts != null ? hosts.hashCode() : 0);
        result = 31 * result + (attendees != null ? attendees.hashCode() : 0);
        return result;
    }

    public String toString() {
        // TODO Make one static GSON instance
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

    public String getEventID() { return this.eventID; }

    public long getCreatedAt() { return this.createdAt; }

    public long getBeginsAt() { return this.beginsAt; }

    public long getEndsAt() { return this.endsAt; }

    public double getLongitude() { return this.longitude; }

    public double getLatitude() { return this.latitude; }

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
        private String eventID;
        private long createdAt;
        private long beginsAt;
        private long endsAt;
        private double latitude;
        private double longitude;

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
            this.eventID = event.getEventID();
            this.createdAt = event.getCreatedAt();
            this.beginsAt = event.getBeginsAt();
            this.endsAt = event.getEndsAt();
            this.longitude = event.getLongitude();
            this.latitude = event.getLatitude();
        }

        // Setters

        /**
         * Sets the name of this event
         * @param name The name of this event.
         * @return The updated EventBuilder
         */
        public EventBuilder setName(String name) {
            this.name = name;
            return this;
        }

        /**
         * Sets the duration for this event in a human readable form i.e 3 hours, 1 day ... etc.
         * @param duration The duration of this event.
         * @return The updated EventBuilder
         */
        public EventBuilder setDuration(String duration) {
            this.duration = duration;
            return this;
        }

        /**
         * Sets the eventID for this event.
         * @param eventID The event id for this event as a UUID.
         * @return The updated EventBuilder
         */
        public EventBuilder setEventID(String eventID) {
            this.eventID = eventID;
            return this;
        }

        /**
         * Sets the time this event was created at.
         * @param createdAt The time this event was created at in UNIX time.
         * @return The updated EventBuilder
         */
        public EventBuilder setCreatedAt(long createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        /**
         * Sets the time this event will begin.
         * @param beginsAt The time this event will begin in UNIX time.
         * @return The updated EventBuilder
         */
        public EventBuilder setBeginsAt(long beginsAt) {
            this.beginsAt = beginsAt;
            return this;
        }

        /**
         * Sets the time this event will end.
         * @param endsAt The time this event will end in UNIX time.
         * @return The updated EventBuilder
         */
        public EventBuilder setEndsAt(long endsAt) {
            this.endsAt = endsAt;
            return this;
        }

        /**
         * Sets the admins for this event
         * @param admins The list of people who have admin capabilities for this event.
         * @return The updated EventBuilder
         */
        public EventBuilder setAdmins(List<AdminPerson> admins) {
            this.admins = admins;
            return this;
        }

        /**
         * Sets the hosts for this event
         * @param hosts The list of people hosting the event.
         * @return The updated EventBuilder
         */
        public EventBuilder setHost(List<HostPerson> hosts) {
            this.hosts = hosts;
            return this;
        }

        /**
         * Sets the attendees of this event
         * @param attendees The list of attendees that will be at this event.
         * @return The updated EventBuilder
         */
        public EventBuilder setAttendees(List<NormalPerson> attendees) {
            this.attendees = attendees;
            return this;
        }

        /**
         * Sets the geographical location of this event
         * @param latitude The latitude coordinate of the location
         * @param longitude The longitude coordinate of the location
         * @return The updated EventBuilder
         */
        public EventBuilder setCoordinates(double latitude, double longitude) {
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
