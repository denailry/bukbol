package com.example.user.bukbol.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.bukbol.data.TempatFutsal;
import com.example.user.bukbol.listener.BookingListener;

import java.util.ArrayList;

/**
 * Created by User on 06/09/2017.
 */

public class BookingCardAdapter extends RecyclerView.Adapter<BookingCardAdapter.ViewHolder> {


    private ArrayList<TempatFutsal> listTempatFutsal;
    BookingListener listener;

    public BookingCardAdapter(ArrayList<TempatFutsal> listTempatFutsal, BookingListener listener) {
        this.listTempatFutsal = listTempatFutsal;
        this.listener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtNamaTempat
                ,txtAlamat
                ,txtJam
                ,txtHarga;
        ImageView imgFull;

        public ViewHolder(View v){
            super(v);

        }
    }


    @Override
    public int getItemCount() {
        return listTempatFutsal.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
    public void refreshData(ArrayList<TempatFutsal> listTempatFutsal){
        this.listTempatFutsal = listTempatFutsal;
        notifyDataSetChanged();
    }
}
