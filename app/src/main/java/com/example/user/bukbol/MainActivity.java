package com.example.user.bukbol;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.user.bukbol.adapter.TabFragmentPagerBookingAdapter;

public class MainActivity extends AppCompatActivity {

    private String TAG = "LOGGING";

    private TabLayout tabsBooking;
    private ViewPager pagerBooking;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setTitle("");

        pagerBooking = (ViewPager)findViewById(R.id.pager);
        tabsBooking = (TabLayout)findViewById(R.id.tabs);
        pagerBooking.setAdapter(new TabFragmentPagerBookingAdapter(getSupportFragmentManager()));

        tabsBooking.setTabTextColors(getResources().getColor(R.color.colorPrimaryDark),
                getResources().getColor(android.R.color.white));
        tabsBooking.setupWithViewPager(pagerBooking);
        tabsBooking.setTabGravity(TabLayout.GRAVITY_FILL);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menus, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        Intent intent;

        switch (item.getItemId()){
            case R.id.profil :
                intent = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(intent);
                break;
        }

        return true;
    }
}
