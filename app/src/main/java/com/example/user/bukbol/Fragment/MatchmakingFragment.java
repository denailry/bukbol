package com.example.user.bukbol.Fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SearchView;

import com.example.user.bukbol.R;
import com.example.user.bukbol.adapter.MatchmakingAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MatchmakingFragment extends Fragment {

    @BindView(R.id.dots_frag_match) LinearLayout cntDots;
    @BindView(R.id.rv_frag_match) RecyclerView rv;
    @BindView(R.id.search_frag_match) SearchView sv;
    @BindView(R.id.viewpager_frag_match) ViewPager vp;

    public MatchmakingFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_matchmaking, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(new MatchmakingAdapter());
    }
}
