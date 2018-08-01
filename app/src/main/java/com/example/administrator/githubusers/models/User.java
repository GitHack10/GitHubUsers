package com.example.administrator.githubusers.models;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("id")
    private int id;
    @SerializedName("login")
    private String login;
    @SerializedName("avatar_url")
    private String avatarUrl;
    @SerializedName("location")
    private String location;
    @SerializedName("name")
    private String name;
    @SerializedName("public_repos")
    private String publicRepos;
    @SerializedName("followers")
    private String followers;
    @SerializedName("following")
    private String following;

    public User(String login, int id) {
        this.login = login;
        this.id = id;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }
}