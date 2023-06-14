package com.example.lab6.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.lab6.R;
import com.example.lab6.data.Location;
import com.example.lab6.data.User;

import org.w3c.dom.Text;

import java.util.List;

public class LocationListFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_location_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ViewModelUser viewModelUser = new ViewModelProvider(requireActivity()).get(ViewModelUser.class);

        RecyclerView recyclerView = view.findViewById(R.id.RecyclerLocations);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        LocationAdapter adapter = new LocationAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setCallback(viewModelUser::deleteLocation);

        int userId = getArguments().getInt("userId");

        User user = viewModelUser.getUser(userId);
        TextView nomUser = view.findViewById(R.id.nomLocation);
        nomUser.setText(user.getNom());

        Button soumettreButton = view.findViewById(R.id.AjouterNewLocation);
        soumettreButton.setOnClickListener(v -> {
            EditText nom = view.findViewById(R.id.nomEditLocation);
            viewModelUser.addLocation(nom.getText().toString(), userId);
            Navigation.findNavController(view).navigateUp();
        });

        viewModelUser.getLocations().observe(getViewLifecycleOwner(), new Observer<List<Location>>() {
            @Override
            public void onChanged(List<Location> locations) {
                adapter.submitList(locations);
            }
        });
    }
}