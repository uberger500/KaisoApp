package com.bignerdranch.android.kaisoapp;

import com.parse.ParseClassName;
import com.parse.ParseObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Created by ursberger1 on 11/13/15.
 */

@ParseClassName("Release")
public class Release extends ParseObject {

    public Release() {}

    public String getUUID() {
        return getString("UUID");
    }

    public void setUUID(String uuid) {
        put("UUID", uuid);
    }


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
        put("mName", numTracks);
    }

    public List<String> getTracks() {
        return (List<String>) get("mTracks");
    }

    public void setTracks(List<String> tracks) {
        put("mTracks", Arrays.asList(tracks));
    }

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

}
/*

    public Release() {
        this(UUID.randomUUID());
    }
    public Release(UUID id) {
        mId = id;

    }

    public Release(UUID id, String title, String artist, String year, String arranger,
                   String numTracks, List<String> tracks, String link, String genre) {
        mId = id;
        mTitle = title;
        mArtist = artist;
        mYear = year;
        mArranger = arranger;
        mNumTracks = numTracks;
        mTracks = tracks;
        mLink = link;
        mGenre = genre;
    }

    public UUID getId() {
        return mId;
    }

    public void setId(UUID id) {
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getArtist() {
        return mArtist;
    }

    public void setArtist(String artist) {
        mArtist = artist;
    }

    public String getYear() {
        return mYear;
    }

    public void setYear(String year) {
        mYear = year;
    }

    public String getArranger() {
        return mArranger;
    }

    public void setArranger(String arranger) {
        mArranger = arranger;
    }

    public String getNumTracks() {
        return mNumTracks;
    }

    public void setNumTracks(String numTracks) {
        mNumTracks = numTracks;
    }

    public List<String> getTracks() {
        return mTracks;
    }

    public void setTracks(List<String> tracks) {
        mTracks = tracks;
    }

    public String getLink() {
        return mLink;
    }

    public void setLink(String link) {
        mLink = link;
    }

    public String getGenre() {
        return mGenre;
    }

    public void setGenre(String genre) {
        mGenre = genre;
    }*/

