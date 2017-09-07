package com.example.user.bukbol.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.user.bukbol.API.ApiClient;
import com.example.user.bukbol.API.ApiInterface;
import com.example.user.bukbol.R;
import com.example.user.bukbol.TempatFutsalDetailActivity;
import com.example.user.bukbol.adapter.BookingCardAdapter;
import com.example.user.bukbol.data.BookDataset;
import com.example.user.bukbol.data.PlaceDataset;
import com.example.user.bukbol.data.PlaceModel;
import com.example.user.bukbol.listener.BookingListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
        Call<PlaceModel> call = service.getPlaces();
        call.enqueue(new Callback<PlaceModel>() {
            @Override
            public void onResponse(Call<PlaceModel> call, Response<PlaceModel> response) {
                List<PlaceDataset> list = response.body().getPlaceDataset();
                Log.d(TAG, "onResponse: berhasil"+list.size());
                for (int i=0; i<list.size();i++){
                    listTempatFutsal.add(list.get(i));
                }
                adapter.refreshData(listTempatFutsal);
            }

            @Override
            public void onFailure(Call<PlaceModel> call, Throwable t) {

                Log.d(TAG, "onFailure: gagal");
            }
        });

        Log.d(TAG, "callDataHomeBooking: "+listTempatFutsal.size());
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
        intent.putExtra("id",tempatFutsal.getId());
        startActivity(intent);
    }

    @Override
    public void onCardClicked(BookDataset bookDataset) {

    }

    private String ubahJam(int i){
        String hasil;

        if (i<10){
            hasil = "0"+i+".00";
        }else{
            hasil = i+"00";
        }

        Log.d(TAG, "ubahJam: "+hasil);

        return hasil;
    }
}
