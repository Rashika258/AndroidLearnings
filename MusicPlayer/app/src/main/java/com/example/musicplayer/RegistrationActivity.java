package com.example.musicplayer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

public class RegistrationActivity extends AppCompatActivity {
    private EditText RegisterEmail, RegisterPassword, RegisterName;
    private Button RegisterButton;
    private ProgressDialog LoadingBar;
    private TextView userLogin;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        setupUIViews();

        firebaseAuth=FirebaseAuth.getInstance();

        RegisterButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
               if(validate())
               {
                   //upload data to the database
                   String user_email=RegisterEmail.getText().toString().trim();
                   String user_password=RegisterPassword.getText().toString().trim();

                   firebaseAuth.createUserWithEmailAndPassword(user_email, user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>()
                   {
                       @Override
                       public void onComplete(@NonNull @NotNull Task<AuthResult> task)
                       {
                           if(task.isSuccessful())
                           {
                               Toast.makeText(RegistrationActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                               Intent i=new Intent(RegistrationActivity.this, LoginActivity.class);
                               startActivity(i);
                           }
                           else
                           {
                               Toast.makeText(RegistrationActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                           }
                       }
                   });
               }
            }
        });

        userLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrationActivity.this, HomeActivity.class));
            }
        });
    }
    public void setupUIViews() {
        RegisterName=(EditText)findViewById(R.id.register_name);
        RegisterPassword=(EditText)findViewById(R.id.register_password);
        RegisterEmail=(EditText)findViewById(R.id.register_email);
        RegisterButton=(Button)findViewById(R.id.reg_btn);
        userLogin=(TextView)findViewById(R.id.userLoginTv);
    }

    public Boolean validate() {
        Boolean result=false;

        String name=RegisterName.getText().toString();
        String password=RegisterPassword.getText().toString();
        String email=RegisterEmail.getText().toString();

        if (name.isEmpty() && password.isEmpty() && email.isEmpty()) {
            Toast.makeText(this, "Please enter all the details", Toast.LENGTH_SHORT).show();
        }
        else {
            result=true;
        }
        return  result;
    }
}