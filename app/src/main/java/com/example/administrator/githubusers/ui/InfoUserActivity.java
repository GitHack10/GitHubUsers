package com.example.administrator.githubusers.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.githubusers.R;
import com.example.administrator.githubusers.adapters.UserItemAdapter;
import com.example.administrator.githubusers.models.User;
import com.squareup.picasso.Picasso;

import java.util.List;

public class InfoUserActivity extends AppCompatActivity {

    private final static String EXTRA_USER = "INFO_USER";
    private List<User> users;

    private ImageView userImageView;
    private TextView userBioTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_user);
    }
}