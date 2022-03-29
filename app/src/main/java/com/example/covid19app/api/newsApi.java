package com.example.covid19app.api;

import java.nio.file.attribute.AclEntry;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class newsApi
{
    private static final String BASE_URL = "https://newsapi.org/v2/";
    private static newsApi newsapi;
    private static Retrofit retrofit;

    private newsApi()
    {
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();

    }

    public static synchronized newsApi getInstance()
    {
        if(newsapi == null)
        {
            newsapi = new newsApi();
        }

        return newsapi;
    }



    public newsApiInterface getApi()
    {
        return retrofit.create(newsApiInterface.class);
    }
}
