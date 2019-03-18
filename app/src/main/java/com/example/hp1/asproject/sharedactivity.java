package com.example.hp1.asproject;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class sharedactivity extends AppCompatActivity implements View.OnClickListener {
 Button btnsave ;
 EditText Etname;
 TextView tvName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sharedactivity);

        btnsave = (Button) findViewById(R.id.btnsave);
        Etname  = (EditText) findViewById(R.id.Etname);
        btnsave.setOnClickListener(this);

        SharedPreferences pref = getSharedPreferences("profile",MODE_PRIVATE);
        String name=pref.getString("name",null);
        tvName  = findViewById(R.id.tvName);
        if(name !=null){
            tvName.setText(name);
        }
    }

    @Override
    public void onClick(View view) {
         SharedPreferences pref = getSharedPreferences("profile",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("name",Etname.getText().toString());
        editor.commit();
    }
}
