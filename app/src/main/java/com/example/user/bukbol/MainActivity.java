package com.example.user.bukbol;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.user.bukbol.Fragment.BookingFragment;
import com.example.user.bukbol.Fragment.MatchmakingFragment;
import com.example.user.bukbol.Fragment.ProfileFragment;
import com.example.user.bukbol.Fragment.TeamFragment;
import com.example.user.bukbol.adapter.TabFragmentPagerBookingAdapter;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;

    //variabel pengingat bottomnavigationview utama sedang dimana
    int idBottomNavigationView = R.id.action_booking;

    private TabLayout tabsBooking;
    private ViewPager pagerBooking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //inisialisasi awal awal ketika baru buka, akan mngebuka fragment booking
        if(savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flContent, new BookingFragment())
                    .commit();
        }

        //inisialisasi bottom navigasi
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        pagerBooking = (ViewPager)findViewById(R.id.pager);
        tabsBooking = (TabLayout)findViewById(R.id.tabs);
        pagerBooking.setAdapter(new TabFragmentPagerBookingAdapter(getSupportFragmentManager()));

        tabsBooking.setTabTextColors(getResources().getColor(R.color.colorPrimary),
                getResources().getColor(android.R.color.black));
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
                    fragment = new BookingFragment();
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

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flContent, fragment)
                    .commit();
        }


        return true;
    }

    private void setViewPagerandTabs(int v){

        tabsBooking.setVisibility(v);
        pagerBooking.setVisibility(v);

    }
}
