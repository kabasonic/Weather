package com.kabasonic.weather;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kabasonic.weather.model.Weather;
import com.kabasonic.weather.model.WeatherRequest;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private JsonApiPlaceHolder jsonApiPlaceHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                //using Interceptor for headers
                .addInterceptor(new Interceptor() {
                    @NotNull
                    @Override
                    public okhttp3.Response intercept(@NotNull Chain chain) throws IOException {
                        okhttp3.Request originalRequest = chain.request();

                        okhttp3.Request newRequest = originalRequest.newBuilder()
                                //using once request, if need more, use .addheader()

                                .build();


                        return chain.proceed(newRequest);
                    }
                })
                .build();

        Gson gson = new GsonBuilder().serializeNulls().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        jsonApiPlaceHolder = retrofit.create(JsonApiPlaceHolder.class);

        fetchWeather();
    }

    private void getCoord(WeatherRequest request){
        Log.i("Coord", request.getCoord().toString());
    }

    private void getWeather(WeatherRequest request){
        List<Weather> callWeatherList = request.getWeather();
        for(Weather weather: callWeatherList){
            Log.i("Weather", request.getWeather().toString());
        }
    }

    private void getClouds(WeatherRequest request){
        Log.i("Clouds",request.getClouds().toString());
    }

    private void getMain(WeatherRequest request){
        Log.i("Main",request.getMain().toString());
    }

    private void getSys(WeatherRequest request){
        Log.i("Sys",request.getSys().toString());
    }

    private void getWind(WeatherRequest request){
        Log.i("Wind",request.getWind().toString());
    }



    private void fetchWeather(){
        Call<WeatherRequest> weatherCall = jsonApiPlaceHolder.getWeatherData("Lublin","metric","en");
        weatherCall.enqueue(new Callback<WeatherRequest>() {
            @Override
            public void onResponse(Call<WeatherRequest> call, Response<WeatherRequest> response) {
                if (!response.isSuccessful()) {
                    Log.d(getClass().getSimpleName(), "onResponse|postList|Message" + response.message());
                    return;
                }
                WeatherRequest callWeather = response.body();

                //coord
                getCoord(callWeather);
                //weather
                getWeather(callWeather);
                //main
                getMain(callWeather);
                //wind
                getWind(callWeather);
                //clouds
                getClouds(callWeather);
                //sys
                getSys(callWeather);
                //different data
                callWeather.toString();
            }

            @Override
            public void onFailure(Call<WeatherRequest> call, Throwable t) {
                Log.d(getClass().getSimpleName(), "onFailure|postsList|Message: " + t.getMessage());
            }
        });
    }
}