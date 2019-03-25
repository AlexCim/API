package com.vogella.android.api.View;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.vogella.android.api.Controller.Controller;
import com.vogella.android.api.R;
import com.vogella.android.api.model.Films;

import java.util.List;

public class MainActivity extends Activity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private Controller controller;

    private static final String PREFS_TITLE = "PREFS_TITLE";
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.my_recycler_view);
        controller = new Controller(this);
        controller.start();

        sharedPreferences = getBaseContext().getSharedPreferences(PREFS_TITLE, MODE_PRIVATE);
        if (sharedPreferences.contains(PREFS_TITLE)) {
            String title = sharedPreferences.getString(PREFS_TITLE,null);
            Toast.makeText(this, title, Toast.LENGTH_SHORT).show();
        }
        else{
            sharedPreferences
                    .edit()
                    .putString(PREFS_TITLE, "Alex")
                    .apply();
            Toast.makeText(this, "Sauvegardé, relancez l'application pour voir le résultat", Toast.LENGTH_SHORT).show();
        }

    }

    public void showList(List<Films> input){
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new MyAdapter(input, new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Films item) {
                detailsFilms(item);
            }
        });
        recyclerView.setAdapter(mAdapter);
    }

    public void detailsFilms(Films films){
        Gson gson = new Gson();
        String json = gson.toJson(films);
        Intent detailsIntent = new Intent(this, DetailsActivity.class);
        detailsIntent.putExtra("anime", json);
        startActivity(detailsIntent);
    }
}
