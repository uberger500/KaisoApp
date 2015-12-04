package com.bignerdranch.android.kaisoapp;

import com.parse.ParseClassName;
import com.parse.ParseObject;

import java.util.UUID;

/**
 * Created by ursberger1 on 11/15/15.
 */

@ParseClassName("User")
public class User extends ParseObject {

    public User() {
    }

    public String getPassword() {
        return getString("mPassword");
    }

    public void setPasssword(String name) {
        put("mPassword", name);
    }

    public String getName() {
        return getString("mName");
    }

    public void setName(String name) {
        put("mName", name);
    }

    public String getEmail() {
        return getString("mEmail");
    }

    public void setEmail(String email) {
        put("mEmail", email);
    }

    public String getPhone() {
        return getString("mPhone");
    }

    public void setPhone(String phone) {
        put("mPhone", phone);
    }
}
/*
    private UUID mId;
    private String mName;
    private String mEmail;
    private Integer mPhone;


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

    public Integer getPhone() {
        return mPhone;
    }

    public void setPhone(Integer phone) {
        mPhone = phone;
    }
    */
//}
