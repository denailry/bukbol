package com.example.user.bukbol;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toolbar;

import com.example.user.bukbol.adapter.ProfileAdapter;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayList<ProfileItem> profileItems;
    private ProfileAdapter adapter;
    //@BindView(R.id.profile_toolbar) Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.profile_toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


       initProfileList();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ProfileItem currentItem = profileItems.get(i);
                Intent intent = new Intent(ProfileActivity.this, DetailProfileActivity.class);
                intent.putExtra("profile", currentItem.getItemName());
                startActivity(intent);
            }
        });
    }

    private void initProfileList(){
        profileItems = new ArrayList<>();
        profileItems.add(new ProfileItem("Account Settings", R.drawable.ic_account));
        profileItems.add(new ProfileItem("Favorites", R.drawable.ic_favorite));
        profileItems.add(new ProfileItem("FAQ", R.drawable.ic_help));
        profileItems.add(new ProfileItem("Notifications", R.drawable.ic_notification));

        adapter = new ProfileAdapter(this, profileItems);

        listView = (ListView) findViewById(R.id.profile_lv);
        listView.setAdapter(adapter);
    }
}