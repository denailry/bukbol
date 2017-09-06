package com.example.user.bukbol.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.bukbol.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by denail on 17/09/06.
 */

public class Matchmaking extends RecyclerView.Adapter<Matchmaking.ViewHolder> {

    private List<> itemList;

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_icon_adapt_match) ImageView ivIcon;
        @BindView(R.id.tv_challenge_adapt_match) TextView tvChallenge;
        @BindView(R.id.tv_name_adapt_match) TextView tvName;
        @BindView(R.id.tv_trophy_adapt_match) TextView tvTrophy;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_frag_matchmaking, parent, false);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}
