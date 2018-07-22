package br.alicefranco.countriesapp;

import org.json.JSONException;

public interface AsyncTaskResponse {
    void onResponse(String response, Boolean error);
}
