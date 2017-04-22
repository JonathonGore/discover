import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by jack on 2017-04-19.
 */
public class ElasticDataClient implements IEventDao {

    private static final Logger logger = LogManager.getLogger(ElasticDataClient.class);
    private static final String ES_PREFIX = "es.";

    private final Config config;
    private TransportClient client = null;

    public ElasticDataClient(Config config) {
        this.config = config;
        buildClient();
    }

    /** Build the Elasticsearch Transport client for fetching data from
     * Elasticsearch.
     * TODO: If it fails to build what do we do?
     */
    private void buildClient() {
        logger.info("Creating ES transport client");
        // Create transport settings with proper clustername
        Settings settings = Settings.builder()
                .put("cluster.name", config.getString(ES_PREFIX + "cluster")).build();

        try {
            InetAddress address = InetAddress.getByName(config.getString(ES_PREFIX + "host"));
            client = new PreBuiltTransportClient(settings)
                    .addTransportAddress(new InetSocketTransportAddress(address, config.getInt(ES_PREFIX + "port")));
            logger.info("Successfully created transport client");
        } catch (UnknownHostException ex) {
            logger.error("Unable to resolve host.");
        }
        // TODO: Figure this out
        // This needs to happen on shutdown but where??
        //client.close();
    }

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
