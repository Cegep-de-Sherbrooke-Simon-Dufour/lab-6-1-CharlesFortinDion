package com.example.lab6.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class RepositoryUser {
    private final UserDao userDao;
    private final LiveData<List<User>> users;
    private final LiveData<List<Location>> locations;

    @Inject
    public RepositoryUser (UserDatabase userDatabase){
        userDao = userDatabase.getDao();
        users = userDao.getAll();
        locations = userDao.getLocations();
    }

    public void addUser(User user) {
        Executors.newSingleThreadExecutor().execute(() -> {
            userDao.insertAll(user);
        });
    }

    public void deleteUser(User user) {
        Executors.newSingleThreadExecutor().execute(() -> {
            userDao.delete(user);
        });
    }

    public LiveData<List<User>> getUsers() {
        return users;
    }

    public User getUser(int key) {
        LiveData<User> user = userDao.getUser(key);
        return user.getValue();
    }

    public LiveData<List<Location>> getLocations() {return locations;}

    public void addLocation(Location location) {
        Executors.newSingleThreadExecutor().execute(() -> {
            userDao.addLocation(location);
        });
    }

    public void deleteLocation(Location location) {
        Executors.newSingleThreadExecutor().execute(() -> {
            userDao.deleteLocation(location);
        });
    }
}
