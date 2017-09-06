package com.example.user.bukbol.upload;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by denail on 17/08/28.
 */

public interface BookAPI {
    String URL_FILE = "/book_in.php";

    @FormUrlEncoded
    @POST(URL_FILE)
    void insert(
            @Field("id") long id,
            @Field("place_id") long placeId,
            @Field("username") String username,
            @Field("date") String date,
            @Field("time") String time,
            @Field("price") int price,
            @Field("transfer_code") int transferCode,
            @Field("field_id") int fieldId,
            @Field("play_id") int playId,
            @Field("status") int status,
            Callback<Response> callback);
}
