package com.example.hp1.asproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    ListView Lvmovies;
    CustomAdapter arrayAdapter;
    ArrayList<Item> arrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Lvmovies = (ListView) findViewById(R.id.Lvmovies) ;


        arrayList.add(new Item(R.drawable.sports,"Sports"));
        arrayList.add(new Item(R.drawable.photography,"Photography"));
        arrayList.add(new Item(R.drawable.biography,"Biography"));
        arrayList.add(new Item(R.drawable.history,"History"));
        arrayList.add(new Item(R.drawable.action,"action"));

        arrayAdapter = new CustomAdapter(this,R.layout.custom_row,arrayList);
        Lvmovies.setAdapter(arrayAdapter);
        Lvmovies.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long ld) {
        //  String selectedItem = arrayList.get(position);
        Intent i = new Intent(this, MoviesListActivity.class);
        if(position==0){
            i.putExtra("Category","sports" );
        }else if(position ==1 ){
            i.putExtra("Category","photography" );
        }else if(position==2){
            i.putExtra("Category","biography" );
        }else if(position==3){
            i.putExtra("Category","History" );
        }
        else if (position==4){
            i.putExtra("Category","action");
        }
        startActivity(i);
    }
}
