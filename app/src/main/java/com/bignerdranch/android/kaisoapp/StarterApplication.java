package com.bignerdranch.android.kaisoapp;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

/**
 * Created by urs on 12/10/15.
 */

//this class provides the linkage to the Parse database
public class StarterApplication extends Application {

    public StarterApplication() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

        //Two subclass that developed out of earlier versions
        ParseObject.registerSubclass(Release.class);
        ParseObject.registerSubclass(Discussion.class);

        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "ITuVP5vobHEQeHjlJZuegG9COr8PABh12yUOsoCf",
                "4iiFd8i4WInojQBNXmIUlOJYPwGiA2dWykeTWOge");
    }

}
