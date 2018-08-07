package com.example.administrator.githubusers.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.administrator.githubusers.UserDao;
import com.example.administrator.githubusers.models.User;

@Database(entities = User.class, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDao userDao();
}