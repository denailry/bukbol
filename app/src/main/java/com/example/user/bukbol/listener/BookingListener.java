package com.example.user.bukbol.listener;

import com.example.user.bukbol.data.BookDataset;
import com.example.user.bukbol.data.PlaceDataset;

/**
 * Created by User on 06/09/2017.
 */

public interface BookingListener {

    void onCardClicked(PlaceDataset tempatFutsal);
    void onCardClicked(BookDataset bookDataset);

}
