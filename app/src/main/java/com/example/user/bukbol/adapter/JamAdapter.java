package com.example.user.bukbol.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.bukbol.R;
import com.example.user.bukbol.data.PlaceDataset;
import com.example.user.bukbol.listener.BookingListener;
import com.example.user.bukbol.listener.JamListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by User on 07/09/2017.
 */

public class JamAdapter extends RecyclerView.Adapter<JamAdapter.ViewHolder> {

    ArrayList<Integer> listJam;
    JamListener listener;

    public JamAdapter(ArrayList<Integer> listTemporal, JamListener listener) {
        this.listJam = listTemporal;
        this.listener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.txt_jam_order)
        TextView txtJam;
        @BindView(R.id.carddosen) CardView carddosen;


        public ViewHolder(View v){
            super(v);
            ButterKnife.bind(this, v);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_jam, parent, false);



        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final int jam = listJam.get(position);

        String hope;
        if (jam<10){
            hope = "0"+jam+".00 - ";
        }else{
            hope = jam+".00 - ";
        }

        String banget;
        if (jam+1<10){
            banget = "0"+jam+".00 - ";
        }else{
            banget = jam+".00 - ";
        }

        ;holder.carddosen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return listJam.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
    public void refreshData(ArrayList<Integer> listTempatFutsal){
        this.listJam = listTempatFutsal;
        notifyDataSetChanged();
    }

}
