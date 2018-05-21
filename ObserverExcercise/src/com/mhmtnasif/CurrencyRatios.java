package com.mhmtnasif;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

public class CurrencyRatios {

    private Map<String, ExchangeRatio> exchangeRatios = new HashMap<>();
    private static final CurrencyRatios currencyRatios = new CurrencyRatios();

    private CurrencyRatios() {

    }

    public Map<String, ExchangeRatio> getExchangeRatios() {
        return exchangeRatios;
    }


    public static CurrencyRatios getInstance() {
        return currencyRatios;
    }
}

class ExchangeRatio extends Observable {

    private double value;
    private String name;

    public ExchangeRatio(String name) {
        this.value = 0;
        this.name=name;
        CurrencyRatios.getInstance().getExchangeRatios().put(name,this);
    }

    public double getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public void setValue(double value) {
        this.value = value;
        setChanged();
        notifyObservers();
    }

}