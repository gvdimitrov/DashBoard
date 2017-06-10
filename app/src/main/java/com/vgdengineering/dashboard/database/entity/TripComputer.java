package com.vgdengineering.dashboard.database.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.activeandroid.Model;

public class TripComputer extends Model implements Parcelable {
    private int distance;

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        if (distance >= 1){
            this.distance = distance;
        }else{
            this.distance = 1;
        }
    }

    public TripComputer(int distance) {
        this.distance = distance;
    }

    public TripComputer() {
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.distance);
    }

    protected TripComputer(Parcel in) {
        this.distance = in.readInt();
    }

    public static final Parcelable.Creator<TripComputer> CREATOR = new Parcelable.Creator<TripComputer>() {
        @Override
        public TripComputer createFromParcel(Parcel source) {
            return new TripComputer(source);
        }

        @Override
        public TripComputer[] newArray(int size) {
            return new TripComputer[size];
        }
    };

    @Override
    public String toString() {
        return "TripComputer{" +
                "distance=" + distance +
                '}';
    }
}
