package com.example.user.bukbol.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.user.bukbol.Fragment.BookingFragment;
import com.example.user.bukbol.Fragment.HistoryBookingFragment;

/**
 * Created by User on 05/09/2017.
 */

public class TabFragmentPagerBookingAdapter extends FragmentPagerAdapter {

    String[] title = new String[]{
            "Booking","History"
    };

    public TabFragmentPagerBookingAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = new BookingFragment();
                break;
            case 1:
                fragment = new HistoryBookingFragment();
                break;
            default:
                fragment = null;
                break;
        }
        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }

    @Override
    public int getCount() {
        return title.length;
    }

}
