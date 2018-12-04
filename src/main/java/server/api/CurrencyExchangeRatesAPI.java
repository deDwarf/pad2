package server.api;
import javax.ws.rs.core.Response;

public interface CurrencyExchangeRatesAPI {

    Response NOT_IMPLEMENTED = Response
            .status(501)
            .entity("This operation is not yet implemented")
            .build();

    Response getRatesForCurrency(String currency);
    
    Response getRatesForAllCurrencies();

    Response addCurrencyRates(String cesp);

    Response patchCurrencyRates(String cesp);

    Response removeExchangeRates(String cesp);
}
