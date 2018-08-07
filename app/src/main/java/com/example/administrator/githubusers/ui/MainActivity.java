package com.example.administrator.githubusers.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.administrator.githubusers.R;
import com.example.administrator.githubusers.fragments.UsersListFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar_info);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setOnMenuItemClickListener((MenuItem item) -> {
            switch (item.getItemId()) {
                case R.id.item_main_favoritesUsers:
                    startActivity(FavoritesUsersActivity.getStartIntent(MainActivity.this));
                    break;
            }
            return true;
        });

        getSupportFragmentManager().beginTransaction().
                replace(R.id.FrameLayout_main_container, new UsersListFragment()).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}