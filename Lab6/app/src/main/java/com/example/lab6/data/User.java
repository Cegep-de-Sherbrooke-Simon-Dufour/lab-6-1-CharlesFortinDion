package com.example.lab6.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")
public class User {
    @PrimaryKey(autoGenerate = true)
    private int userId;

    @ColumnInfo(name = "nom")
    private String nom;

    @ColumnInfo(name = "email")
    private String email;

    @ColumnInfo(name = "uri")
    private String uri;

    public User(String nom, String email, String uri) {
        this.nom = nom;
        this.email = email;
        this.uri = uri;
    }
    public int getUserId() { return userId;}
    public void setUserId(int userId) { this.userId = userId;}

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
