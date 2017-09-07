package com.example.user.bukbol.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.bukbol.R;
import com.example.user.bukbol.data.BookDataset;
import com.example.user.bukbol.data.PlaceDataset;
import com.example.user.bukbol.listener.BookingListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by User on 06/09/2017.
 */

public class HistoryBookAdapter extends RecyclerView.Adapter<HistoryBookAdapter.ViewHolder> {


    private ArrayList<BookDataset> bookings;
    BookingListener listener;

    public HistoryBookAdapter(ArrayList<BookDataset> bookings, BookingListener listener) {
        this.bookings = bookings;
        this.listener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.txt_nama_tempat_histori) TextView txtNamaTempat;
        @BindView(R.id.txt_alamat_histori) TextView txtAlamat;
        @BindView(R.id.txt_histori_status) TextView txtStatus;
        @BindView(R.id.txt_harga_histori) TextView txtHarga;
        @BindView(R.id.txt_tanggal_histori) TextView txtTanggal;
        @BindView(R.id.txt_jam_histori) TextView txtJam;
        @BindView(R.id.card_book) CardView card;


        public ViewHolder(View v){
            super(v);
            ButterKnife.bind(this, v);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_histori, parent, false);

        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final BookDataset bookDataset = bookings.get(position);

        holder.txtNamaTempat.setText(bookDataset.getPlaceId().toString());
        holder.txtAlamat.setText(bookDataset.getPlaceId().toString());
        holder.txtHarga.setText(bookDataset.getPrice().toString());
        holder.txtStatus.setText(bookDataset.getStatus().toString());
        holder.txtTanggal.setText(bookDataset.getDate().toString());
        holder.txtJam.setText(bookDataset.getTime().toString());

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onCardClicked(bookDataset);
            }
        });

    }

    @Override
    public int getItemCount() {
        return bookings.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
    public void refreshData(ArrayList<BookDataset> bookDatasets){
        this.bookings = bookDatasets;
        notifyDataSetChanged();
    }
}
