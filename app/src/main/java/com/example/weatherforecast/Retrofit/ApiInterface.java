package com.example.weatherforecast.Retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("weather?appid=f006f8073564b2a2a61f708f16443ab2&units=metric")
    Call<Example> getWeatherData(@Query("q") String name);
}
