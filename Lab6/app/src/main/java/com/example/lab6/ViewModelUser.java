package com.example.lab6;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class ViewModelUser extends ViewModel {
    private final RepositoryUser repository;
    @Inject
    public ViewModelUser(RepositoryUser repository) {
        this.repository = repository;
    }
    public void addUser(String nom, String email) {
        repository.addUser(new User(nom, email));
    }

    public void deleteUser(User user) {
        repository.deleteUser(user);
    }

    public LiveData<ArrayList<User>> getUsers() {
        return repository.getUsers();
    }
}
