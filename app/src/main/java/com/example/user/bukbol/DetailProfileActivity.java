package com.example.user.bukbol;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class DetailProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent prevIntent = getIntent();
        String profileType = prevIntent.getStringExtra("profile");
        switch (profileType){
            case "Help":{
                setContentView(R.layout.profile_help);
                break;
            }
            case "Account Settings":{
                setContentView(R.layout.profile_account);
            }
            case "Notifications":{
                setContentView(R.layout.profile_notification);
            }
            case "Settings":{
                setContentView(R.layout.profile_setting);
            }
        }

    }

}
