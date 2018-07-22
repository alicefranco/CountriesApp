package br.alicefranco.countriesapp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JsonDeserializer {
    private ArrayList<Country> countries;
    private String json;
    //private Class<ArrayList<T>> clazz;

    JsonDeserializer(String json){//, Class<ArrayList<T>> clazz){
        this.json = json;
        //this.clazz = clazz;
    }

    public ArrayList<Country> deserialize() {
        GsonBuilder gsobBuilder = new GsonBuilder();
        Gson gson = gsobBuilder.create();
        Type listType = new TypeToken<ArrayList<Country>>(){}.getType();
        countries = gson.fromJson(json, listType);//, clazz);
        return countries;
    }
}
