import com.sun.net.httpserver.HttpExchange;
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
    public static void write(HttpExchange t, Response response) throws IOException {

        // Make sure the response is not null
        if(response == null) {
            logger.error("Attemped to write a null response.. aborting...");
            return;
        }

        String message = response.getMessage();
        t.sendResponseHeaders(response.getStatusCode(), message.length());
        OutputStream os = t.getResponseBody();
        os.write(message.getBytes());
        os.close();
    }
}
