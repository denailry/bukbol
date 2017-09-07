package com.example.user.bukbol.API;

import com.example.user.bukbol.data.BookModel;
import com.example.user.bukbol.data.FieldModel;
import com.example.user.bukbol.data.PersonModel;
import com.example.user.bukbol.data.PlaceModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Adylan Roaffa on 9/7/2017.
 */

public interface ApiInterface {

    @GET("person.php")
    Call<PersonModel> getUsernames(@Query("username") String username);

    @GET("field.php")
    Call<FieldModel> getFields(@Query("place_id") long id);

    @GET("field.php")
    Call<FieldModel> getFields();


    @GET("book.php")
    Call<BookModel> getBooks(@Query("username") String username);

    @GET("place.php")
    Call<PlaceModel> getPlaces(@Query("id") long id);

    @GET("place.php")
    Call<PlaceModel> getPlaces();


}
