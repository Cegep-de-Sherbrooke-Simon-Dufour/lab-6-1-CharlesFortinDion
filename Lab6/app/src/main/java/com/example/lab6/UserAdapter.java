package com.example.lab6;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserAdapter extends ListAdapter<User, UserAdapter.ViewHolder> {
    private final RecyclerCallback<User> callback;
    ArrayList<User> users;

    public UserAdapter(ArrayList<User> users) {
        super(new DiffUtil.ItemCallback<User>() {
            @Override
            public boolean areItemsTheSame(User oldUser, User newUser) {
                return oldUser == newUser;
            }
            @Override
            public boolean areContentsTheSame(User oldUser, User newUser) {
                return oldUser.getNom().equals(newUser.getNom()) && oldUser.getEmail().equals(newUser.getEmail());
            }
        });
        callback = null;
        this.users = users;
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
        System.out.println("binding stuff");
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView nom;
        private final TextView email;
        User user;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(view -> {
                System.out.println("bouton cliqué");
                users.remove(user);
                submitList(new ArrayList<User>(users));
            });
            nom = itemView.findViewById(R.id.nom);
            email = itemView.findViewById(R.id.email);
        }

        public void setItem(User user) {
            nom.setText(user.getNom());
            email.setText(user.getEmail());
            this.user = user;
        }
    }
}
