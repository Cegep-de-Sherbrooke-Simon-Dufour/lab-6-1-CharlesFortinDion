package com.example.lab6;

import java.util.ArrayList;
import java.util.List;

@Singleton
public class repositoryDemo {
    @Inject
    public repositoryDemo {
        //
    }
    private ArrayList<Item> animals = new ArrayList<>();

    public void addAnimal(Item animal) {
        animals.add(animal);
    }

    public List<Item> getAnimals() {
        return new ArrayList<Item>(animals);
    }
}
