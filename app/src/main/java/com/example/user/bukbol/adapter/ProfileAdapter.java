package com.example.user.bukbol.adapter;

import android.content.Context;
import android.media.Image;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.bukbol.ProfileItem;
import com.example.user.bukbol.R;

import java.util.ArrayList;

/**
 * Created by Adylan Roaffa on 9/6/2017.
 */

public class ProfileAdapter extends ArrayAdapter<ProfileItem> {


    public ProfileAdapter(Context context, ArrayList<ProfileItem> profileItems){
        super(context,0,profileItems);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;

        if (listItemView==null){
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.profile_item,parent,false);
        }

        final ProfileItem currentItem = getItem(position);
        TextView itemName = (TextView) listItemView.findViewById(R.id.profile_item_name);
        itemName.setText(currentItem.getItemName());

        ImageView itemImage = (ImageView) listItemView.findViewById(R.id.profile_item_image);
        itemImage.setImageResource(currentItem.getImageResource());

        return listItemView;
    }
}
