package br.alicefranco.countriesapp;

import android.content.Intent;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);

        new Timer().schedule(new TimerTask(){
            public void run() {
                SplashActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        startActivity(new Intent(SplashActivity.this, CountriesListActivity.class));
                        SplashActivity.this.finish();
                    }
                });
            }
        }, 1000);
    }
}
