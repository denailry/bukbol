
package com.example.user.bukbol.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class

FieldDataset {

    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("place_id")
    @Expose
    private long placeId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("count")
    @Expose
    private Integer count;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(long placeId) {
        this.placeId = placeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

}
