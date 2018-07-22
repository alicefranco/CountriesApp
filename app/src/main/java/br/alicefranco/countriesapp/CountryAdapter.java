package br.alicefranco.countriesapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder> {
    private ArrayList<Country> countriesList;

    CountryAdapter(ArrayList<Country> countriesList) {
        this.countriesList = countriesList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.country_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        holder.countryName.setText(countriesList.get(i).getName());
        holder.countryLanguage.setText(countriesList.get(i).getLanguages().get(0).getName());
        holder.countryCurrency.setText(countriesList.get(i).getCurrencies().get(0).getName());
    }

    @Override
    public int getItemCount() {
        return countriesList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView countryName;
        TextView countryLanguage;
        TextView countryCurrency;

        ViewHolder(View itemView) {
            super(itemView);
            countryName = itemView.findViewById(R.id.tv_country_name);
            countryLanguage = itemView.findViewById(R.id.tv_country_language_value);
            countryCurrency = itemView.findViewById(R.id.tv_country_currency_value);
        }
    }
}


