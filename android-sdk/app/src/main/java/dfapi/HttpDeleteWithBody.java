package dfapi;

import org.apache.http.client.methods.HttpPost;

/**
 * Creates an Apache delete method that allows you to use a body
 */
public class HttpDeleteWithBody extends HttpPost {
    public static final String METHOD_PATCH = "DELETE";

    public HttpDeleteWithBody(final String url) {
        super(url);
    }

    @Override
    public String getMethod() {
        return METHOD_PATCH;
    }
}
