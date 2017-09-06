package com.example.user.bukbol.upload;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by denail on 17/08/28.
 */

public interface PlaceAPI {
    String URL_FILE = "/place_in.php";

    @FormUrlEncoded
    @POST(URL_FILE)
    void insert(
            @Field("id") long id,
            @Field("name") String name,
            @Field("address") String address,
            @Field("open_hour") int open_hour,
            @Field("close_hour") int close_hour,
            @Field("description") String description,
            Callback<Response> callback);
}
