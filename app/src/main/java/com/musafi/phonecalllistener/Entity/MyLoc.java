package com.musafi.phonecalllistener.Entity;

import android.annotation.SuppressLint;
import android.location.Location;

public class MyLoc {

    private double mLatitude = 0.0;
    private double mLongitude = 0.0;
    private float mSpeed = 0.0f;

    public MyLoc() { }

    @SuppressLint("NewApi")
    public MyLoc(Location location) {

        this.mLatitude = location.getLatitude();
        this.mLongitude = location.getLongitude();

        this.mSpeed = location.getSpeed();


    }

    public MyLoc(MyLoc myLoc) {

        this.mLatitude = myLoc.mLatitude;
        this.mLongitude = myLoc.mLongitude;

        this.mSpeed = myLoc.mSpeed;

    }


    public double getLatitude() {
        return mLatitude;
    }

    public MyLoc setLatitude(double mLatitude) {
        this.mLatitude = mLatitude;
        return this;
    }

    public double getLongitude() {
        return mLongitude;
    }

    public MyLoc setLongitude(double mLongitude) {
        this.mLongitude = mLongitude;
        return this;
    }


    public float getSpeed() {
        return mSpeed;
    }

    public MyLoc setSpeed(float mSpeed) {
        this.mSpeed = mSpeed;
        return this;
    }
}
