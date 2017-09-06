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

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by User on 06/09/2017.
 */

public class BookingCardAdapter extends RecyclerView.Adapter<BookingCardAdapter.ViewHolder> {


    private ArrayList<PlaceDataset> listTempatFutsal;
    BookingListener listener;

    public BookingCardAdapter(ArrayList<PlaceDataset> listTempatFutsal, BookingListener listener) {
        this.listTempatFutsal = listTempatFutsal;
        this.listener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.txt_nama_tempat_detail) TextView txtNamaTempat;
        @BindView(R.id.txt_alamat_detail) TextView txtAlamat;
        @BindView(R.id.txt_harga_detail) TextView txtHarga;
        @BindView(R.id.img_detail_full) ImageView imgFull;
        @BindView(R.id.card_book)
        CardView card;


        public ViewHolder(View v){
            super(v);
            ButterKnife.bind(this, v);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_booking, parent, false);

        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final PlaceDataset tempatFutsal = listTempatFutsal.get(position);

        holder.txtNamaTempat.setText(tempatFutsal.getName());
        holder.txtAlamat.setText(tempatFutsal.getAddress());
        holder.txtHarga.setText("Rp "+editRupiah(String.valueOf(tempatFutsal.getLowPrice() ))+"  -  "+editRupiah(String.valueOf(tempatFutsal.getHighPrice())));
        //holder.imgFull.setImageResource(tempatFutsal);

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onCardClicked(tempatFutsal);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listTempatFutsal.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
    public void refreshData(ArrayList<PlaceDataset> listTempatFutsal){
        this.listTempatFutsal = listTempatFutsal;
        notifyDataSetChanged();
    }

    private String editRupiah(String rupiah){
        String edtRuiah = rupiah;
        int length = edtRuiah.length();

        //100000
        while (length>3){
            edtRuiah = edtRuiah.substring(0,(length-3))+"."+edtRuiah.substring((length-3),edtRuiah.length());
            length = length-3;
        }


        return edtRuiah;
    }
}
