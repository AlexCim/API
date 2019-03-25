package com.vogella.android.api.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;
import com.vogella.android.api.R;
import com.vogella.android.api.model.Films;

public class DetailsActivity extends AppCompatActivity {
    public TextView title;
    public TextView director;
    public TextView release_date;
    public TextView description;
    public TextView producer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        title = (TextView) findViewById(R.id.titleLine);
        director =(TextView) findViewById(R.id.directorLine);
        release_date = (TextView) findViewById(R.id.dateLine);
        description =(TextView) findViewById(R.id.descriptionLine);
        producer = (TextView) findViewById(R.id.producerLine);


        String json = getIntent().getStringExtra("anime");
        Gson gson = new Gson();
        Films item = gson.fromJson(json ,Films.class);

        title.setText("Title: " + item.getTitle());
        director.setText("Director: " + item.getDirector());
        release_date.setText("Date: " + item.getRelease_date());
        producer.setText("Producer: " + item.getProducer());
        description.setText("Description: " + item.getDescription());


    }
}
