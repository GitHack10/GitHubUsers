package com.example.administrator.githubusers.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.githubusers.R;
import com.example.administrator.githubusers.models.User;
import com.squareup.picasso.Picasso;

@SuppressLint("ValidFragment")
public class InfoUserFragment extends Fragment {

    private ImageView avatarImageView;
    private User user;

    private TextView loginDefaultTextView;
    private TextView loginTextView;

    private final static String EXTRA_USER = "INFO_USER";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_info_user, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        inItViews(view);

        if (getArguments() != null) user = getArguments().getParcelable(EXTRA_USER);
    }

    private void inItViews(@NonNull View view) {
        loginDefaultTextView = view.findViewById(R.id.TextView_info_bioUser);
        avatarImageView = view.findViewById(R.id.ImageView_info_imgUser);
    }

    private void setData() {
        Picasso.get().load(user.getAvatarUrl()).into(avatarImageView);
        loginTextView.setText(user.getLogin());
    }
}