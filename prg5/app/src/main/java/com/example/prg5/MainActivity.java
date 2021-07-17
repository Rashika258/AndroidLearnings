package com.example.prg5;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MainActivity extends AppCompatActivity {

    private EditText username, pwd;
    private Button signup, login;
    String regularEx="=\"^(?=.*[A-Z])(?=.*[a-z])(?=.*\\\\d)(?=.*[@$!])[A-Za-z\\\\d@$!]{8,}$";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText)findViewById(R.id.username);
        pwd = (EditText)findViewById(R.id.password);
        signup = (Button)findViewById(R.id.signup);
        login = (Button)findViewById(R.id.login);

        login.setOnClickListener(this);
        signup.setOnClickListener(this);

    }

}