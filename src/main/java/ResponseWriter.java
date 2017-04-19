import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by jack on 2017-03-19.
 */
public class ResponseWriter {
    /**
     * Writes the message in response to the http address contained in t
     */
    public static void write(HttpExchange t, Response response) throws IOException {
        String message = response.getMessage();
        t.sendResponseHeaders(response.getStatusCode(), message.length());
        OutputStream os = t.getResponseBody();
        os.write(message.getBytes());
        os.close();
    }
}
