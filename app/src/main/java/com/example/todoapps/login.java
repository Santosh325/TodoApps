package com.example.todoapps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import static com.example.todoapps.R.layout.activity_login;

public class login extends AppCompatActivity {
    EditText email,password;
    Button login;
    TextView signup;
    FirebaseAuth mAuth;
    String getEmail,getPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_login);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        signup = findViewById(R.id.signup);
        mAuth = FirebaseAuth.getInstance();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getEmail = email.getText().toString();
                getPassword = password.getText().toString();
                signInMethod(getEmail,getPassword);
            }
        });
    }

    public void signInMethod(String getEmail, String getPassword) {
        mAuth.signInWithEmailAndPassword(getEmail,getPassword)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        finish();
                        Toast.makeText(getApplicationContext(),"You are logged In",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),home.class));
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),"Log in failed",Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
