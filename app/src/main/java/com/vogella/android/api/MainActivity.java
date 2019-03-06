package com.vogella.android.api;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.List;

public class MainActivity extends Activity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private Controller controller;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.my_recycler_view);
        controller = new Controller(this);
        controller.start();

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
