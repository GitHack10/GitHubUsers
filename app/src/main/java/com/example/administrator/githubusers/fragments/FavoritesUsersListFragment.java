package com.example.administrator.githubusers.fragments;


import android.annotation.SuppressLint;
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
import com.example.administrator.githubusers.adapters.FavoriteUserItemAdapter;
import com.example.administrator.githubusers.models.User;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavoritesUsersListFragment extends Fragment {

    private RecyclerView favoritesUsersRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorites_users_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        favoritesUsersRecyclerView = view.findViewById(R.id.RecyclerView_fragmentFavoritesUsers_user);
        new ShowFavoritesUsers().execute();
    }

    @SuppressLint("StaticFieldLeak")
    class ShowFavoritesUsers extends AsyncTask<Void, Void, List<User>> {

        @Override
        protected List<User> doInBackground(Void... voids) {
            return App.getAppDatabase().userDao().getUsers();
        }

        @Override
        protected void onPostExecute(List<User> favoritesUsers) {
            FavoriteUserItemAdapter favoriteUserItemAdapter = new FavoriteUserItemAdapter(favoritesUsers);
            favoritesUsersRecyclerView.setAdapter(favoriteUserItemAdapter);
            favoriteUserItemAdapter.setOnRemoveItemListener(favoriteUser -> new RemoveUser(favoriteUser).execute());
        }
    }

    @SuppressLint("StaticFieldLeak")
    class RemoveUser extends AsyncTask<Void, Void, User> {

        private User favoriteUser;

        RemoveUser(User favoriteUser) {
            this.favoriteUser = favoriteUser;
        }

        @Override
        protected User doInBackground(Void... voids) {
            App.getAppDatabase().userDao().delete(favoriteUser);
            return favoriteUser;
        }

        @Override
        protected void onPostExecute(User user) {
            new ShowFavoritesUsers().execute();
        }
    }
}