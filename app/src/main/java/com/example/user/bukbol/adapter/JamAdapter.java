package com.example.user.bukbol.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.bukbol.R;
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
    Context context;
    ArrayList<Boolean> listBool;
    int x;

    public JamAdapter(ArrayList<Integer> listTemporal,ArrayList<Boolean> listBool, JamListener listener, Context context,int x) {
        this.listJam = listTemporal;
        this.listener = listener;
        this.context = context;
        this.listBool = listBool;
        this.x = x;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.btn)
        Button carddosen;


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
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final int jam = listJam.get(position);
        if (x != position){
            if (listBool.get(position)){
                holder.carddosen.setBackgroundColor(context.getResources().getColor(android.R.color.white));
            }else{
                holder.carddosen.setBackgroundColor(context.getResources().getColor(R.color.colorPrimaryDark));
            }
        }else {
            holder.carddosen.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));
        }

        String hope;
        if (jam<10){
            hope = "0"+jam+".00 - ";
        }else{
            hope = jam+".00 - ";
        }

        String banget;
        if (jam+1<10){
            banget = "0"+jam+".00 ";
        }else{
            banget = jam+".00 ";
        }

        holder.carddosen.setText(hope+banget);



        holder.carddosen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listBool.get(position)){
                    listener.onJamClicked(position);
                    x = position;
                    notifyDataSetChanged();
                }else{
                    Toast.makeText(context, "anda tidak bisa memesan pada jam tersebut", Toast.LENGTH_SHORT).show();
                }
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
    public void refreshData(ArrayList<Integer> listTempatFutsal, ArrayList<Boolean> listBool){
        this.listJam = listTempatFutsal;
        this.listBool = listBool;
        notifyDataSetChanged();
    }

}
