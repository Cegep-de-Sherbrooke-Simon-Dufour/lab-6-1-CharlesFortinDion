package com.example.lab6.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab6.R;
import com.example.lab6.data.User;

public class UserAdapter extends ListAdapter<User, UserAdapter.ViewHolder> {
    RecyclerCallback<User> callback = (u) -> {};

    public void setCallback(RecyclerCallback<User> callback) {
        this.callback = callback;
    }

    protected UserAdapter() {
        super(new DiffUtil.ItemCallback<User>() {
            @Override
            public boolean areItemsTheSame(@NonNull User oldUser, @NonNull User newUser) {
                return oldUser == newUser;
            }
            @Override
            public boolean areContentsTheSame(@NonNull User oldUser, @NonNull User newUser) {
                return oldUser.getNom().equals(newUser.getNom()) && oldUser.getEmail().equals(newUser.getEmail());
            }
        });
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setItem(getItem(position));
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView nom;
        private final TextView email;
        private User user;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nom = itemView.findViewById(R.id.nom);
            email = itemView.findViewById(R.id.email);
            itemView.setOnClickListener(view -> {
                callback.onClick(user);
            });
        }

        public void setItem(User user) {
            nom.setText(user.getNom());
            email.setText(user.getEmail());
            this.user = user;
        }
    }
}
