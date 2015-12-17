package com.bignerdranch.android.kaisoapp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by urs on 12/16/15.
 */
/*
public class FileRead {
    public static void main(String[] args) throws FileNotFoundException {

        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter("output.json"));


            // Open the file that is the first
            // command line parameter



            FileInputStream fstream = new FileInputStream("Music4.csv");
            Scanner scanner = new Scanner(fstream);
            // Get the object of DataInputStream
         //   DataInputStream in = new DataInputStream(fstream);
         //   BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String firstLine = scanner.nextLine();
            String headerField[] = firstLine.split(",");

            String firstItem = scanner.nextLine();
            String firstRelease[] = firstItem.split(",");

            List<String> mTracks = new ArrayList<String>();
            List<String> mComments = new ArrayList<String>();

            mTracks.add(firstRelease[1]);
            String strNextLine;
            String secondRelease[];
            writer.write("{\"results\":[");
            while ((scanner.hasNextLine())) {

                strNextLine = scanner.nextLine();
                secondRelease = strNextLine.split(",");

                if (firstRelease[1].equals(secondRelease[1])) {
                    mTracks.add(secondRelease[2]);
                } else {
                    writer.write("{\"mArtist\":" + firstRelease[0] +"," +
                            "\"mTitle\":"+ firstRelease[1]+"," +
                            "\"mTracks\": " + mTracks +
                            "\"mYear\":"+ firstRelease[3]+"," +
                            "\"mLabel\":"+ firstRelease[4]+"," +
                            "\"mArranger\":"+ firstRelease[5]+"," +
                            "\"mGenre\":"+ firstRelease[6]+"," +
                            "\"mCompilation\":"+ firstRelease[7]+"," +
                            "\"mComments\": " + mComments +
                            "}");
//                     mTracks.add(secondRelease[1]);
                    System.out.println(mTracks);
                    firstRelease = secondRelease;
                    mTracks.clear();
                    mTracks.add(firstRelease[2]);
                    if(scanner.hasNextLine()) {
                        writer.write(",");
                    }else {
                        writer.write("]}");
                    }
                }
            }
            //Close the input stream
            scanner.close();
        } catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (writer != null) {
                System.out.println("Closing PrintWriter");
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("PrintWriter not open");
            }
        }
    }
}

*/

