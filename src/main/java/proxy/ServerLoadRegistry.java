package proxy;

import java.util.ArrayList;
import java.util.List;

// TODO servers must register themselves (sic!) by themselves. Need to introduce some separate "protocol"
// TODO for proxy to communicate with server instances

// temporary, let us assume we will always have two server instances up&running:
// 1) localhost:25676
// 2) localhost:25678
public class ServerLoadRegistry {
    private static volatile ServerLoadRegistry mInstance;
    private static int nextNode = -1;
    private List<ServerEntity> servers = new ArrayList<>();

    static {
        getInstance().addServerEntity(
                new ServerEntity("first", "localhost", "25676")
        );
        getInstance().addServerEntity(
                new ServerEntity("second", "localhost", "25678")
        );
    }

    private ServerLoadRegistry(){}

    public static ServerLoadRegistry getInstance() {
        if (mInstance == null) {
            synchronized (ServerLoadRegistry.class) {
                if (mInstance == null) {
                    mInstance = new ServerLoadRegistry();
                }
            }
        }
        return mInstance;
    }

    public synchronized boolean addServerEntity(ServerEntity se) {
        if (servers.contains(se)) {
            return false;
        }
        servers.add(se);
        return true;
    }

    public synchronized boolean removeServerEntity(ServerEntity se) {
        if (!servers.contains(se)) {
            return false;
        }
        servers.remove(se);
        return true;
    }

    public synchronized ServerEntity getLeastLoadedServer() {
        nextNode = (nextNode + 1) % servers.size();
        return servers.get(nextNode);
    }

}
