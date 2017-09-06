package com.example.user.bukbol.upload;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by denail on 17/08/28.
 */

public interface FieldAPI {
    String URL_FILE = "/field_in.php";

    @FormUrlEncoded
    @POST(URL_FILE)
    void insert(
            @Field("id") long id,
            @Field("place_id") long placeId,
            @Field("name") String name,
            @Field("description") String description,
            @Field("count") int count,
            Callback<Response> callback);
}
