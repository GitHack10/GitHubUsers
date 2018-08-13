package com.example.administrator.githubusers.ui;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.administrator.githubusers.App;
import com.example.administrator.githubusers.R;
import com.example.administrator.githubusers.fragments.InfoUserFragment;
import com.example.administrator.githubusers.models.User;

public class InfoUserActivity extends AppCompatActivity {

    private final static String EXTRA_USER = "INFO_USER";

    public static Intent getStartIntent(Context context, User user) {
        Intent intent = new Intent(context, InfoUserActivity.class);
        intent.putExtra(EXTRA_USER, user);
        return intent;
    }

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_user);

        user = getIntent().getParcelableExtra(EXTRA_USER);
        setSupportActionBar(findViewById(R.id.toolbar_main));
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        showInfoUserFragment(user);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        switch (item.getItemId()) {
            case R.id.item_infoUser_addUser:
                item.setIcon(R.drawable.ic_check_user);
                new AddUser(user).execute();
                Toast.makeText(this, "User add in favorites", Toast.LENGTH_SHORT).show();
                break;

            case android.R.id.home:
                finish(); // close this activity and return to preview activity (if there is any)
        }
        return true;
    }

    private void showInfoUserFragment(User user) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(EXTRA_USER, user);
        InfoUserFragment infoUserFragment = new InfoUserFragment();
        infoUserFragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction().
                replace(R.id.FrameLayout_infoUser_container, infoUserFragment).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_info_user, menu);
        return true;
    }

    class AddUser extends AsyncTask<Void, Void, Void> {

        private User favoritesUser;

        AddUser(User favoritesUser) {
            this.favoritesUser = favoritesUser;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            App.getAppDatabase().userDao().insert(favoritesUser);
            return null;
        }
    }
}