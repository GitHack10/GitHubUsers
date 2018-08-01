package com.example.administrator.githubusers.networks;

import com.example.administrator.githubusers.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GithubService {

    @GET("users")
    Call<List<User>> getUsers();
}