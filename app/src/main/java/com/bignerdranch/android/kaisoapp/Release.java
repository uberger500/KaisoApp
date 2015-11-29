package com.bignerdranch.android.kaisoapp;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by ursberger1 on 11/13/15.
 */
public class Release {
    private UUID mId;
    private String mTitle;
    private String mArtist;
    private String mYear;
    private String mArranger;
    private String mNumTracks;
    private List<String> mTracks;
    private String mLink;
    private String mGenre;

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
    }
}
