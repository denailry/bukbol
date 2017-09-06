package com.example.user.bukbol.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.user.bukbol.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookingFragment extends Fragment{

    private String TAG = "LOGGING";

    SearchView searchBooking;

    public BookingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_booking, container, false);

        searchBooking = (SearchView)v.findViewById(R.id.search_view_booking);
        searchBooking.setSubmitButtonEnabled(true);
        searchBooking.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                searchLangsung(s);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        return v;
    }

    private void searchLangsung(String s) {

        Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();

    }

}
