package com.example.hp1.asproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.Executor;

public class SignupFragment extends Fragment {

    TextView signup2;
    Button btsignup;
    EditText etuser,etpass,etcon;
    private FirebaseAuth mAuth;
    final String TAG="firebase";



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mAuth = FirebaseAuth.getInstance();
        View view = inflater.inflate(R.layout.activity_signup,container,false);
        // Inflate the layout for this fragment

        btsignup = view.findViewById(R.id.btsignup);
        signup2=view.findViewById(R.id.signup2);
        etuser=view.findViewById(R.id.etuser);
        etpass=view.findViewById(R.id.etpass);
        etcon=view.findViewById(R.id.etcon);


        btsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = etuser.getText().toString();
                String password = etpass.getText().toString();
                if(user.equals("") || password.equals("") ){

                }else{
                    createUser(user,password);
                }
            }

        });
        return view;
    }

    public void createUser(String email,String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener((Executor) this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                      //      Intent intent=new Intent(signup.this,Main2Activity.class);
                         //   startActivity(intent);
                            // updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
              //              Toast.makeText(signup.this, "Authentication failed.",
                        //            Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // ...
                    }
                });
    }

}
