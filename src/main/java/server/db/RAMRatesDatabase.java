package server.db;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class RAMRatesDatabase implements RatesDatabaseAPI {
    private static volatile RAMRatesDatabase mInstance;
    private Map<String, Map<String, Double>> payload = new HashMap<>();

    private RAMRatesDatabase() {
        initSomeRates();
    }

    public static RAMRatesDatabase getInstance() {
        if (mInstance == null) {
            synchronized (RAMRatesDatabase.class) {
                if (mInstance == null) {
                    mInstance = new RAMRatesDatabase();
                }
            }
        }
        return mInstance;
    }

    private void initSomeRates() {
        Map<String, Double> rates;

        rates = new HashMap<>();
        rates.put("USD", 17.154);
        rates.put("EUR", 19.585);
        rates.put("RUB", 0.262);
        payload.put("BNM", rates);

        rates = new HashMap<>();
        rates.put("USD", 17.12);
        rates.put("EUR", 19.52);
        rates.put("RUB", 0.254);
        payload.put("MICB", rates);
    }

    @Override
    public void connect() {

    }

    @Override
    public void disconnect() {

    }

    @Override
    public boolean checkCESPExists(String cesp) {
        return payload.containsKey(cesp);
    }

    @Override
    public void insertCurrencyRates(String cesp, Map<String, Double> rates) {
        payload.put(cesp, rates);
    }

    @Override
    public void updateCurrencyRates(String cesp, Map<String, Double> rates) {
        Map<String, Double> oldRates = payload.get(cesp);
        rates.forEach(oldRates::put);
    }

    @Override
    public boolean deleteCurrencyRates(String cesp) {
        if (!payload.containsKey(cesp)) {
            return false;
        }
        payload.remove(cesp);
        return true;
    }

    @Override
    public boolean deleteCurrencyRates(String cesp, Set<String> currencies) {
        if (!payload.containsKey(cesp)) {
            return false;
        }
        Map<String, Double> cespsRates = payload.get(cesp);
        boolean atLeastOneRemoved = false;
        for (String currency : currencies) {
            if (cespsRates.containsKey(currency)) {
                atLeastOneRemoved = true;
                cespsRates.remove(currency);
            }
        }
        return atLeastOneRemoved;
    }

    @Override
    public Map<String, Double> selectCurrencyRates(String currency) {
        Map<String, Double> rates = new HashMap<>();
        for (String cesp : payload.keySet()) {
            Double val = payload.get(cesp).get(currency);
            if (val != null) {
                rates.put(cesp, val);
            }
        }
        return rates;
    }

    @Override
    public Map<String, Map<String, Double>> selectCurrencyRates() {
        return payload;
    }
}
