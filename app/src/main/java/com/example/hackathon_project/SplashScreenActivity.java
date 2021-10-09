package com.example.hackathon_project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class SplashScreenActivity extends AppCompatActivity {
    private int loginStatus = 0;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        if (user != null) {loginStatus = 1;}


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                if(loginStatus == 1){
                    //intent = new Intent(SplashScreenActivity.this,MainActivity.class);
//                }
//                else {
                    intent = new Intent(SplashScreenActivity.this, Login_or_SignUp_Page.class);
//                }
                finish();
                startActivity(intent);
            }
        }, 3000);
    }
}

