package com.example.lab6.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM users")
    LiveData<List<User>> getAll();

    @Query("SELECT * FROM users WHERE id IN (:userIds)")
    LiveData<List<User>> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM users WHERE nom LIKE :name AND email LIKE :email LIMIT 1")
    User findByName(String name, String email);

    @Insert
    void insertAll(User ... user);

    @Delete
    void delete(User user);
}
