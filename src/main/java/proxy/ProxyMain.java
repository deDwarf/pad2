package proxy;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;

public class ProxyMain {
    private static final String BASE_URI_TEMPLATE = "http://%s:%s/";
    private static final String HOST = "localhost";
    private static final String PORT = "8080";

    public static HttpServer startServer(String host, String port) {
        // create a resource config that scans for JAX-RS resources and providers
        String uri = String.format(BASE_URI_TEMPLATE, host, port);
        final ResourceConfig rc = new ResourceConfig().packages("proxy/api");
        System.out.println("Started proxy server.. Hit enter to exit");
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(uri), rc);
    }

    public static void main(String[] args) throws IOException {
        final HttpServer server = startServer(HOST, PORT);
        //noinspection ResultOfMethodCallIgnored
        int c = System.in.read();
        while(c != 13) {
            c = System.in.read();
        }
        server.shutdownNow();
    }
}
