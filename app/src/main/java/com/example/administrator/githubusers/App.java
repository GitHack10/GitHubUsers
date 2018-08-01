package com.example.administrator.githubusers;

import android.app.Application;

import com.example.administrator.githubusers.networks.GithubService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {

    private static final String BASE_URL = "https://api.github.com/";
    private static GithubService githubService;

    @Override
    public void onCreate() {
        super.onCreate();
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().
                addInterceptor(httpLoggingInterceptor).build();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).
                client(okHttpClient).addConverterFactory(GsonConverterFactory.create()).build();

        githubService = retrofit.create(GithubService.class);
    }

    public static GithubService getGithubService() {
        return githubService;
    }
}