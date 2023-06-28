package com.example.lab6.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;
import java.util.Map;

@Dao
public interface UserDao {
    @Query("SELECT * FROM users")
    LiveData<List<User>> getAll();

    @Query("SELECT * FROM users WHERE userId = :i")
    LiveData<User> getUser(int i);

    @Query("SELECT * FROM locations")
    LiveData<List<Location>> getLocations();

    @Query("SELECT * FROM users WHERE userId IN (:userIds)")
    LiveData<List<User>> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM users WHERE nom LIKE :name AND email LIKE :email LIMIT 1")
    User findByName(String name, String email);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(User ... user);

    @Delete
    void delete(User user);

    @Query(
            "SELECT * FROM users JOIN locations ON users.userId = locations.userId"
    )
    Map<User, List<Location>> loadUsersAndLocations();

    @Query(
            "SELECT * FROM users JOIN locations ON users.userId = locations.userId WHERE users.userId IN (:userId)"
    )
    Map<User, List<Location>> loadUserAndLocations(int userId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addLocation(Location ... location);

    @Delete
    void deleteLocation(Location location);
}
