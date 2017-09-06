package com.example.user.bukbol.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.user.bukbol.R;
import com.example.user.bukbol.TempatFutsalDetailActivity;
import com.example.user.bukbol.adapter.BookingCardAdapter;
import com.example.user.bukbol.data.PlaceDataset;
import com.example.user.bukbol.listener.BookingListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookingFragment extends Fragment implements BookingListener{

    private String TAG = "LOGGING";

    SearchView searchBooking;

    ArrayList<PlaceDataset> listTempatFutsal = new ArrayList<>();

    BookingCardAdapter adapter;

    @BindView(R.id.rv_home_booking)
    RecyclerView rvHomeBooking;

    public BookingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
        rvHomeBooking.requestFocus();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_booking, container, false);
        ButterKnife.bind(this, v);

        searchBooking = (SearchView)v.findViewById(R.id.search_view_booking);


        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        searchBooking.setSubmitButtonEnabled(true);
        rvHomeBooking.requestFocus();
        searchBooking.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                searchLangsung(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });


        rvHomeBooking.setHasFixedSize(true);
        final LinearLayoutManager llm = new LinearLayoutManager(getContext());
        rvHomeBooking.setLayoutManager(llm);

        adapter = new BookingCardAdapter(listTempatFutsal, this);
        rvHomeBooking.setAdapter(adapter);
        callDataHomeBooking();

        super.onActivityCreated(savedInstanceState);
    }

    private void callDataHomeBooking() {

        PlaceDataset tim = new PlaceDataset(1,"Futsal Town","Jalan bagusrangin", 8,21,"aplikasi baik");
        listTempatFutsal.add(tim);
        listTempatFutsal.add(tim);
        listTempatFutsal.add(tim);

        adapter.refreshData(listTempatFutsal);
    }

    private void searchLangsung(String s) {

        Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onCardClicked(PlaceDataset tempatFutsal) {
        Intent intent = new Intent(getActivity(), TempatFutsalDetailActivity.class);
        intent.putExtra("namaTempat",tempatFutsal.getName());
        intent.putExtra("alamat",tempatFutsal.getAddress());
        intent.putExtra("jam",ubahJam(tempatFutsal.getOpenHour())+" - "+ubahJam(tempatFutsal.getCloseHour()));
        intent.putExtra("deskripsi",tempatFutsal.getDescription());
        startActivity(intent);
    }

    private String ubahJam(int i){
        String hasil;

        if (i<10){
            hasil = "0"+i+".00";
        }else{
            hasil = i+"00";
        }

        return hasil;
    }
}
