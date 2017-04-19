import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.InetSocketAddress;
import java.util.List;

/**
 * Created by jack on 2017-03-18.
 * Singleton server class
 */
public class Server {

    private static final String HTTP_POST = "POST";
    private static final String HTTP_GET = "GET";

    private static Logger logger = LogManager.getLogger(Server.class);
    private static Server server = null;
    private EventManager eventManager = null;
    private HttpServer httpServer = null;
    private Gson gson = null;

    public static Server getInstance() {
        if(server == null) {
            server = new Server();
        }
        return server;
    }

    /**
     * Creates a server on the port designated in configuration file
     * @throws IOException
     */
    private void createServer() throws IOException {
        // Pull out port to be configured
        logger.info("Creating server...");
        httpServer = HttpServer.create(new InetSocketAddress(8000), 0);
        createEndpoints();
        httpServer.setExecutor(null); // creates a default executor
    }

    // Starts the server
    public void start() {
        httpServer.start();
    }

    private void createEndpoints() {
        logger.info("Registering endpoints..");
        httpServer.createContext("/events", new EventHandler());
        httpServer.createContext("/test", new MyHandler());
    }

    // Constructor
    private Server() {
        /*TODO: Instead of passing in null we must pass in the appropriate client
         *      for retrieving data. This will be elastic search client
         */

        eventManager = new EventManager(new ElasticDataClient());

        gson = new Gson();
        // Try to create the server otherwise throw
        try {
            createServer();
        } catch (IOException ex) {
            throw new RuntimeException("Unable to create server shutting down...");
        }

    }

    /**
     * Returns the request type of a given HttpExchange t
     * @param t the HttpExchange
     * @return one of POST, GET, PATCH... etc
     */
    private String getRequestType(HttpExchange t) {
        return t.getRequestMethod();
    }

    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            ResponseWriter.write(t, new Response(200, "This is the response"));
        }
    }

    /**
     * Handler for the /events endpoint
     */
    private class EventHandler implements HttpHandler {

        /**
         * Handles a GET request for the events endpoint
         * @return the String that is to be used as the response
         */
        private String handleGET() {
            logger.info("Received get request at /events");
            Type listType = new TypeToken<List<Event>>() {}.getType();
            String response = gson.toJson(eventManager.getEvents(), listType);
            logger.info("Sending response: {}", response);
            return response;
        }

        /**
         * Handles a POST request for the events endpoint
         * @return the String that is to be used as the response
         */
        private String handlePOST(HttpExchange t) {
            // Convert Body of the Http request to string
            String body = Utilities.toString(t.getRequestBody());
            logger.info("Received post request at /events. With body {}", body);
            eventManager.createEvent(body);
            return "Created event successfully";
        }

        @Override
        public void handle(HttpExchange t) throws IOException {
            String response = "";
            // Get body from http request
            String requestType = getRequestType(t);
            if(HTTP_POST.equalsIgnoreCase(requestType)) {
                response = handlePOST(t);

            } else if(HTTP_GET.equalsIgnoreCase(requestType)) {
                response = handleGET();
            }
            ResponseWriter.write(t, new Response(200, response));
        }
    }
}
