package com.example.hackathon_project;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class SignUpActivity extends AppCompatActivity {
    private String first_name,last_name,number,dob,mail,password,re_enter_password;
    private EditText edt_first_name,edt_last_name,edt_number,edt_dob,edt_mail,
            edt_password,edt_re_enter_password;
    private Button signup;
    private String id;
    private String imageURL = "default";
    private HashMap<String, Object> hashMap;
    private CircleImageView image;
    int SELECT_PICTURE = 200;
    private ProgressBar progressBar;
    private Uri uri;

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
        image = findViewById(R.id.user_image);
        progressBar = findViewById(R.id.progress_bar);

        mAuth = FirebaseAuth.getInstance();

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageChooser();
                if(uri!=null){image.setImageURI(uri);}
            }
        });

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

                                String filepath = "Photos/" + "userprofile" + user.getUid();
                                StorageReference reference = FirebaseStorage.getInstance().getReference(filepath);
                                if (uri != null ) {
                                    // update the preview image in the layout
                                    reference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                    @Override
                                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                        Task<Uri> task = taskSnapshot.getMetadata().getReference().getDownloadUrl();

                                        task.addOnSuccessListener(new OnSuccessListener<Uri>() {
                                            @Override
                                            public void onSuccess(Uri uri) {
                                                imageURL = uri.toString();
                                                if(imageURL == "null")
                                                {
                                                    Uri urii = Uri.parse("app/src/main/res/drawable-v24/profilepicc.png");
                                                    imageURL = urii.toString();
                                                }
                                            }
                                        });
                                    }
                                    });

                                }


                                hashMap = new HashMap<>();
                                hashMap.put("id", user.getUid());
                                hashMap.put("imageURL",imageURL);
                                hashMap.put("first_name",first_name);
                                hashMap.put("last_name", last_name);
                                hashMap.put("number",number);
                                hashMap.put("dob", dob);
                                hashMap.put("mail", mail);
                                hashMap.put("password", password);

                                DatabaseReference reference1;
                                reference1 = FirebaseDatabase.getInstance().getReference("Users").child(user.getUid());

                                reference1.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
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
        });
    }

    void imageChooser() {

        // create an instance of the
        // intent of the type image
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        // pass the constant to compare it
        // with the returned requestCode
        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
    }

    // this function is triggered when user
    // selects the image from the imageChooser
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        progressBar.setVisibility(View.VISIBLE);
        signup.setEnabled(false);
        signup.getBackground().setAlpha(50);

        if (resultCode == RESULT_OK) {

            // compare the resultCode with the
            // SELECT_PICTURE constant
            if (requestCode == SELECT_PICTURE) {
                // Get the url of the image from data
                uri = data.getData();
//                if(uri!=null) {
//                    image.setImageURI(uri);
//                }
            }
        }

        progressBar.setVisibility(View.INVISIBLE);
        signup.setEnabled(true);
        signup.getBackground().setAlpha(100);
    }
}