package com.example.hp1.asproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Moviedetails extends AppCompatActivity {

    TextView tvName;
    TextView tvRating;
    TextView tvSummary;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moviedetails);

        Movie movie = (Movie) getIntent().getSerializableExtra("movie");
        if(movie!=null) {
            tvName = findViewById(R.id.tvName);
            tvName.setText(movie.getMname());

            tvRating = findViewById(R.id.tvRating);
            tvRating.setText(movie.getRating() + " ");

            tvSummary = findViewById(R.id.tvSummary);
            tvSummary.setText(movie.getSummary());
        }

    }
}
