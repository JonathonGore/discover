import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.Filter;

import java.sql.*;

import java.util.LinkedList;
import java.util.List;


/**
 * Created by jack on 2017-03-20.
 */
public class DiscoverDAO {

    private static Logger logger = LogManager.getLogger(DiscoverDAO.class);

    // Configuration
    // TODO: Move to configuration files ...
    private final String driver = "com.mysql.cj.jdbc.Driver";
    // use SSL is to remove error.. maybe eventually fix this properly
    private final String dbUrl = "jdbc:mysql://localhost/discover?useSSL=false";
    private final String dbUser = "root";
    private final String dbPass = "waterloo";

    private Gson gson = null;

    private Connection connection = null;

    private static DiscoverDAO discoverDAO = null;

    public static DiscoverDAO getInstance() {
        if(discoverDAO == null) {
            discoverDAO = new DiscoverDAO();
        }
        return discoverDAO;
    }

    private DiscoverDAO() {
        gson = new Gson();
       establishConnection();
       setupDB();
    }

    private void setupDB() {
        try {
            insert(Queries.createTable);
        } catch (Exception ex) {
            logger.error("Unable to setup database table, error: {}", ex.getMessage());
        }
    }

    /**
     * Return result set received from executing statement
     * Watch out for NULL pointers!!
     * @param query
     * @return
     */
    private ResultSet query(String query) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeQuery(query);
    }

    /**
     * Executes a non-query statement against the db
     * @param query the query to execute
     */
    private void insert(String query) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute(query);
    }

    /**
     * Attempts to establish a connection to the database.
     * This is a critical part of the application -> If unable to create
     * connection we will abort
     */
    private void establishConnection() {
        try{
            Class.forName(driver);
            connection = DriverManager.getConnection(dbUrl, dbUser, dbPass);
        } catch(Exception e){
            logger.error(e.getMessage());
            //throw new RuntimeException("Unable to connect to DB aborting...");
        }
    }


    private String eventToInsertQuery(Event event) {
        return String.format("INSERT INTO `discover`.`events` (`event_id`, `name`, `duration`," +
                " `created_at`, `begins_at`, `ends_at`, `hosts`, `admins`, `attendees`) VALUES (" +
                "'%s', '%s', '%s', <{created_at: }>, <{begins_at: }>, <{ends_at: }>," +
                " <{hosts: }>, <{admins: }>, <{attendees: }>);", "f4ffkkljer" /* replace with event id */,
                event.getName(), event.getDuration(), event.getCreatedAt(), event.getBeginsAt(),
                event.getEndsAt(), event.getHosts().toString(), event.getAdmins().toString(),
                event.getAttendees().toString());
    }

    /**
     * Gets all events available to a user
     * TODO: Limit to x amount
     */
    public List<Event> getEvents() {
        LinkedList<Event> events = new LinkedList<>();
        try {
            ResultSet rs = query(Queries.getEvents);
            while (rs.next()) {
                Event.EventBuilder eb = new Event.EventBuilder();
                Event e = eb.setName(rs.getString("name"))
                        .setBeginsAt(Long.parseLong(rs.getString("begins_at")))
                        .setEndsAt(Long.parseLong(rs.getString("ends_at")))
                        .setDuration(rs.getString("duration")).build();
                // TODO: Add utility function to convert deliminated string to list
                   //     .setAdmins(rs.getString("admins"))
                   //     .setAttendees("attendees")
                    //    .setHost("hosts").build();
                events.add(e);
            }
        } catch (SQLException ex) {
            logger.error("Unable to get events from database");
        }

        return events;
    }

    /**
     * Inserts an event into the DB
     * @return
     */
    public boolean insertEvent(Event event) {
        try {
            insert(event.toInsertQuery());
        } catch (SQLException ex) {
            logger.error("Unable to insert event {} in database", "fsf32f" /*Change to get event id*/);
        }
        return true;
    }

}