import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Created by jack on 2017-03-18.
 * Singleton server class
 */
public class Server {

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
        httpServer.createContext("/events", new EventCreationHandler());
        httpServer.createContext("/test", new MyHandler());
    }

    // Constructor
    private Server() {
        /*TODO: Instead of passing in null we must pass in the appropriate client
     *          for retrieving data. This will be elastic search client
         */

        eventManager = new EventManager(null);

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
    private class EventCreationHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            // Get body from http request
            if(getRequestType(t).equalsIgnoreCase("POST")) {
                // Body of the Http request
                String body = Utilities.toString(t.getRequestBody());
                logger.info("Received post request at /events. With body {}", body);
            } else if(getRequestType(t).equalsIgnoreCase("GET")) {
                logger.info("Received get request at /events");
            }
               // String body = t.getRequestBody().toString();
               // String e = eventManager.createEvent(body).toString();
               // System.out.println("Event as string:" + e);
                ResponseWriter.write(t, new Response(200, "This is the response"));
        }
    }
}
