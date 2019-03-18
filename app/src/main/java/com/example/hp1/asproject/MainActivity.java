package com.example.hp1.asproject;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final int NOTIFICATION_REMINDER_NIGHT = 2;
    ArrayList<Item> arrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout gallery = findViewById(R.id.gallery);
        LayoutInflater inflater = LayoutInflater.from(this);

        arrayList.add(new Item(R.drawable.action, "Action"));
        arrayList.add(new Item(R.drawable.biography, "Biorgraphy"));
        arrayList.add(new Item(R.drawable.sports, "Sports"));
        arrayList.add(new Item(R.drawable.history, "History"));
        arrayList.add(new Item(R.drawable.buisness, "Buisness"));


        Intent notifyIntent = new Intent(this,MyReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast
                (this, NOTIFICATION_REMINDER_NIGHT, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,  System.currentTimeMillis(),
                1000 * 60 * 60 * 24, pendingIntent);


        for(int i = 0; i< arrayList.size() ; i++){
            View view = inflater.inflate(R.layout.horiz_item, gallery, false);
            TextView text = view.findViewById(R.id.horText);
            text.setText(arrayList.get(i).getName());

            ImageView imageView = view.findViewById(R.id.imageView);
            imageView.setImageResource(arrayList.get(i).getImage());

            gallery.addView(view);

        }
    }
}
