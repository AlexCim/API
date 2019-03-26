package com.vogella.android.api.Controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

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
    private Context context;
    private static final String PREFS_title = "PREFS_title";
    SharedPreferences sharedPreferences;

    static final String BASE_URL = "https://ghibliapi.herokuapp.com/";

    public Controller(MainActivity mainActivity , Context context) {
        this.activity = mainActivity;
        this.context = context;
    }

    public void start() {

        /*sharedPreferences = context.getSharedPreferences(PREFS_title, context.MODE_PRIVATE);
        if (sharedPreferences.contains(PREFS_title)) {
            String title = sharedPreferences.getString(PREFS_title,null);
            Gson gson3 = new Gson();
            Films films = gson3.fromJson(PREFS_title ,Films.class);

        }
        else{*/
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
            /*Gson gson2 = new Gson();
            String json = gson2.toJson(call);
            sharedPreferences
                    .edit()
                    .putString(PREFS_title, json)
                    .apply();
        }*/



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