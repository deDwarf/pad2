package api;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Glossary:
 * - CESP - Currency Exchange Service Provider
 *
 */
@Path("currencies")
public class CurrencyResource {

    private static final Response NOT_IMPLEMENTED = Response
            .status(501)
            .entity("This operation is not yet implemented")
            .build();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{currency}")
    public Response getRatesForCurrency(@PathParam("currency") String currency) {
        return Response.ok("WTF").build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRatesForAllCurrencies() {
        return NOT_IMPLEMENTED;
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
