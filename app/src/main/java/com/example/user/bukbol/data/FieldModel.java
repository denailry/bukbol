
package com.example.user.bukbol.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FieldModel {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("dataset")
    @Expose
    private List<FieldDataset> fieldDataset = null;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<FieldDataset> getFieldDataset() {
        return fieldDataset;
    }

    public void setFieldDataset(List<FieldDataset> fieldDataset) {
        this.fieldDataset = fieldDataset;
    }

}
