package com.vgdengineering.dashboard.database.entity;


import android.os.Parcel;
import android.os.Parcelable;

import com.activeandroid.Model;


public class Headlights extends Model implements Parcelable {

    private Light highBeams;
    private Light lowBeams;
    private Light fogLight;

    public Headlights() {}

    public Headlights(Light highBeams, Light lowBeams, Light fogLight) {
        this.highBeams = highBeams;
        this.lowBeams = lowBeams;
        this.fogLight = fogLight;
    }

    public Light getHighBeams() {
        return highBeams;
    }

    public void setHighBeams(Light highBeams) {
        this.highBeams = highBeams;
    }

    public Light getLowBeams() {
        return lowBeams;
    }

    public void setLowBeams(Light lowBeams) {
        this.lowBeams = lowBeams;
    }

    public Light getFogLight() {
        return fogLight;
    }

    public void setFogLight(Light fogLight) {
        this.fogLight = fogLight;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.highBeams, flags);
        dest.writeParcelable(this.lowBeams, flags);
        dest.writeParcelable(this.fogLight, flags);
    }

    protected Headlights(Parcel in) {
        this.highBeams = in.readParcelable(Light.class.getClassLoader());
        this.lowBeams = in.readParcelable(Light.class.getClassLoader());
        this.fogLight = in.readParcelable(Light.class.getClassLoader());
    }

    public static final Parcelable.Creator<Headlights> CREATOR = new Parcelable.Creator<Headlights>() {
        @Override
        public Headlights createFromParcel(Parcel source) {
            return new Headlights(source);
        }

        @Override
        public Headlights[] newArray(int size) {
            return new Headlights[size];
        }
    };

    @Override
    public String toString() {
        return "Headlights{" +
                "highBeams=" + highBeams +
                ", lowBeams=" + lowBeams +
                ", fogLight=" + fogLight +
                '}';
    }
}
