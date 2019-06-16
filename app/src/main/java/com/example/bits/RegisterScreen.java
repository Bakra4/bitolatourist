package com.example.bits;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;


public class RegisterScreen extends AppCompatActivity {
    EditText username, password;
    CardView registerBtn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        registerBtn = findViewById(R.id.register_btn);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!username.getText().toString().isEmpty() && !password.getText().toString().isEmpty()) {
                    FirebaseService.getFirebaseInstance().createUserWithEmailAndPassword(username.getText().toString(), password.getText().toString())
                            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    Toast.makeText(RegisterScreen.this, "the user " + authResult.getUser() + " was successfully created", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(RegisterScreen.this, LoginScreen.class);
                                    startActivity(intent);
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(RegisterScreen.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                    } else {
                        Toast.makeText(RegisterScreen.this, "please make sure both of the fields are not empty", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
