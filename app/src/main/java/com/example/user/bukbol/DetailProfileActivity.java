package com.example.user.bukbol;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import static android.R.string.no;

public class DetailProfileActivity extends AppCompatActivity {

    private ArrayList<String> notifications;
    private ListView notificationLV;
    private ArrayAdapter<String> notificationAdapter;
    private android.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent prevIntent = getIntent();
        String profileType = prevIntent.getStringExtra("profile");


        notifications = new ArrayList<>();

        switch (profileType){
            case "FAQ":{
                setContentView(R.layout.profile_faq);
                break;
            }
            case "Account Settings":{
                setContentView(R.layout.profile_account);
                break;
            }
            case "Notifications":{
                setContentView(R.layout.profile_notification);
                notificationLV = (ListView) findViewById(R.id.notification_lv);
                initNotifications();
                break;
            }
            case "Settings":{
                setContentView(R.layout.profile_setting);
                break;
            }
        }

        toolbar = (android.widget.Toolbar) findViewById(R.id.profile_toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               finish();
            }
        });



    }


    private void initNotifications(){


        notifications.add("Selamat");
        notifications.add("Selamat");
        notifications.add("Selamat");
        notifications.add("Selamat");

        notificationAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,notifications);

        notificationLV.setAdapter(notificationAdapter);
    }
}
