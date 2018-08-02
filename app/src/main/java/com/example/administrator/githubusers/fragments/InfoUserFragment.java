package com.example.administrator.githubusers.fragments;

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

        import com.example.administrator.githubusers.App;
        import com.example.administrator.githubusers.R;
        import com.example.administrator.githubusers.models.User;
        import com.squareup.picasso.Picasso;

        import retrofit2.Call;
        import retrofit2.Callback;
        import retrofit2.Response;

@SuppressLint("ValidFragment")
public class InfoUserFragment extends Fragment {

    private ImageView avatarImageView;
    private User user;

    private TextView loginTextView;
    private TextView nameTextView;
    private TextView locationTextView;
    private TextView followersTextView;
    private TextView followingTextView;

//    private ProgressBar progressBar;
//
//    private LinearLayout noNetworkContainer;
//    private TextView tryItAgainText;

    private final static String EXTRA_USER = "INFO_USER";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_info_user, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        inItViews(view);

        if (getArguments() != null) {
            user = getArguments().getParcelable(EXTRA_USER);
        }

        App.getGithubService().getUser(user.getLogin()).enqueue(new Callback<User>() {
            @Override
            public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                if (response.isSuccessful()) {
                    user = response.body();
                    setData();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

    private void inItViews(@NonNull View view) {
//        progressBar = view.findViewById(R.id.progress_user_info);
//        noNetworkContainer = view.findViewById(R.id.Liner_info_user_container);
//        tryItAgainText = view.findViewById(R.id.Text_info_user_try_again);
        avatarImageView = view.findViewById(R.id.ImageView_user_info_avatar);

        loginTextView = view.findViewById(R.id.text_user_info_login);
        nameTextView = view.findViewById(R.id.text_user_info_name);
        locationTextView = view.findViewById(R.id.text_user_info_location);
        followersTextView = view.findViewById(R.id.text_user_info_followers);
        followingTextView = view.findViewById(R.id.text_user_info_following);
    }

    private void setData() {
        Picasso.get().load(user.getAvatarUrl()).into(avatarImageView);
        loginTextView.setText(user.getLogin());
        nameTextView.setText(user.getName());
        locationTextView.setText(user.getLocation());
        followersTextView.setText(user.getFollowers());
        followingTextView.setText(user.getFollowing());
    }

//    // ПОВТОР ЗАПРОСА
//    private void tryItAgainCallUserRequest() {
//        noNetworkContainer.setVisibility(View.GONE);
//        progressBar.setVisibility(View.VISIBLE);
//        new Thread(() -> {
//            try {
//                Thread.sleep(1000);
//                callUserRequest();
//            } catch (InterruptedException el) {
//                el.printStackTrace();
//            }
//        }).start();
//    }

// ЗАПРОС
//    private void callUserRequest() {
//        Call<User> usersCall = App.getInstance().getGithubService().getUser(user.getLogin());
//        usersCall.enqueue(new Callback<User>() {
//            @Override
//            public void onResponse(@NonNull Call<User> call, Response<User> response) {
//
//            }
//
//            @Override
//            public void onFailure(@NonNull Call<User> call, Throwable t) {
//                noNetworkContainer.setVisibility(View.VISIBLE);
//            }
//        });
//    }
}