package com.example.administrator.githubusers.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.administrator.githubusers.R;
import com.example.administrator.githubusers.fragments.UsersListFragment;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_FAVORITES = 2;
    private static final String TAG = "tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        showUsersListFragment();

        getSupportFragmentManager().beginTransaction().
                replace(R.id.FrameLayout_main_container, new UsersListFragment(), TAG).commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_main_favoritesUsers:
                getSupportFragmentManager().findFragmentByTag(TAG).startActivityForResult(FavoritesUsersActivity
                        .getStartIntent(MainActivity.this), REQUEST_CODE_FAVORITES);
                break;
        }
        return true;
    }

    private void showUsersListFragment() {
        UsersListFragment usersListFragment = new UsersListFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.FrameLayout_main_container, usersListFragment, TAG).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}