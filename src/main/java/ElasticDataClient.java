import com.typesafe.config.Config;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.search.SearchHit;
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
    private static final String DELETED = "DELETED";

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
     * @return List of events represented as strings
     */
    public List<String> getEvents() {

        int resultCount = 0;
        LinkedList<String> events = new LinkedList<>();

        // Prepare a search for retrieving entries from elastic search
        // Limited to 100
        SearchResponse resp = client.prepareSearch(config.getString(ES_PREFIX + "index"))
                .setSize(100).get(); //max of 100 hits will be returned for each scroll


        for(SearchHit sh : resp.getHits()) {
            events.add(sh.getSourceAsString());
            resultCount++;
        }

        logger.info("Fetched {} entries from elasticsearch", resultCount);

        return events;
    }

    /**
     * Gets the event with the corresponding eventId from elasticsearch
     * @param id The id of the requested Event
     * @return the requested Event or null
     */
    public Event getEvent(String id) {
        GetResponse response = client.prepareGet(config.getString(ES_PREFIX + "index"),
                config.getString(ES_PREFIX + "type"), id).get();
        logger.info("Retrieved event {} from elasticsearch", response.getSourceAsString());
        return Event.eventFromJSON(response.getSourceAsString());
    }

    /**
     * Deletes the Event with the corresponding id if it exists
     * @param id The id of the Event to delete
     * @return true if an event was delete, false otherwise
     */
    public boolean deleteEvent(String id) {
        // TODO: Get if the delete was successful and return that instead of just false
        DeleteResponse response = client.prepareDelete(config.getString(ES_PREFIX + "index"),
                config.getString(ES_PREFIX + "type"), id).get();
        return DELETED.equals(response.getResult().toString());
    }

    /**
     * Inserts the given event into ElasticSearch
     * @param event: the event to insert into ElasticSearch
     * @return whether or not the insertion was successful
     */
    public boolean insertEvent(Event event) {
        // TODO: Return if the insertion event was successful or not
        logger.info("Inserting event into elasticsearch");
        // TODO: .setSource is deprecated change it to something not deprecated
        // Paramaters are (index name, type, id)
        IndexResponse response = client.prepareIndex(
                config.getString(ES_PREFIX + "index"),
                config.getString(ES_PREFIX + "type"),
                event.getEventID())
                .setSource(event.toJSON())
                .get();
        return true;
    }
}
