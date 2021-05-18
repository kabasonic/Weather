package com.kabasonic.weather;

import com.kabasonic.weather.model.WeatherRequest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface JsonApiPlaceHolder {
    @GET("weather?appid=6442ac1ba02c45069a200554cf037a4d")
    Call<WeatherRequest> getWeatherData(@Query("q") String city,
                                        @Query("units") String units,
                                        @Query("lang") String language);

}
