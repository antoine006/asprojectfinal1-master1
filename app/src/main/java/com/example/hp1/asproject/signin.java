package com.example.hp1.asproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class signin extends AppCompatActivity implements View.OnClickListener{
    private FirebaseAuth mAuth;
    TextView tv1;
    EditText username1,pass;
    Button signin,signupnot;
    final String TAG = "Firebase";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin);
        tv1= findViewById(R.id.tv1);
        username1= findViewById(R.id.username1);
        pass= findViewById(R.id.pass);
        signin= findViewById(R.id.signin);
        signin.setOnClickListener(this);

        signupnot= findViewById(R.id.signupnot);

        mAuth = FirebaseAuth.getInstance(); //conects between the fire base and the app

        signupnot.setOnClickListener(new View.OnClickListener() {
        public void onClick(View view) {
            Intent intent = new Intent(getApplication(), signup.class);
            startActivity(intent);
        }
    });



    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //  updateUI(currentUser);

    }

    public void  signIn(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update useriterface with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent i = new Intent(signin.this, Main2Activity.class);
                            startActivity(i);
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(signin.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                          //  updateUI(null);
                        }

                        // ...
                    }
                });
    }
    @Override
    public void onClick(View v) {
        //createUser
        if (v == signin) {
            String user = username1.getText().toString();
            String password = pass.getText().toString();
            if (user.equals("")|| password.equals("")) {
                Toast.makeText(signin.this, "Email  Or Passwrod Is Empty",
                        Toast.LENGTH_SHORT).show();
            } else {
                signIn(user, password);
            }

        }
    }

}

