package com.example.hp1.asproject;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Item> {
private int resourceLayout;
private Context mContext;
    public CustomAdapter(@NonNull Context context, int resource, @NonNull List<Item> objects) {
        super(context, resource, objects);
        this.resourceLayout = resource;
        this.mContext = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;

        if(v==null)
         v = LayoutInflater.from(mContext).inflate(resourceLayout,parent, false);
        Item p =getItem(position);
        if (p!=null){
            TextView tv1 =(TextView) v.findViewById(R.id.tv1);
            tv1.setText(p.getName());
            ImageView imageView=(ImageView)v.findViewById(R.id.imageview);
            imageView.setImageResource(p.getImage());

        }
        return v;

    }
}
