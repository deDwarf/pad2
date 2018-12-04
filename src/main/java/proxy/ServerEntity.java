package proxy;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

public class ServerEntity {
    private String name;
    private String host;
    private String port;
    private Client cl = ClientBuilder.newClient();

    public ServerEntity(String name, String host, String port) {
        this.name = name;
        this.host = host;
        this.port = port;
    }

    public String getName() {
        return name;
    }

    public String getHost() {
        return host;
    }

    public String getPort() {
        return port;
    }

    public Client getCl() {
        return cl;
    }

    public String getBaseUri() {
        return String.format("http://%s:%s/", host, port);
    }

    @Override
    public String toString() {
        return "ServerEntity{" +
                "name='" + name + '\'' +
                ", host='" + host + '\'' +
                ", port='" + port + '\'' +
                '}';
    }
}
