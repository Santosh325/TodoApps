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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    EditText name,email,phone,password;
    Button register,delete;
    String getname,getemail,getmobile,getpassword;
    TextView login;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference mRef = database.getReference("user");
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        password = findViewById(R.id.password);
        register = findViewById(R.id.register);
        delete = findViewById(R.id.delete);
        login = findViewById(R.id.login);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getname = name.getText().toString();
                getemail = email.getText().toString();
                getmobile = phone.getText().toString();
                getpassword = password.getText().toString();


                mRef.child(getname).child("name").setValue(getname).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(MainActivity.this, "Data Added",Toast.LENGTH_SHORT).show();
                    }
                });



        mRef.child(getname).child("name").setValue(getname);
        mRef.child(getname).child("mobile").setValue(getmobile);
        mRef.child(getname).child("email").setValue(getemail);
        mRef.child(getname).child("password").setValue(getpassword);

        signupmethod(getemail,getpassword);
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getname = name.getText().toString();
                mRef.child(getname).removeValue();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),login.class));
            }
        });

    }
    public void signupmethod(String getemail,String getpassword) {
        mAuth.createUserWithEmailAndPassword(getemail,getpassword)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        finish();
                        Toast.makeText(getApplicationContext(),"Sign Up success", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),home.class));
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),"Sign up Failed", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
