
package com.example.user.bukbol.data;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PersonModel {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("dataset")
    @Expose
    private List<PersonDataset> personDataset = null;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<PersonDataset> getPersonDataset() {
        return personDataset;
    }

    public void setPersonDataset(List<PersonDataset> personDataset) {
        this.personDataset = personDataset;
    }

}
