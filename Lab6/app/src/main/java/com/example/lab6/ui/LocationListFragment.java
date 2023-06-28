package com.example.lab6.ui;

import android.net.Uri;
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
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lab6.R;
import com.example.lab6.data.Location;
import com.example.lab6.data.User;

import org.w3c.dom.Text;

import java.net.URI;
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

        assert getArguments() != null;
        long userId = getArguments().getLong("userId");

        User user = viewModelUser.getUser((int)userId);
        TextView nomUser = view.findViewById(R.id.UserName);
        TextView emailUser = view.findViewById(R.id.UserAdress);
        ImageView userImage = view.findViewById(R.id.UserPic);
        nomUser.setText(user.getNom());
        emailUser.setText(user.getEmail());
        userImage.setImageURI(Uri.parse(user.getUri()));

        Button soumettreButton = view.findViewById(R.id.AjouterNewLocation);
        soumettreButton.setOnClickListener(v -> {
            EditText nom = view.findViewById(R.id.nomEditLocation);
            viewModelUser.addLocation(nom.getText().toString(), (int)userId);
        });

        Button supprimer = view.findViewById(R.id.Supprimer);
        supprimer.setOnClickListener(v -> {
            viewModelUser.deleteUser(user);
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