package proxy.api;

import proxy.ServerEntity;
import proxy.ServerLoadRegistry;
import server.api.CurrencyExchangeRatesAPI;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("currencies")
public class ProxyCurrencyExchangeResource implements CurrencyExchangeRatesAPI {
    private Logger log = Logger.getLogger(getClass().getName());

    private ServerLoadRegistry obs = ServerLoadRegistry.getInstance();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{currency}")
    public Response getRatesForCurrency(@PathParam("currency") String currency) {
        ServerEntity executor = obs.getLeastLoadedServer();
        String uri = executor.getBaseUri() + "currencies/" + currency;
        log.log(Level.INFO, String.format("uri: <%s>, executor: <%s>", uri, executor));
        return executor.getCl()
                .target(uri)
                .request(MediaType.APPLICATION_JSON)
                .get();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRatesForAllCurrencies() {
        ServerEntity executor = obs.getLeastLoadedServer();
        return executor.getCl()
                .target(executor.getBaseUri() + "currencies/")
                .request(MediaType.APPLICATION_JSON)
                .get();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addCurrencyRates(@PathParam("cesp") String cesp) {
        return NOT_IMPLEMENTED;
    }

    @PATCH
    @Consumes(MediaType.APPLICATION_JSON)
    public Response patchCurrencyRates(@PathParam("cesp") String cesp) {
        return NOT_IMPLEMENTED;
    }

    @DELETE
    public Response removeExchangeRates(@PathParam("cesp") String cesp) {
        return NOT_IMPLEMENTED;
    }
}
