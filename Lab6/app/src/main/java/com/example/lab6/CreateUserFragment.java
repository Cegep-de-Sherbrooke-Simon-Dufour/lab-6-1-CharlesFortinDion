package com.example.lab6;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class CreateUserFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_user, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ViewModelUser viewModelUser =new ViewModelProvider(requireActivity()).get(ViewModelUser.class);

        Button soumettreButton = view.findViewById(R.id.soumettreNewUser);
        soumettreButton.setOnClickListener(v -> {
            EditText nom = view.findViewById(R.id.editNom);
            EditText email = view.findViewById(R.id.editEmail);
            viewModelUser.addUser(nom.getText().toString(), email.getText().toString());
            Navigation.findNavController(view).navigateUp();
        });

        Button annulerButton = view.findViewById(R.id.annulerCreate);
        annulerButton.setOnClickListener(v -> {
            Navigation.findNavController(view).navigateUp();
        });
    }
}