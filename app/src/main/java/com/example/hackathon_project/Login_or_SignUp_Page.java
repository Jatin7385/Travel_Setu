package com.example.hackathon_project;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Login_or_SignUp_Page extends AppCompatActivity {

    private Button login,signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_or_sign_up_page);

        signup = findViewById(R.id.btn_signup);
        login = findViewById(R.id.btn_login);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login_or_SignUp_Page.this , SignUpActivity.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login_or_SignUp_Page.this,LoginActivity.class);
                startActivity(intent);
            }
        });

    }
}