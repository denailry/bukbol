
package com.example.user.bukbol.data;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlaceModel {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("dataset")
    @Expose
    private List<PlaceDataset> placeDataset = null;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<PlaceDataset> getPlaceDataset() {
        return placeDataset;
    }

    public void setPlaceDataset(List<PlaceDataset> placeDataset) {
        this.placeDataset = placeDataset;
    }

}
