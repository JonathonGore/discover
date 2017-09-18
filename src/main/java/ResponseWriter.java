import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpsExchange;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by jack on 2017-03-19.
 */
public class ResponseWriter {

    private static Logger logger = LogManager.getLogger(ResponseWriter.class);

    /**
     * Writes the message in response to the http address contained in t
     */
    public static void write(HttpExchange ht, Response response) throws IOException {
        HttpsExchange t = (HttpsExchange) ht;

        // Make sure the response is not null
        if(response == null) {
            logger.error("Attemped to write a null response.. aborting...");
            return;
        }

        // Add response header for CORS to succeed
        t.getResponseHeaders().add("Access-Control-Allow-Origin", "*");

        // Get the message from the proposed response object
        String message = response.getMessage();
        logger.info("Responding to request with message {}.", message);
        // Set status code and size of message we are responding with
        t.sendResponseHeaders(response.getStatusCode(), message.length());
        // Write our response to the output stream
        OutputStream os = t.getResponseBody();
        os.write(message.getBytes());
        os.close();
    }
}
