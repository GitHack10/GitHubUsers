package com.example.administrator.githubusers.adapters;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.githubusers.App;
import com.example.administrator.githubusers.R;
import com.example.administrator.githubusers.models.User;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserItemAdapter extends RecyclerView.Adapter<UserItemAdapter.ItemViewHolder>  {

    private List<User> users;
    private List<Integer> idFavoritesUsers;
    private OnUsersItemListener onUsersItemListener;

    public UserItemAdapter(List<User> users, List<Integer> idFavoritesUsers) {
        this.users = users;
        this.idFavoritesUsers = idFavoritesUsers;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_user, parent, false);

        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.setData(users.get(position));
        String s = "";
    }

    @Override
    public long getItemId(int position) {
        return users.get(position).getId();
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public void setOnUsersItemListener(OnUsersItemListener onUsersItemListener) {
        this.onUsersItemListener = onUsersItemListener;
    }

    public void setIdFavoritesUsers(List<Integer> idFavoritesUsers) {
        this.idFavoritesUsers = idFavoritesUsers;
    }

    public interface OnUsersItemListener {
        void onUserItemClick(User user);
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        private CircleImageView userCircleImageView;
        private ImageView addUserImageView;

        private TextView userNameTextView;
        private TextView userIdTextView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            initViews(itemView);
        }

        private void initViews(View itemView) {
            userCircleImageView = itemView.findViewById(R.id.ImageView_itemUser_icon);
            addUserImageView = itemView.findViewById(R.id.ImageView_itemUser_addUser);

            userNameTextView = itemView.findViewById(R.id.TextView_itemUser_userName);
            userIdTextView = itemView.findViewById(R.id.TextView_itemUser_userId1);
        }

        public void setData(User userData) {

            Picasso.get().load(userData.getAvatarUrl()).into(userCircleImageView);
            userNameTextView.setText(userData.getLogin());
            userIdTextView.setText(String.valueOf(userData.getId()));

            for (int i : idFavoritesUsers) {
                if (i == userData.getId()) {
                    addUserImageView.setImageResource(R.drawable.ic_check_user);
                    break;
                } else {
                    addUserImageView.setImageResource(R.drawable.ic_add_user);
                }
            }

            addUserImageView.setOnClickListener(view -> {
                new AddUser(userData).execute();
                idFavoritesUsers.add(userData.getId());

                for (int i : idFavoritesUsers) {
                    if (i == userData.getId()) {
                        addUserImageView.setImageResource(R.drawable.ic_check_user);
                        break;
                    } else {
                        addUserImageView.setImageResource(R.drawable.ic_add_user);
                    }
                }
                Toast.makeText(itemView.getContext(), itemView.getContext().getResources()
                        .getString(R.string.msg_addUser_in_favorites), Toast.LENGTH_SHORT).show();
            });

            itemView.setOnClickListener(view -> {
                if (onUsersItemListener != null) onUsersItemListener.onUserItemClick(userData);
            });
        }
    }

    class AddUser extends AsyncTask<Void, Void, Void>{

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