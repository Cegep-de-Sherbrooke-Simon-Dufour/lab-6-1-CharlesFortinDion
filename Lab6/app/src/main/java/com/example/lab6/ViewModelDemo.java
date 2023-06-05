package com.example.lab6;

import androidx.lifecycle.ViewModel;

public class ViewModelDemo extends ViewModel {
    private repositoryDemo repository;
    public ViewModelDemo(repositoryDemo repository) {
        this.repository = repository;
    }
    public void addAnimal(String nom) {
        repository.addAnimal(new Item);
    }
}
