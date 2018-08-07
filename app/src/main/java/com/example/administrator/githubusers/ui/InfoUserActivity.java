package com.example.administrator.githubusers.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_user);

        showInfoUserFragment(getIntent().getParcelableExtra(EXTRA_USER));
    }

    private void showInfoUserFragment(User user) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(EXTRA_USER, user);
        InfoUserFragment infoUserFragment = new InfoUserFragment();
        infoUserFragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction().
                replace(R.id.FrameLayout_infoUser_container, infoUserFragment).commit();
    }
}