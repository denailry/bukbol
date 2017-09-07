package com.example.user.bukbol.API;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by denail on 17/09/06.
 */

public interface ApiConfig {
    @Multipart
    @POST("/dummy/person_image.php")
    Call<ServerResponse> uploadFile(@Part MultipartBody.Part file,
                                    @Part("name") RequestBody name);
}
