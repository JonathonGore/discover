/**
 * Created by jack on 2017-03-19.
 */
public class Response {
    private final String message;
    private final int code;

    public Response(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() { return this.message; }

    public int getStatusCode() { return this.code; }
}
