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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class SignUpActivity extends AppCompatActivity {
    private String first_name,last_name,number,dob,mail,password,re_enter_password;
    private EditText edt_first_name,edt_last_name,edt_number,edt_dob,edt_mail,
            edt_password,edt_re_enter_password;
    private Button signup;
    private String id;
    private HashMap<String, Object> hashMap;
    private DatabaseReference reference;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        edt_first_name = findViewById(R.id.edt_first_name);
        edt_last_name = findViewById(R.id.edt_last_name);
        edt_number = findViewById(R.id.edt_number);
        edt_dob = findViewById(R.id.edt_dob);
        edt_mail = findViewById(R.id.edt_mail);
        edt_password = findViewById(R.id.edt_pswrd);
        edt_re_enter_password = findViewById(R.id.edt_re_enter_password);
        signup = findViewById(R.id.button_signup);

        mAuth = FirebaseAuth.getInstance();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                first_name = edt_first_name.getText().toString().trim();
                last_name = edt_last_name.getText().toString().trim();
                number = edt_number.getText().toString().trim();
                dob = edt_dob.getText().toString().trim();
                mail = edt_mail.getText().toString().trim();
                password = edt_password.getText().toString().trim();
                re_enter_password = edt_re_enter_password.getText().toString().trim();

//                if(password.equals(re_enter_password)) {
                    mAuth.createUserWithEmailAndPassword(mail, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                FirebaseUser user = mAuth.getCurrentUser();
                                hashMap = new HashMap<>();
                                hashMap.put("id", user.getUid());
                                hashMap.put("first_name",first_name);
                                hashMap.put("last_name", last_name);
                                hashMap.put("number",number);
                                hashMap.put("dob", dob);
                                hashMap.put("mail", mail);
                                hashMap.put("password", password);

                                reference = FirebaseDatabase.getInstance().getReference("Users").child(user.getUid());

                                reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        Toast.makeText(SignUpActivity.this, "User Created. Kindly Login", Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                                        startActivity(intent);
                                    }
                                });
                            }
                            else {
                                Toast.makeText(SignUpActivity.this,"Error : " + task.getException(),Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            //}
        });
    }
}