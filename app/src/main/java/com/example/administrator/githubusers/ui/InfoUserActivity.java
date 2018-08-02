package com.example.administrator.githubusers.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.githubusers.R;
import com.example.administrator.githubusers.fragments.InfoUserFragment;
import com.example.administrator.githubusers.models.User;

public class InfoUserActivity extends AppCompatActivity {

    private final static String EXTRA_USER = "INFO_USER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_user);

//        showFragment(R.id.FrameLayout_infoUser_container, new InfoUserFragment(), this);
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
    public void showFragment(int container, Fragment fragment,
                                         FragmentActivity fragmentActivity) {
        fragmentActivity.getSupportFragmentManager().beginTransaction()
                .add(container, fragment).commit();
    }
}