package com.example.administrator.githubusers.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class User implements Parcelable {

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

//    public User(String login, int id) {
//        this.login = login;
//        this.id = id;
//    }

    protected User(Parcel in) {
        id = in.readInt();
        login = in.readString();
        avatarUrl = in.readString();
        location = in.readString();
        name = in.readString();
        publicRepos = in.readString();
        followers = in.readString();
        following = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    public String getFollowers() {
        return followers;
    }

    public String getFollowing() {
        return following;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(login);
        parcel.writeString(avatarUrl);
        parcel.writeString(location);
        parcel.writeString(name);
        parcel.writeString(publicRepos);
        parcel.writeString(followers);
        parcel.writeString(following);
    }
}