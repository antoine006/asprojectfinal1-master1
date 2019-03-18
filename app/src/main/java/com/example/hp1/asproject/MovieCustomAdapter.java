package com.example.hp1.asproject;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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

public class MovieCustomAdapter extends ArrayAdapter<Movie> {
    private int resourceLayout;
    private Context mContext;
    Button btAddToWishList;

    //get the current user logged in, then get the UID set by the firebase
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseUser currentUser = mAuth.getCurrentUser();
    String userId = currentUser.getUid();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference("Users");
    public MovieCustomAdapter(@NonNull Context context, int resource, @NonNull List<Movie> objects) {
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
            TextView tv1 =(TextView) v.findViewById(R.id.tvMovieName);
            tv1.setText(p.getMname());

            ImageView imageView=(ImageView)v.findViewById(R.id.imageview);
            imageView.setImageResource(p.getImage());

            Button btAdd =v.findViewById(R.id.btAddToWishList);
            btAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(), "Added", Toast.LENGTH_SHORT).show();
                    reference.child(userId).push().setValue(p);
                    Intent intent = new Intent(mContext , WishlistActivity.class);
                    mContext.startActivity(intent);
                }
            });
           Button btInfo =v.findViewById(R.id.infBtn);
            btInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext , Moviedetails.class);
                    intent.putExtra("movie",p);
                    mContext.startActivity(intent);
                }
            });

        }
        return v;

    }
}
