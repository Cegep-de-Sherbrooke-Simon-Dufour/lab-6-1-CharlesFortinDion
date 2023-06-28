package com.example.lab6.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {User.class, Location.class}, version = 4)
public abstract class UserDatabase extends RoomDatabase {
    public abstract UserDao getDao();

}
