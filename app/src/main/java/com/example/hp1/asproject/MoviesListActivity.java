package com.example.hp1.asproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MoviesListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ArrayList<Movie> arrayList = new ArrayList<>();
    ListView lvmovies;
    Button btAddToWishList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);


        lvmovies = findViewById(R.id.lvMovies);

        String category = getIntent().getStringExtra("Category");
        Toast.makeText(this, getIntent().getStringExtra("Category"), Toast.LENGTH_LONG).show();
        if(category != null)
            fillSubCategoriesList(category);

        MovieCustomAdapter adapter = new MovieCustomAdapter(this, R.layout.movie_custom_row, arrayList);
        lvmovies.setAdapter(adapter);
        lvmovies.setOnItemClickListener(this);

        btAddToWishList = findViewById(R.id.btAddToWishList);




    }
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent i=new Intent(this,detailedmovie.class);
        i.putExtra("movie", arrayList.get(position));
        startActivity(i);
    }

    public void fillSubCategoriesList(String Category){

        Toast.makeText(this, Category, Toast.LENGTH_SHORT).show();
        if (Category.equals("sports")) {
            arrayList.add(new Movie("Creed", 4.5, R.drawable.creed, "this is the summary"));
            arrayList.add(new Movie("concussion", 4.5, R.drawable.concussion, "this is the summary"));
        }
        if (Category.equals("photography")) {
            arrayList.add(new Movie("Closer", 4.5, R.drawable.closer, "this is the summary"));
            arrayList.add(new Movie("" +"pecker", 4.5, R.drawable.pecker, "this is the summary"));
        }

        if (Category.equals("biography")) {
            arrayList.add(new Movie("The Wizard of lies", 4.5, R.drawable.thewizardoflies, "this is the summary"));
            arrayList.add(new Movie("the persuit of happyness", 4.5, R.drawable.thepersuitofhappyness, "this is the summary"));
        }
        if (Category.equals("History")) {
            arrayList.add(new Movie("troy", 4.5, R.drawable.troy, "this is the summary"));
            arrayList.add(new Movie("walkiria", 4.5, R.drawable.walkiria, "this is the summary"));
        }
        if (Category.equals("action")) {
            arrayList.add(new Movie("sky fall", 4.5, R.drawable.skyfall, "this is the summary"));
            arrayList.add(new Movie("spectre", 4.5, R.drawable.spectre, "this is the summary"));

        }
        lvmovies = findViewById(R.id.lvMovies);

        MovieCustomAdapter adapter  = new MovieCustomAdapter(this, R.layout.movie_custom_row, arrayList);

        lvmovies.setAdapter(adapter);

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}