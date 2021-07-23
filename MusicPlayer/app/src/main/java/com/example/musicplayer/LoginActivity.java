package com.example.musicplayer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.jetbrains.annotations.NotNull;

public class LoginActivity extends AppCompatActivity {

    EditText loginName, loginPassword;
    Button loginButton;
    TextView attemptsTv;
    //Boolean invalid=true;
    private int counter=5;
    private TextView userRegistration;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
   // private TextView forgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setUILoginViews();

        attemptsTv.setText("Number of attempts remaining : 5");
        
        firebaseAuth=FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(this);

    FirebaseUser user=firebaseAuth.getCurrentUser();
    if(user != null) {
        finish();
        startActivity(new Intent(LoginActivity.this, MusicLibActivity.class));
    }

        userRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String logName=loginName.getText().toString();
                String logPassword=loginPassword.getText().toString();

                if(logName.isEmpty() || logPassword.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Please enter all details", Toast.LENGTH_SHORT).show();
                } else {
                    validation(logName, logPassword);
                }
            }
        });
    }

    public void setUILoginViews() {
        loginName=(EditText)findViewById(R.id.login_name);
        loginPassword=(EditText)findViewById(R.id.login_password);
        loginButton=(Button)findViewById(R.id.login_button);
        attemptsTv=(TextView)findViewById(R.id.attemptsTv);
        userRegistration=(TextView)findViewById(R.id.registerUserTv);
    }
    public  void validation(String name, String password) {
        progressDialog.setMessage("Getting the music for you");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(name, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
              if(task.isSuccessful()) {
                  progressDialog.dismiss();
                  startActivity(new Intent(LoginActivity.this, MusicLibActivity.class));
                  Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
              } else {
                  Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                  counter--;
                  attemptsTv.setText("Number of attempts remaining "+counter);
                  progressDialog.dismiss();
                  if(counter==0){
                      loginButton.setEnabled(false);
                  }
              }
            }
        });
    }
}