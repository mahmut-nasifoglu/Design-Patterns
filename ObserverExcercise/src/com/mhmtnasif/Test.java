package com.mhmtnasif;

public class Test {
    public static void main(String[] args) {
        MoneyBag moneyBag = new MoneyBag();
        moneyBag.getMoneyUnits().add(new Usd(100, new ExchangeRatio("usd")));
        moneyBag.getMoneyUnits().add(new Pln(100, new ExchangeRatio("pln")));
        moneyBag.getMoneyUnits().add(new Tl(100, new ExchangeRatio("tl")));
        CurrencyRatios.getInstance().getExchangeRatios().get("usd").setValue(2);
        moneyBag.print();
        CurrencyRatios.getInstance().getExchangeRatios().get("pln").setValue(4);
        moneyBag.print();
        CurrencyRatios.getInstance().getExchangeRatios().get("tl").setValue(5);
        moneyBag.print();
        CurrencyRatios.getInstance().getExchangeRatios().get("usd").setValue(10);
        moneyBag.print();


    }
}
