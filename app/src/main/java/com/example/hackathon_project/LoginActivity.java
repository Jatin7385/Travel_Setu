package com.example.hackathon_project;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private String email,password;
    private EditText edit_email,edit_password;
    private Button login;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edit_email = findViewById(R.id.edt_email);
        edit_password = findViewById(R.id.edt_password);
        login = findViewById(R.id.button_login);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = edit_email.getText().toString().trim();
                password = edit_password.getText().toString().trim();

                mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                            finish();
                            startActivity(intent);
                        }
                        else
                            {
                                Toast.makeText(LoginActivity.this,"Error : "+ task.getException(),Toast.LENGTH_LONG).show();
                            }
                    }
                });
            }
        });
    }
}