package com.example.user.bukbol;

/**
 * Created by Adylan Roaffa on 9/6/2017.
 */

public class ProfileItem {

    private String itemName;
    private int imageResource;

    public ProfileItem(String itemName, int imageResource) {
        this.itemName = itemName;
        this.imageResource = imageResource;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }
}
