package br.alicefranco.countriesapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.util.ArrayList;

public class CountriesListActivity extends AppCompatActivity implements AsyncTaskResponse{
    private RecyclerView rvCountries;
    private ProgressBar progress;
    private Button reloadButton;
    private TextView errorMessage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.countries_list_activity);

        rvCountries = findViewById(R.id.rv_countries_list);
        progress = findViewById(R.id.countries_progressbar);
        reloadButton = findViewById(R.id.reload_button);
        errorMessage = findViewById(R.id.tv_countries_error);

        reloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progress.setVisibility(View.VISIBLE);
                errorMessage.setVisibility(View.GONE);
                reloadButton.setVisibility(View.GONE);
                fetchData();
            }
        });
        fetchData();
    }

    private void fetchData(){
        progress.setVisibility(View.VISIBLE);
        HttpRequest httpRequest = new HttpRequest(this);
        httpRequest.execute(Constants.DATA_URL);
    }

    private void setupRecycler(ArrayList<Country> countries){
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvCountries.getContext(), layoutManager.getOrientation());

        final CountryAdapter adapter = new CountryAdapter(countries);

        //TODO pass adapter
        CustomSwipeCallback customItemTouchCallBack =  new CustomSwipeCallback(this, rvCountries, adapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(customItemTouchCallBack);
        itemTouchHelper.attachToRecyclerView(rvCountries);

        rvCountries.setLayoutManager(layoutManager);
        rvCountries.setAdapter(adapter);
        rvCountries.addItemDecoration(dividerItemDecoration);
        progress.setVisibility(View.GONE);
    }


    @Override
    public void onResponse(String response, Boolean error){
        if(error){
            progress.setVisibility(View.GONE);
            errorMessage.setVisibility(View.VISIBLE);
            reloadButton.setVisibility(View.VISIBLE);
        }
        else {
            JsonDeserializer deserializer = new JsonDeserializer(response);
            if(deserializer.deserialize() != null)
                setupRecycler(deserializer.deserialize());
            else{
                progress.setVisibility(View.GONE);
                errorMessage.setVisibility(View.VISIBLE);
                reloadButton.setVisibility(View.VISIBLE);
            }
            progress.setVisibility(View.GONE);
        }
    }
}


