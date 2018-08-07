package com.example.administrator.githubusers.fragments;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.githubusers.App;
import com.example.administrator.githubusers.R;
import com.example.administrator.githubusers.adapters.UserItemAdapter;
import com.example.administrator.githubusers.models.User;
import com.example.administrator.githubusers.ui.InfoUserActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class UsersListFragment extends Fragment {

    private List<User> users;
    private RecyclerView userRecyclerView;
    private UserItemAdapter userItemAdapter;
    private Call<List<User>> usersCall;

    private final static String EXTRA_USER = "INFO_USER";
    private static final int REQUEST_CODE_USER_INFO = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_users_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        userRecyclerView = view.findViewById(R.id.RecyclerView_main_user);
        new GetFavoritesUsersId().execute();

//        usersCall = App.getGithubService().getUsers();
//        usersCall.enqueue(new Callback<List<User>>() {
//            @Override
//            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
//                if (response.isSuccessful()) {
//                    users = response.body();
//                    userItemAdapter = new UserItemAdapter(users, idFavoirtesUsers);
//                    userItemAdapter.setOnUsersItemListener(user -> {
//                        Intent intent = new Intent(getActivity(), InfoUserActivity.class);
//                        intent.putExtra(EXTRA_USER, user);
//                        startActivity(intent);
//                    });
//                    userRecyclerView.setAdapter(userItemAdapter);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<User>> call, Throwable t) {
//                if (!usersCall.isCanceled()) {
//                    //
//                }
//            }
//        });
    }

    private void callUsersRequest(List<Integer> idFavoirtesUsers) {
        Call<List<User>> userCall = App.getGithubService().getUsers();
        userCall.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(@NonNull Call<List<User>> call, @NonNull Response<List<User>> response) {
                if (response.isSuccessful()) {
                    users = response.body();
                    userItemAdapter = new UserItemAdapter(users, idFavoirtesUsers);
                    userItemAdapter.setOnUsersItemListener(user ->
                            startActivityForResult(InfoUserActivity.
                                    getStartIntent(getContext(), user), REQUEST_CODE_USER_INFO));
                    userRecyclerView.setAdapter(userItemAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE_USER_INFO) {
            new GetFavoritesIdForExitActivityUserAsyncTask().execute();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (usersCall != null) {
            usersCall.cancel();
        }
    }

    @SuppressLint("StaticFieldLeak")
    class GetFavoritesUsersId extends AsyncTask<Void, Void, List<Integer>> {

        @Override
        protected List<Integer> doInBackground(Void... voids) {
            List<User> favoritesUsers = App.getAppDatabase().userDao().getUsers();
            List<Integer> idFavoriteUser = new ArrayList<>();

            for (User favoriteUser : favoritesUsers) idFavoriteUser.add(favoriteUser.getId());
            return idFavoriteUser;
        }

        @Override
        protected void onPostExecute(List<Integer> integers) {
            callUsersRequest(integers);
        }
    }

    @SuppressLint("StaticFieldLeak")
    private class GetFavoritesIdForExitActivityUserAsyncTask extends AsyncTask<Void, Void, List<Integer>> {
        @Override
        protected List<Integer> doInBackground(Void... voids) {
            List<User> favoritesUsers = App.getAppDatabase().userDao().getUsers();
            List<Integer> idFavoritesUsers = new ArrayList<>();

            for (User favoriteUser : favoritesUsers) idFavoritesUsers.add(favoriteUser.getId());

            return idFavoritesUsers;
        }

        @Override
        protected void onPostExecute(List<Integer> integers) {
            userItemAdapter.setIdFavoritesUsers(integers);
        }
    }
}