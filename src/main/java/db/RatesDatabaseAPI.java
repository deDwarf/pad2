package db;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface RatesDatabaseAPI {
    
    void connect();
    
    void disconnect();

    boolean checkCESPExists(String cesp);
    
    void insertCurrencyRates(String cesp, Map<String, Double> rates);

    void updateCurrencyRates(String cesp, Map<String, Double> rates);

    boolean deleteCurrencyRates(String cesp);

    boolean deleteCurrencyRates(String cesp, Set<String> currencies);

    /**
     *
     * @param currency desired currency
     * @return all CESPs` offers for given currency as map
     */
    Map<String, Double> selectCurrencyRates(String currency);

    /**
     * Get all available currency rates for today`s date
     * @return Currency Exchange Service Provider (CESP) mapped to it`s rates
     */
    Map<String, Map<String, Double>> selectCurrencyRates();
}

