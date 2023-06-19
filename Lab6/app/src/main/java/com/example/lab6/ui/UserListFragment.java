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

import com.example.lab6.R;
import com.example.lab6.data.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class UserListFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ViewModelUser viewModelUser = new ViewModelProvider(requireActivity()).get(ViewModelUser.class);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        UserAdapter adapter = new UserAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setCallback((user) -> {
            Bundle bundle = new Bundle();
            bundle.putLong("userId", user.getUserId());
            System.out.println(user.getUserId());
            Navigation.findNavController(view).navigate(R.id.action_userListFragment_to_locationListFragment, bundle);
        });

        FloatingActionButton addButton = view.findViewById(R.id.addButton);
        addButton.setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.action_userListFragment_to_createUserFragment);
        });

        viewModelUser.getUsers().observe(getViewLifecycleOwner(), new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                adapter.submitList(users);
            }
        });
    }
}