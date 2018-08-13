package com.example.administrator.githubusers.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.example.administrator.githubusers.R;
import com.example.administrator.githubusers.fragments.FavoritesUsersListFragment;

public class FavoritesUsersActivity extends AppCompatActivity {

    public static Intent getStartIntent(Context context) {
        return new Intent(context, FavoritesUsersActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites_users);

        setSupportActionBar(findViewById(R.id.toolbar_main));
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        getSupportFragmentManager().beginTransaction().
                replace(R.id.FrameLayout_favoritesUsers_container, new FavoritesUsersListFragment()).commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_OK);
        super.onBackPressed();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("onStop", "message onStop()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("onPause", "message onPause()");
    }
}