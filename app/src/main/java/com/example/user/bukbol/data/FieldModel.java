
package com.example.user.bukbol.data;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FieldModel {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("fieldDataset")
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
