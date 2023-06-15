package com.example.lab6;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class RepositoryUser {
    @Inject
    public RepositoryUser() {}
    private ArrayList<User> users = new ArrayList<>(Arrays.asList(
            new User("Bob", "bobestcool@gmail.com"),
            new User("Alice", "ACC@gmail.com"),
            new User("Bob", "bobestcool@gmail.com"),
            new User("Alice", "ACC@gmail.com"),
            new User("Bob", "bobestcool@gmail.com")
    ));
    private final MutableLiveData<ArrayList<User>> usersLiveData = new
            MutableLiveData<>(new ArrayList<>(users));

    public void addUser(User user) {
        users.add(user);
        usersLiveData.setValue(new ArrayList<>(users));
    }

    public void deleteUser(User user) {
        users.remove(user);
        usersLiveData.setValue(new ArrayList<>(users));
    }

    public LiveData<ArrayList<User>> getUsers() {
        return usersLiveData;
    }
}
