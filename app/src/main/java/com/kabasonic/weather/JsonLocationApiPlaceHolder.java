package com.kabasonic.weather;

import com.kabasonic.weather.model.location.LocationRequest;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonLocationApiPlaceHolder {
    @GET("countries")
    Call<LocationRequest> getLocationRequest();
}
