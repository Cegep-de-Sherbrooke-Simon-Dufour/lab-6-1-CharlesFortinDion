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

class UserAdapter extends ListAdapter<Item, UserAdapter.ViewHolder> {
    private final RecyclerCallback<Item> callback;
    ArrayList<Item> users;

    public UserAdapter(ArrayList<Item> users) {
        super(new DiffUtil.ItemCallback<Item>() {
            @Override
            public boolean areItemsTheSame(Item oldUser, Item newUser) {
                return oldUser == newUser;
            }
            @Override
            public boolean areContentsTheSame(Item oldUser, Item newUser) {
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
        Item user;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(view -> {
                System.out.println("bouton cliqué");
                users.remove(user);
                submitList(new ArrayList<Item>(users));
            });
            nom = itemView.findViewById(R.id.nom);
            email = itemView.findViewById(R.id.email);
        }

        public void setItem(Item item) {
            nom.setText(item.getNom());
            email.setText(item.getEmail());
            this.user = item;
        }
    }
}
