package com.example.hp1.asproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

public class signup extends AppCompatActivity implements View.OnClickListener{
    TextView signup2;
    Button btsignup;
    EditText etuser,etpass,etcon;
    private FirebaseAuth mAuth;
    final String TAG="firebase";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signup2=findViewById(R.id.signup2);
        etuser=findViewById(R.id.etuser);
        etpass=findViewById(R.id.etpass);
        etcon=findViewById(R.id.etcon);
        btsignup   = findViewById(R.id.btsignup);
        btsignup.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance(); //conects betwwen the firebase and the project

    }
    public void openActivity2(){

    }
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }
    public void createUser(String email,String password){
        mAuth.createUserWithEmailAndPassword(email, password)  //Tries to create a new user account with the given email address and password.
                                                            // If successful, it also signs the user in into the app
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign up success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent intent=new Intent(signup.this,Main2Activity.class);
                            startActivity(intent);
                            // updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(signup.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // ...
                    }
                });
    }

    @Override
    public void onClick(View v) {
        if(v == btsignup){
            String user = etuser.getText().toString();
            String password = etpass.getText().toString();
            if(user.equals("") || password.equals("") ){
            Toast.makeText(signup.this,"Username or Password is empty",Toast.LENGTH_SHORT).show();
            }else{
                createUser(user,password);
            }
        }
    }
}
