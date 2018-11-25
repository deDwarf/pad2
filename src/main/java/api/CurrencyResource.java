package api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import db.RAMRatesDatabase;
import db.RatesDatabaseAPI;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

/**
 * Glossary:
 * - CESP - Currency Exchange Service Provider
 *
 */
@Path("currencies")
public class CurrencyResource {
    private RatesDatabaseAPI db = RAMRatesDatabase.getInstance();
    private Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    private static final Response NOT_IMPLEMENTED = Response
            .status(501)
            .entity("This operation is not yet implemented")
            .build();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{currency}")
    public Response getRatesForCurrency(@PathParam("currency") String currency) {
        Map<String, Double> rates = db.selectCurrencyRates(currency);
        if (rates == null || rates.isEmpty()) {
            return Response.ok("There is no rates associated with given currency: " + currency).build();
        }
        return Response.ok(this.mapToJson(rates)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRatesForAllCurrencies() {
        Map<String, Map<String, Double>> rates = db.selectCurrencyRates();
        if (rates == null || rates.isEmpty()) {
            return Response.ok("There is no any rates information available. Please repeat later").build();
        }
        return Response.ok(this.mapToJson(rates)).build();
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

    private String mapToJson(Map<?, ?> map) {
        return gson.toJson(map);
    }
}
