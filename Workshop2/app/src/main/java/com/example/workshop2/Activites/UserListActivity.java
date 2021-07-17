package com.example.workshop2.Activites;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.workshop2.ModelClass.UserListModel;
import com.example.workshop2.R;

public class UserListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        populateData();
    }
    private void populateData() {

        List <UserListModel> userList=new ArrayList<>();
        UserListModel userModel=new UserListModel("1","Rashika","9448673537", "rashika@gmail.com","Bangalore" );
        userList.add(userModel);
         userModel=new UserListModel("1","Rashika","9448673537", "rashika@gmail.com","Bangalore" );

    }

}