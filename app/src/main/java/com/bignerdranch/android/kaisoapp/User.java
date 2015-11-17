package com.bignerdranch.android.kaisoapp;

import java.util.UUID;

/**
 * Created by ursberger1 on 11/15/15.
 */
public class User {

    private UUID mId;
    private String mName;
    private String mEmail;
    private String mPhone;


    public User() {
        this(UUID.randomUUID());
    }
    public User(UUID id) {
        mId = id;
    }

    public UUID getId() {
        return mId;
    }

    public void setId(UUID id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getPhone() {
        return mPhone;
    }

    public void setPhone(String phone) {
        mPhone = phone;
    }
}
