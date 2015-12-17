package com.bignerdranch.android.kaisoapp;

import com.parse.ParseClassName;
import com.parse.ParseObject;

import java.util.List;

/**
 * Created by ursberger1 on 11/13/15.
 */

@ParseClassName("Release")
public class Release extends ParseObject {

    public Release() {}

    public String getTitle() {
        return getString("mTitle");
    }

    public void setTitle(String title) {
        put("mTitle", title);
    }

    public String getArtist() {
        return getString("mArtist");
    }

    public void setArtist(String artist) {
        put("mArtist", artist);
    }

    public void setArtistLowercase(String artist) {
        put("mArtistLowercase", artist.toLowerCase());
    }

    public String getYear() {
        return getString("mYear");
    }

    public void setYear(String year) {
        put("mYear", year);
    }

    public String getArranger() {
        return getString("mArranger");
    }

    public void setArranger(String arranger) {
        put("mArranger", arranger);
    }

    public String getNumTracks() {
        return getString("mNumTracks");
    }

    public void setNumTracks(String numTracks) {
        put("mNumTracks", numTracks);
    }

    public List<String> getTracks() {
        return (List<String>) get("mTracks");
    }

    public void setTracks(List<String> tracks) { put("mTracks", tracks); }

    public String getLabel() {
        return getString("mLabel");
    }

    public void setLabel(String label) {
        put("mLabel", label);
    }

    public String getGenre() {
        return getString("mGenre");
    }

    public void setGenre(String genre) {
        put("mGenre", genre);
    }

    public void setComments(List<String> comments) { put("mComments", comments); }

    public List<String> getComments() {
        return (List<String>) get("mComments");
    }
}
