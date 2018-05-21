package com.mhmtnasif;

import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class MoneyBag {

    private  List<MoneyUnit> moneyUnits = new LinkedList<>();

    public List<MoneyUnit> getMoneyUnits() {
        return moneyUnits;
    }

    public  void print() {
        for (MoneyUnit m : getMoneyUnits()) {
            System.out.println(m.amountOfCurrency+" "+m.exchangeRatio.getName()+
                    " with "+m.exchangeRatio.getValue()+" Exchange Ratio = "+ m.getValue());
        }
        System.out.println("-------------------------");
    }

}

abstract class MoneyUnit {

    protected int amountOfCurrency;
    protected double value;
    protected ExchangeRatio exchangeRatio;


    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}

class Usd extends MoneyUnit implements Observer {

    public Usd(int amountOfCurrency, ExchangeRatio exchangeRatio) {
        this.exchangeRatio = exchangeRatio;
        this.exchangeRatio.addObserver(this);
        this.amountOfCurrency = amountOfCurrency;

    }

    @Override
    public void update(Observable o, Object arg) {
        setValue(this.amountOfCurrency/this.exchangeRatio.getValue());
    }

}

class Pln extends MoneyUnit implements Observer {

    public Pln(int amountOfCurrency, ExchangeRatio exchangeRatio) {
        this.exchangeRatio = exchangeRatio;
        this.exchangeRatio.addObserver(this);
        this.amountOfCurrency = amountOfCurrency;
    }

    @Override
    public void update(Observable o, Object arg) {
        setValue(this.amountOfCurrency/this.exchangeRatio.getValue());
    }
}

class Tl extends MoneyUnit implements Observer {

    public Tl(int amountOfCurrency, ExchangeRatio exchangeRatio) {
        this.exchangeRatio = exchangeRatio;
        this.exchangeRatio.addObserver(this);
        this.amountOfCurrency = amountOfCurrency;
    }

    @Override
    public void update(Observable o, Object arg) {
        setValue(this.amountOfCurrency/this.exchangeRatio.getValue());
    }
}