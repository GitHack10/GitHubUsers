package com.example.administrator.githubusers;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.githubusers.models.User;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class FavoriteUserItemAdapter extends RecyclerView.Adapter<FavoriteUserItemAdapter.FavoritesUseresItemViewHolder> {

    private List<User> favoritesUsers;

    public FavoriteUserItemAdapter(List<User> favoritesUsers) {
        this.favoritesUsers = favoritesUsers;
    }

    @NonNull
    @Override
    public FavoritesUseresItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_favorite_user, parent, false);
        return new FavoritesUseresItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoritesUseresItemViewHolder holder, int position) {
        holder.setFavoritesUsersData(favoritesUsers.get(position));
    }

    @Override
    public int getItemCount() {
        return favoritesUsers.size();
    }

    class FavoritesUseresItemViewHolder extends RecyclerView.ViewHolder {

        private TextView loginTextView;
        private TextView idTextView;
        private CircleImageView avatarCircleImageView;
        private ImageView removeImageView;

        FavoritesUseresItemViewHolder(View itemView) {
            super(itemView);

            loginTextView = itemView.findViewById(R.id.TextView_itemFavoriteUser_userName);
            idTextView = itemView.findViewById(R.id.TextView_itemFavoriteUser_userId1);
            avatarCircleImageView = itemView.findViewById(R.id.ImageView_itemFavoriteUser_icon);
            removeImageView = itemView.findViewById(R.id.ImageView_itemFavoriteUser_removeUser);
        }

        public void setFavoritesUsersData(User favoriteUserData) {
            Picasso.get().load(favoriteUserData.getAvatarUrl()).into(avatarCircleImageView);
            loginTextView.setText(favoriteUserData.getLogin());
            idTextView.setText(favoriteUserData.getId());
        }
    }
}