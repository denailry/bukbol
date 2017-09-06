package com.example.user.bukbol.upload;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by denail on 17/08/28.
 */

public interface PersonAPI {
    String URL_FILE = "/person_in.php";

    @FormUrlEncoded
    @POST(URL_FILE)
    void insert(
            @Field("username") String username,
            @Field("name") String name,
            @Field("email") String email,
            @Field("phone") String phone,
            @Field("address") String address,
            @Field("password") String password,
            @Field("date") String dat,
            Callback<Response> callback);
}
