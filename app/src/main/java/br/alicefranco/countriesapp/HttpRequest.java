package br.alicefranco.countriesapp;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class HttpRequest extends AsyncTask<String, Void, String> {
    private Boolean error = false;
    private AsyncTaskResponse responseListener;


    HttpRequest(AsyncTaskResponse responseListener){
       this.responseListener = responseListener;
    }

    @Override
    protected String doInBackground(String... stringUrl) {
        URL url;
        BufferedReader reader = null;

        try {
            URI uri = new URI(stringUrl[0]);
            url = uri.toURL();
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.connect();

            int statusCode = conn.getResponseCode();
            if (statusCode != 200){
                error = true;
                return null;
            }

            InputStream inputStream = conn.getInputStream();
            StringBuilder buffer = new StringBuilder();

            if (inputStream == null) {
                error = true;
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                error = true;
                return null;
            }

            return buffer.toString();

        } catch (IOException e) {

            Log.i("HTTP REQUEST","Connection unsucessful.");
            e.printStackTrace();
            error = true;
            return null;

        } catch (URISyntaxException e) {

            e.printStackTrace();
            error = true;
            return null;

        } finally {

            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception e) {
                    error = true;
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    protected void onPostExecute(String response) {
        responseListener.onResponse(response, error);
    }
}
