package br.alicefranco.countriesapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;

public class JsonDeserializer {
    private ArrayList<Country> countries = new ArrayList<>();
    private String json;

    JsonDeserializer(String json){
        this.json = json;
    }

    public ArrayList<Country> deserialize() {
       /* GsonBuilder gsobBuilder = new GsonBuilder();
        Gson gson = gsobBuilder.create();
        Type listType = new TypeToken<ArrayList<Country>>(){}.getType();
        countries = gson.fromJson(json, listType);//, clazz);
        return countries;*/

        try {

            JSONArray array = new JSONArray(new JSONTokener(json));
            for (int i = 0; i < array.length(); i++) {
                String name = array.getJSONObject(i).getString("name");

                JSONArray currenciesArray = array.getJSONObject(i).getJSONArray("currencies");
                ArrayList<Currency> currencies = new ArrayList<>();
                for (int j = 0; j < currenciesArray.length(); j++) {
                    String currencyName = currenciesArray.getJSONObject(j).getString("name");
                    String currencySymbol = currenciesArray.getJSONObject(j).getString("symbol");
                    currencies.add(new Currency(currencyName, currencySymbol));
                }

                JSONArray languagesArray = array.getJSONObject(i).getJSONArray("languages");
                ArrayList<Language> languages = new ArrayList<>();
                for (int j = 0; j < languagesArray.length(); j++) {
                    String languageName = languagesArray.getJSONObject(j).getString("name");
                    String languageNativeName = languagesArray.getJSONObject(j).getString("nativeName");
                    languages.add(new Language(languageName, languageNativeName));
                }
                countries.add(new Country(name, languages, currencies));
            }
            return countries;
        }
        catch (JSONException e){
            Log.e("JSONException", e.toString());
        }
        return null;
    }
}
