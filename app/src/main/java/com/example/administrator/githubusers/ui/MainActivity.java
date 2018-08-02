package com.example.administrator.githubusers.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.administrator.githubusers.App;
import com.example.administrator.githubusers.R;
import com.example.administrator.githubusers.models.User;
import com.example.administrator.githubusers.adapters.UserItemAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private List<User> users;
    private RecyclerView userRecyclerView;
    private UserItemAdapter userItemAdapter;

    private final static String EXTRA_USER = "INFO_USER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar_info);
        setSupportActionBar(toolbar);

        userRecyclerView = findViewById(R.id.RecyclerView_main_user);

        App.getGithubService().getUsers().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful()) {
                    users = response.body();
                    userItemAdapter = new UserItemAdapter(users);
                    userItemAdapter.setOnUsersItemListener(user -> {
                        Intent intent = new Intent(MainActivity.this, InfoUserActivity.class);
                        intent.putExtra(EXTRA_USER, user);
                        startActivity(intent);
                    });
                    userRecyclerView.setAdapter(userItemAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });
    }
}