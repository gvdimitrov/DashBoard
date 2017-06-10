package com.vgdengineering.dashboard.database.entity;


import android.os.Parcel;
import android.os.Parcelable;

import com.activeandroid.Model;


public class Headlights extends Model implements Parcelable {

    private boolean highBeams;
    private boolean lowBeams;
    private boolean fogLight;

    public Headlights() {}

    public Headlights(boolean highBeams, boolean lowBeams, boolean fogLight) {
        this.highBeams = highBeams;
        this.lowBeams = lowBeams;
        this.fogLight = fogLight;
    }

    public boolean getHighBeams() {
        return highBeams;
    }

    public void setHighBeams(boolean highBeams) {
        this.highBeams = highBeams;
    }

    public boolean getLowBeams() {
        return lowBeams;
    }

    public void setLowBeams(boolean lowBeams) {
        this.lowBeams = lowBeams;
    }

    public boolean getFogLight() {
        return fogLight;
    }

    public void setFogLight(boolean fogLight) {
        this.fogLight = fogLight;
    }


    @Override
    public String toString() {
        return "Headlights{" +
                "highBeams=" + highBeams +
                ", lowBeams=" + lowBeams +
                ", fogLight=" + fogLight +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.highBeams ? (byte) 1 : (byte) 0);
        dest.writeByte(this.lowBeams ? (byte) 1 : (byte) 0);
        dest.writeByte(this.fogLight ? (byte) 1 : (byte) 0);
    }

    protected Headlights(Parcel in) {
        this.highBeams = in.readByte() != 0;
        this.lowBeams = in.readByte() != 0;
        this.fogLight = in.readByte() != 0;
    }

    public static final Creator<Headlights> CREATOR = new Creator<Headlights>() {
        @Override
        public Headlights createFromParcel(Parcel source) {
            return new Headlights(source);
        }

        @Override
        public Headlights[] newArray(int size) {
            return new Headlights[size];
        }
    };
}
