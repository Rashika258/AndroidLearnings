package com.example.workshop2.Activites;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import com.example.workshop2.R;

import static com.example.workshop2.R.layout.activity_register;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_register);
        initViews();
    }
    private void initViews() {
        EditText usernameEt=findViewById(R.id.username_et);
        EditText emailEt=findViewById(R.id.email_et);
        EditText phoneEt=findViewById(R.id.phone_et);
        EditText passwordEt=findViewById(R.id.password_et);
        Button signUpBtn=findViewById(R.id.signup_bt);
        signUpBtn.setOnClickListener(View.OnClickListener() {

        });

        String[] cityNames={"Delhi","Hyderabad","Bengaluru", "Chennai", "Mumbai"};
        Integer[] number={1,2,3};
        int stringArraySize= cityNames.length;
//        creating spinner adapter
        ArrayAdapter cityAdapter=new ArrayAdapter(RegisterActivity.this, android.R.layout.simple_spinner_dropdown_item, cityNames);
        //setting adapter of spinner
        citySpinner.setAdapter(cityAdapter);
//        create item selection for spinner
        citySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected()
        });

    }
}