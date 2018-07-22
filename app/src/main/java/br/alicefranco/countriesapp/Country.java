package br.alicefranco.countriesapp;

import java.util.ArrayList;

class Country {
    private String name;
    private ArrayList<Language> languages;
    private ArrayList<Currency> currencies;

    public Country(String name, ArrayList<Language> languages, ArrayList<Currency> currencies){
        this.name = name;
        this.languages = languages;
        this.currencies = currencies;
    }

    public void setName(String name){ this.name = name; }
    public void setLanguages(ArrayList<Language> languages){ this.languages = languages; }
    public void setCurrencies(ArrayList<Currency> currencies){ this.currencies = currencies; }

    public String getName(){ return name; }
    public ArrayList<Language> getLanguages(){ return languages; }
    public ArrayList<Currency> getCurrencies(){ return currencies; }

}


