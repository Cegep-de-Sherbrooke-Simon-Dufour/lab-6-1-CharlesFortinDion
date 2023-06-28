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
import com.example.lab6.data.Location;

public class LocationAdapter extends ListAdapter<Location, LocationAdapter.LocationViewHolder> {
    RecyclerCallback<Location> callback = (u) -> {};

    public void setCallback(RecyclerCallback<Location> callback) {
        this.callback = callback;
    }

    protected LocationAdapter() {
        super(new DiffUtil.ItemCallback<Location>() {
            @Override
            public boolean areItemsTheSame(@NonNull Location oldLocation, @NonNull Location newLocation) {
                return oldLocation == newLocation;
            }
            @Override
            public boolean areContentsTheSame(@NonNull Location oldLocation, @NonNull Location newLocation) {
                return oldLocation.getName().equals(newLocation.getName());
            }
        });
    }

    @NonNull
    @Override
    public LocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_location, parent, false);
        return new LocationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LocationViewHolder holder, int position) {
        holder.setItem(getItem(position));
    }

    public class LocationViewHolder extends RecyclerView.ViewHolder {
        private final TextView nom;
        private Location location;

        public LocationViewHolder(@NonNull View itemView) {
            super(itemView);
             nom = itemView.findViewById(R.id.nomLocation);
            itemView.setOnClickListener(view -> {
                callback.onClick(location);
            });
        }

        public void setItem(Location location) {
            nom.setText(location.getName());
            this.location = location;
        }
    }
}
