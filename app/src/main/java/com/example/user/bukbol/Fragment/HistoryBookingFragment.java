package com.example.user.bukbol.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.bukbol.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryBookingFragment extends Fragment {


    public HistoryBookingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history_booking, container, false);
    }

}
