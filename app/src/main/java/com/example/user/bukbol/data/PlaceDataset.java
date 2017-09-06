
package com.example.user.bukbol.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlaceDataset {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("open_hour")
    @Expose
    private Integer openHour;
    @SerializedName("close_hour")
    @Expose
    private Integer closeHour;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("low_price")
    @Expose
    private int lowPrice;
    @SerializedName("high_price")
    @Expose
    private int highPrice;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getOpenHour() {
        return openHour;
    }

    public void setOpenHour(Integer openHour) {
        this.openHour = openHour;
    }

    public Integer getCloseHour() {
        return closeHour;
    }

    public void setCloseHour(Integer closeHour) {
        this.closeHour = closeHour;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(int lowPrice) {
        this.lowPrice = lowPrice;
    }

    public int getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(int highPrice) {
        this.highPrice = highPrice;
    }
}
