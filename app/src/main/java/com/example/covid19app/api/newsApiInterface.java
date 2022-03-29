package com.example.covid19app.api;

import com.example.covid19app.NewsModel.NewsModel;

import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.Call;

public interface newsApiInterface
{
    @GET("top-headlines")
    Call<NewsModel> getHeadlines(
            @Query("q") String keyword,
        @Query("country") String country,
        @Query("apiKey") String apiKey
    );
}
