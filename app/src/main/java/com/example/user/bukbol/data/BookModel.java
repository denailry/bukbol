
package com.example.user.bukbol.data;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BookModel {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("dataset")
    @Expose
    private List<BookDataset> bookDataset = null;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<BookDataset> getBookDataset() {
        return bookDataset;
    }

    public void setBookDataset(List<BookDataset> bookDataset) {
        this.bookDataset = bookDataset;
    }

}
