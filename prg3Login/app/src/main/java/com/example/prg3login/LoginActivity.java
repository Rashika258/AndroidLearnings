package com.example.prg3login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    Button login_btn;
    EditText lpass_et, luser_et;
    int count = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();
    }

    private void initViews() {
        login_btn = findViewById(R.id.signin_btn);
        lpass_et = findViewById(R.id.lpass_et);
        luser_et = findViewById(R.id.luser_et);

        login_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.equals(login_btn)){

            if(validate()) {
                Intent home = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(home);
                finish();
            }else{
                count--;
                if(count<=0){
                    login_btn.setEnabled(false);
                    Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "Number of attempts remaining: "+count, Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private boolean validate() {
        String username, password, entered_username, entered_password;
        Bundle bundle = getIntent().getBundleExtra("data");
        username = bundle.getString("name");
        password = bundle.getString("password");
        entered_password = lpass_et.getText().toString();
        entered_username = luser_et.getText().toString();
        if(entered_username == null){
            luser_et.setError("Please fill this field");
        }
        if(entered_password == null){
            lpass_et.setError("Please fill this field");
        }
        if(entered_password.equals(password) && entered_username.equals(username)){
            return true;
        }
        return false;
    }
}

