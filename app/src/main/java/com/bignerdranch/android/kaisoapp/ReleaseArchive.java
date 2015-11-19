package com.bignerdranch.android.kaisoapp;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by ursberger1 on 11/13/15.
 */
public class ReleaseArchive {
    private static ReleaseArchive sReleaseArchive;

    private List<Release> mReleases;
/*
    public ReleaseArchive(List<Release> releases) {
        mReleases = releases;
    }
*/
    public static ReleaseArchive get(Context context) {
        if (sReleaseArchive == null) {
            sReleaseArchive = new ReleaseArchive(context);
        }
        return sReleaseArchive;
    }
    private ReleaseArchive(Context context) {
       mReleases = new ArrayList<>();
   /*     for (int i = 0; i < 100; i++) {
            Release release = new Release();
            release.setTitle("Release #" + i);
            mReleases.add(release);
        }*/
    }

    public List<Release> getReleases() {
        return mReleases;
    }

    public Release getRelease(UUID id) {
        for (Release release : mReleases) {
            if (release.getId().equals(id)) {
                return release;
            }
        }
        return null;
    }
/*
    public int getSize(ReleaseArchive releaseArchive) {

        int size = releaseArchive.size();

    }
*/
    public void addRelease(Release r) {
        mReleases.add(r);
    }
}
