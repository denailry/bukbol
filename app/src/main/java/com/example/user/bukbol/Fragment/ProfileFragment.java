package com.example.user.bukbol.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.user.bukbol.DetailProfileActivity;
import com.example.user.bukbol.ProfileItem;
import com.example.user.bukbol.R;
import com.example.user.bukbol.adapter.ProfileAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    private ListView listView;
    private ArrayList<ProfileItem> profileItems;
    private ProfileAdapter adapter;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        profileItems = new ArrayList<>();


        profileItems.add(new ProfileItem("Account Settings",R.drawable.ic_person));
        profileItems.add(new ProfileItem("Help",R.drawable.ic_help));
        profileItems.add(new ProfileItem("Notifications",R.drawable.ic_notification));
        profileItems.add(new ProfileItem("Settings",R.drawable.ic_settings));

        adapter = new ProfileAdapter(getContext(),profileItems);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        listView = (ListView) v.findViewById(R.id.profile_lv);
        listView.setAdapter(adapter);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                ProfileItem currentItem = profileItems.get(i);
                Intent intent = new Intent(getActivity(),DetailProfileActivity.class);
                intent.putExtra("profile",currentItem.getItemName());
                startActivity(intent);

            }


        });
        return v;
    }
}