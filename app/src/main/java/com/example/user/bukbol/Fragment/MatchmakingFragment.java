package com.example.user.bukbol.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.bukbol.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MatchmakingFragment extends Fragment {

    @BindView(R.id.tv_challenge_adapt_match) TextView tvChallenge;
    @BindView(R.id.dots_frag_match) LinearLayout cntDots;
    @BindView(R.id.iv_icon_adapt_match) ImageView ivIcon;

    public MatchmakingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_matchmaking, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

}
