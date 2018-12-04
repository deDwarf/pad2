package server;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;

public class ServerMain {
    // Base URI the Grizzly HTTP server will listen on
    private static final String BASE_URI_TEMPLATE = "http://%s:%s/";

    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
     * @return Grizzly HTTP server.
     */
    public static HttpServer startServer(String host, String port) {
        // create a resource config that scans for JAX-RS resources and providers
        String uri = String.format(BASE_URI_TEMPLATE, host, port);
        final ResourceConfig rc = new ResourceConfig().packages("server/api");
        System.out.println(String.format("Jersey app started with WADL available at %sapplication.wadl", uri));
        System.out.println("Hit enter to stop it...");
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(uri), rc);
    }

    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            throw new IllegalArgumentException(String.format("Expected two arguments: host and port. Received: %s"
                    , args.length));
        }
        final HttpServer server = startServer(args[0], args[1]);
        //noinspection ResultOfMethodCallIgnored
        System.in.read();
        server.shutdownNow();
    }
}
