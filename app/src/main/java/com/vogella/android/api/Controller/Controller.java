package com.vogella.android.api.Controller;

import android.util.Log;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vogella.android.api.View.MainActivity;
import com.vogella.android.api.model.Films;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Controller implements Callback<List<Films>> {
    private MainActivity activity;

    static final String BASE_URL = "https://ghibliapi.herokuapp.com/";

    public Controller(MainActivity mainActivity) {
        this.activity = mainActivity;
    }

    public void start() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        GerritAPI gerritAPI = retrofit.create(GerritAPI.class);

        Call<List<Films>> call = gerritAPI.getListFilms();
        call.enqueue(this);

    }
        @Override
        public void onResponse(Call<List<Films>> call, Response<List<Films>> response) {
            List<Films> listFilms = response.body();

            activity.showList(listFilms);
        }

        @Override
        public void onFailure(Call<List<Films>> call, Throwable t) {
            Log.d("ERROR", "Api Error");
        }
}