package com.example.user.bukbol;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.example.user.bukbol.Fragment.MatchmakingFragment;
import com.example.user.bukbol.Fragment.ProfileFragment;
import com.example.user.bukbol.Fragment.TeamFragment;
import com.example.user.bukbol.adapter.TabFragmentPagerBookingAdapter;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private String TAG = "LOGGING";

    BottomNavigationView bottomNavigationView;

    //variabel pengingat bottomnavigationview utama sedang dimana
    int idBottomNavigationView = R.id.action_booking;
    Fragment prevFragment = null;

    private TabLayout tabsBooking;
    private ViewPager pagerBooking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //inisialisasi bottom navigasi
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        pagerBooking = (ViewPager)findViewById(R.id.pager);
        tabsBooking = (TabLayout)findViewById(R.id.tabs);
        pagerBooking.setAdapter(new TabFragmentPagerBookingAdapter(getSupportFragmentManager()));

        tabsBooking.setTabTextColors(getResources().getColor(R.color.colorAccent),
                getResources().getColor(android.R.color.white));
        tabsBooking.setupWithViewPager(pagerBooking);
        tabsBooking.setTabGravity(TabLayout.GRAVITY_FILL);
        setViewPagerandTabs(View.VISIBLE);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Fragment fragment = null;


        if (item.getItemId() != idBottomNavigationView){
            switch (item.getItemId()){
                case R.id.action_booking:
                    idBottomNavigationView = item.getItemId();
                    setViewPagerandTabs(View.VISIBLE);
                    break;
                case R.id.action_match:
                    fragment = new MatchmakingFragment();
                    idBottomNavigationView = item.getItemId();
                    setViewPagerandTabs(View.GONE);
                    break;
                case R.id.action_team:
                    fragment = new TeamFragment();
                    idBottomNavigationView = item.getItemId();
                    setViewPagerandTabs(View.GONE);
                    break;
                case R.id.action_profile:
                    fragment = new ProfileFragment();
                    idBottomNavigationView = item.getItemId();
                    setViewPagerandTabs(View.GONE);
                    break;
            }

            if (idBottomNavigationView != R.id.action_booking){
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flContent, fragment)
                        .commit();
            }else{
                Log.d(TAG, "onNavigationItemSelected: ");
                getSupportFragmentManager()
                        .beginTransaction()
                        .remove(prevFragment)
                        .commit();
            }

            prevFragment = fragment;
        }


        return true;
    }

    private void setViewPagerandTabs(int v){

        tabsBooking.setVisibility(v);
        pagerBooking.setVisibility(v);

    }
}
