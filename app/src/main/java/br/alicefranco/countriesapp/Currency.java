package br.alicefranco.countriesapp;

public class Currency {
    private String name;
    private String symbol;

    public void setName(String name){ this.name = name; }
    public void setSymbol(String symbol){ this.symbol = symbol; }

    public String getName(){ return name; }
    public String getSymbol(){ return symbol; }
}
