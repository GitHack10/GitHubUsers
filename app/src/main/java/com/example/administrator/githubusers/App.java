package com.example.administrator.githubusers;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.example.administrator.githubusers.database.AppDatabase;
import com.example.administrator.githubusers.networks.GithubService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {

    private static final String BASE_URL = "https://api.github.com/";

    private static GithubService githubService;
    private static AppDatabase appDatabase;

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

        appDatabase = Room.databaseBuilder(this, AppDatabase.class, "database").build();
    }

    public static GithubService getGithubService() {
        return githubService;
    }

    public static AppDatabase getAppDatabase() {
        return appDatabase;
    }
}