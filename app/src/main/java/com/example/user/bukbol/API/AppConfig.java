package com.example.user.bukbol.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by denail on 17/09/06.
 */

public class AppConfig {
    private static String BASE_URL = "http://rupy.levyson.com/ ";
    public static Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(AppConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
