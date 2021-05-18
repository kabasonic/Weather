package com.kabasonic.weather;

import com.kabasonic.weather.model.WeatherRequest;
import com.kabasonic.weather.model.daily.DailyRequest;
import com.kabasonic.weather.model.location.LocationRequest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JsonWeatherApiPlaceHolder {
    @GET("weather?appid=6442ac1ba02c45069a200554cf037a4d")
    Call<WeatherRequest> getWeatherData(@Query("q") String city,
                                        @Query("units") String units,
                                        @Query("lang") String language);
    @GET("onecall?appid=6442ac1ba02c45069a200554cf037a4d")
    Call<DailyRequest> getDailyWeatherData(@Query("lat") float lat,
                                           @Query("lon") float lon,
                                           @Query("exclude") String exclude,
                                           @Query("units") String units,
                                           @Query("lang") String language);
}
