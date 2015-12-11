package com.bignerdranch.android.kaisoapp;

import android.app.Application;
import android.content.Context;

import com.parse.Parse;
import com.parse.ParseObject;

/**
 * Created by urs on 12/10/15.
 */
public class StarterApplication extends Application {

    public StarterApplication() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(Release.class);
        ParseObject.registerSubclass(User.class);
        ParseObject.registerSubclass(Discussion.class);

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "ITuVP5vobHEQeHjlJZuegG9COr8PABh12yUOsoCf",
                "4iiFd8i4WInojQBNXmIUlOJYPwGiA2dWykeTWOge");


    }

}
