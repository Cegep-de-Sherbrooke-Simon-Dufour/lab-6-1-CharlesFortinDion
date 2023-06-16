package com.example.lab6.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.lab6.data.Location;
import com.example.lab6.data.RepositoryUser;
import com.example.lab6.data.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public LiveData<List<User>> getUsers() {
        return repository.getUsers();
    }

    public User getUser(int key) {
        for (User user : Objects.requireNonNull(repository.getUsers().getValue())) {
            if (user.getUserId() == key) {
                return user;
            }
        }
        return null;
    }
    public LiveData<List<Location>> getLocations() { return repository.getLocations(); }
    // à completer
    public void addLocation(String nom, int userId) { repository.addLocation(new Location(nom, 1));}
    public void deleteLocation(Location location) {
        repository.deleteLocation(location);
    }

}
