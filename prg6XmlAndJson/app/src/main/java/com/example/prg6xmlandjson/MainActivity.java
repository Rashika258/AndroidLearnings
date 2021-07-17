package com.example.prg6xmlandjson;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnParseXml,btnParseJson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnParseXml=(Button)findViewById(R.id.btnParseXml);
        btnParseJson=(Button)findViewById(R.id.btnParseJson);
        btnParseJson.setOnClickListener((View.OnClickListener) this);
        btnParseXml.setOnClickListener((View.OnClickListener) this);
    }
    public void onClick(View v) {
        if(v.equals(btnParseJson)) {
            Intent it=new Intent(this,ViewActivity.class);
            it.putExtra("mode",1);
            startActivity(it);
        }
        else if(v.equals(btnParseXml)) {
            Intent it=new Intent(this, ViewActivity.class);
            it.putExtra("mode",2);
            startActivity(it);
        }
    }
}