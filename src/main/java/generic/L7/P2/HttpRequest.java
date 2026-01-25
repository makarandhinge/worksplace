package generic.L7.P2;

import java.util.HashMap;

public class HttpRequest {
    private final String method;
    private final String url;
    private final HashMap<String, String> header;
    private final String body;
    private final Integer timeout;

    public HttpRequest(String method, String url, HashMap<String, String> header, String body, Integer timeout) {
        this.method = method;
        this.url = url;
        this.header = header;
        this.body = body;
        this.timeout = timeout;
    }

    public static BuilderStage builder(){
        return new HttpRequestBuilderImpl();
    }
}
