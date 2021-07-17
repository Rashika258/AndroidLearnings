package com.example.workshop2.Activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.workshop2.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {
    EditText emailEt, passEt;
    private boolean isCheckBoxChecked=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();

    }
    private  void initViews() {
        emailEt=findViewById(R.id.email_et);
      passEt=findViewById(R.id.pass_et);
        CheckBox checkBx=findViewById(R.id.check_bx);
        Button loginBtn=findViewById(R.id.login_btn);
        TextView regText=findViewById(R.id.reg_tv);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(LoginActivity.this, "Login Button Clicked", Toast.LENGTH_LONG).show();
                validation();
            }
        });

        regText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regIntent=new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(regIntent);
            }
        });

        checkBx.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(LoginActivity.this, "checkbox + b",Toast.LENGTH_LONG).show();
                /*
                *checkBx
                 */
            }
        });

    }
    private  void validation() {
        String email=emailEt.getText().toString();
        String password=passEt.getText().toString();
        boolean isValidEmail=true;
        boolean isValidPassword=true;
        String regex="^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern=Pattern.compile(regex);
        if(email.length()>5&&email!=null) {
            Matcher matcher=pattern.matcher(email);
            if(matcher.matches()) {
                Toast.makeText(LoginActivity.this, "Pattern matches match is full", Toast.LENGTH_LONG).show();

            } else {
                Toast.makeText(LoginActivity.this, "Pattern doesn't match", Toast.LENGTH_LONG).show();
                isValidEmail=false;
                emailEt.setError("Pattern mismatch");
            }
        } else {
            Toast.makeText(LoginActivity.this, "Invalid email", Toast.LENGTH_LONG).show();
            isValidEmail=false;
        }

        if(password.length()<8) {
            isValidPassword=false;
            Toast.makeText(LoginActivity.this, "Length is lesser than 8", Toast.LENGTH_LONG).show();
        }
        if(isValidEmail&&isValidPassword) {
            Toast.makeText(LoginActivity.this, "Valid email and password", Toast.LENGTH_LONG).show();
        }
    }
}