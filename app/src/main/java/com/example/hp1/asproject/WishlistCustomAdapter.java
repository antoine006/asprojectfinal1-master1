package com.example.hp1.asproject;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class WishlistCustomAdapter extends ArrayAdapter<Movie> {
    private int resourceLayout;
    private Context mContext;



    public WishlistCustomAdapter(@NonNull Context context, int resource, @NonNull List<Movie> objects) {
        super(context, resource, objects);
        this.resourceLayout = resource;
        this.mContext = context;
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;

        if(v==null)
            v = LayoutInflater.from(mContext).inflate(resourceLayout,parent, false);
        final Movie p =getItem(position);
        if (p!=null){
            TextView tv1 =(TextView) v.findViewById(R.id.tv1);
            tv1.setText(p.getMname());

            ImageView imageView=(ImageView)v.findViewById(R.id.imageview);
            imageView.setImageResource(p.getImage());


        }
        return v;

    }
}