package com.example.prg3login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements  View.OnClickListener{

    Button signup_btn;
    EditText suser_et, spass_et;
    String regexpass = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!])[A-Za-z\\d@$!]{8,}$";

    void initViews(){
        signup_btn = findViewById(R.id.signup_btn);
        suser_et = findViewById(R.id.suser_et);
        spass_et = findViewById(R.id.spass_et);

        signup_btn.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

    }


    @Override
    public void onClick(View v) {
        if(v.equals(signup_btn)){
            String username, password;
            username = suser_et.getText().toString();
            password = spass_et.getText().toString();
            if(username == null){
                suser_et.setError("Please fill this field");
            }
            if(password == null){
                spass_et.setError("Please fill this field");
            }
            if(validatePassword(password)){
                Bundle bundle = new Bundle();
                bundle.putString("name", username);
                bundle.putString("password", password);
                Intent loginPage = new Intent(MainActivity.this, LoginActivity.class);
                loginPage.putExtra("data", bundle);
                startActivity(loginPage);
                finish();
            }else{
                spass_et.setError("Invalid pattern");
                Toast.makeText(MainActivity.this, "Please enter valid pattern", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "No match", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean validatePassword(String password) {
        Pattern pattern = Pattern.compile(regexpass);
        Matcher matcher = pattern.matcher(password);
        if(matcher.matches())
            return true;
        return false;
    }
}

