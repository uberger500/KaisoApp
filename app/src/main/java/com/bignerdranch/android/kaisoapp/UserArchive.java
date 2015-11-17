package com.bignerdranch.android.kaisoapp;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by ursberger1 on 11/15/15.
 */
public class UserArchive {

    private static UserArchive sUserArchive;

    private List<User> mUsers;

    public static UserArchive get(Context context) {
        if (sUserArchive == null) {
            sUserArchive = new UserArchive(context);
        }
        return sUserArchive;
    }
    private UserArchive(Context context) {
        mUsers = new ArrayList<>();
   /*     for (int i = 0; i < 100; i++) {
            User user = new User();
            user.setName("User #" + i);
            mUsers.add(user);
        }*/
    }

    public List<User> getUsers() {
        return mUsers;
    }

    public User getUser(UUID id) {
        for (User user : mUsers) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }

    public void addUser(User c) {
        mUsers.add(c);
    }
}
