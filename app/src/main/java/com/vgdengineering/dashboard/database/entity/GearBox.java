package com.vgdengineering.dashboard.database.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.security.InvalidParameterException;

@Table(name = "gear_box")
public class GearBox extends Model implements Parcelable {
    private static final String TAG = GearBox.class.getSimpleName();
    private static final int NEUTRAL = 0;

    @Column(name = "current_gear")
    private int currentGear;
    @Column(name = "next_gear")
    private int nextGear;

    public int getCurrentGear() {
        return currentGear;
    }

    public void setCurrentGear(int currentGear) {
        if (currentGear >= -1 && currentGear <= 6) {
            this.currentGear = currentGear;
        } else {
            Log.e(TAG, "currentGear: " + currentGear, new InvalidParameterException());
            this.currentGear = NEUTRAL;
        }
    }

    public int getNextGear() {
        return nextGear;
    }

    public void setNextGear(int nextGear) {
        if (nextGear >= -1 && nextGear <= 6) {
            this.nextGear = nextGear;
        } else {
            Log.e(TAG, "nextGear: " + nextGear, new InvalidParameterException());
            this.nextGear = NEUTRAL;
        }
    }

    public GearBox(int currentGear, int nextGear) {
        setCurrentGear(currentGear);
        setNextGear(nextGear);
    }

    public GearBox() {
    }

    @Override
    public String toString() {
        return "GearBox{" +
                "currentGear=" + currentGear +
                ", nextGear=" + nextGear +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.currentGear);
        dest.writeInt(this.nextGear);
    }

    protected GearBox(Parcel in) {
        this.currentGear = in.readInt();
        this.nextGear = in.readInt();
    }

    public static final Parcelable.Creator<GearBox> CREATOR = new Parcelable.Creator<GearBox>() {
        @Override
        public GearBox createFromParcel(Parcel source) {
            return new GearBox(source);
        }

        @Override
        public GearBox[] newArray(int size) {
            return new GearBox[size];
        }
    };
}
