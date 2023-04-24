package com.example.harmonicatulashop.models.account;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "admin")
public class Admin {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "avatarImage")
    private byte[] avatarImage;
    @NonNull
    @ColumnInfo(name = "login")
    private String login;
    @ColumnInfo(name = "hashPassword")
    private long hashPassword;
    @NonNull
    @ColumnInfo(name = "firstname")
    private String firstname;
    @ColumnInfo(name = "lastname")
    private String lastname;

    public Admin(byte[] avatarImage, @NonNull String login, long hashPassword, @NonNull String firstname, String lastname) {
        this.avatarImage = avatarImage;
        this.login = login;
        this.hashPassword = hashPassword;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getAvatarImage() {
        return avatarImage;
    }

    public void setAvatarImage(byte[] avatarImage) {
        this.avatarImage = avatarImage;
    }

    @NonNull
    public String getLogin() {
        return login;
    }

    public void setLogin(@NonNull String login) {
        this.login = login;
    }

    public long getHashPassword() {
        return hashPassword;
    }

    public void setHashPassword(long hashPassword) {
        this.hashPassword = hashPassword;
    }

    @NonNull
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(@NonNull String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @NonNull
    @Override
    public String toString() {
        return login + " " + firstname + " " + lastname;
    }
}
