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

import com.example.user.bukbol.API.ApiClient;
import com.example.user.bukbol.API.ApiInterface;
import com.example.user.bukbol.R;
import com.example.user.bukbol.ReceiptBookingActivity;
import com.example.user.bukbol.adapter.HistoryBookAdapter;
import com.example.user.bukbol.data.BookDataset;
import com.example.user.bukbol.data.BookModel;
import com.example.user.bukbol.data.PlaceDataset;
import com.example.user.bukbol.listener.BookingListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryBookingFragment extends Fragment {
    private RecyclerView rvHistori;


    private ArrayList<BookDataset> bookings = new ArrayList<>();
    private HistoryBookAdapter adapter;

    public HistoryBookingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        callDataHomeBooking();
        rvHistori.setHasFixedSize(true);
        final LinearLayoutManager llm = new LinearLayoutManager(getContext());
        rvHistori.setLayoutManager(llm);

        adapter = new HistoryBookAdapter(bookings, new BookingListener() {
            @Override
            public void onCardClicked(PlaceDataset tempatFutsal) {

            }

            @Override
            public void onCardClicked(BookDataset bookDataset) {
                Intent i = new Intent(getActivity(), ReceiptBookingActivity.class);
            }
        });

        rvHistori.setAdapter(adapter);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_history_booking, container, false);
        ButterKnife.bind(getActivity());
        rvHistori = (RecyclerView) v.findViewById(R.id.rv_histori);
        return v;
    }

    private void callDataHomeBooking() {

        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
        Call<BookModel> call = service.getBooks("");
        call.enqueue(new Callback<BookModel>() {
            @Override
            public void onResponse(Call<BookModel> call, Response<BookModel> response) {
                List<BookDataset> list = response.body().getBookDataset();
                Log.d(TAG, "onResponse: berhasil"+list.size());
                for (int i=0; i<list.size();i++){
                    bookings.add(list.get(i));
                }
                adapter.refreshData(bookings);
            }

            @Override
            public void onFailure(Call<BookModel> call, Throwable t) {

            }

        });

    }
}
