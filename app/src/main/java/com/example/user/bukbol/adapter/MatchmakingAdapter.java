package com.example.user.bukbol.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.bukbol.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by denail on 17/09/06.
 */

public class MatchmakingAdapter extends RecyclerView.Adapter<MatchmakingAdapter.ViewHolder> {

    private final String TV_CHALLENGE = "Challenge";
    private final String TV_CANCEL = "Cancel";

    private List<Integer> itemList;

    public MatchmakingAdapter() {
        this.itemList = new ArrayList<>();
    }

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

    @OnClick(R.id.tv_challenge_adapt_match)
    public void toggleChallenge(View view) {
        TextView tvChallenge = (TextView) view;
        String state = tvChallenge.getText().toString();
        if(state.equals(TV_CHALLENGE)) {
            tvChallenge.setText(TV_CANCEL);
            tvChallenge.setBackgroundColor(Color.parseColor("#000000"));
            tvChallenge.setTextColor(Color.parseColor("#ffffff"));
        } else {
            tvChallenge.setText(TV_CHALLENGE);
            tvChallenge.setBackgroundColor(Color.parseColor("#ff9900"));
            tvChallenge.setTextColor(Color.parseColor("#000000"));
        }
    }
}
