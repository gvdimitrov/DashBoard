package com.vgdengineering.dashboard.database.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.activeandroid.Model;

public class Light extends Model implements Parcelable {
    protected boolean isOn;
    protected int level;

    public boolean isOn() {
        return isOn;
    }

    public void setOn(boolean on) {
        isOn = on;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        if(level >= 1) {
            this.level = level;
        }else{
            this.level = 1;
        }
    }

    @Override
    public String toString() {
        return "Light{" +
                "isOn=" + isOn +
                ", level=" + level +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.isOn ? (byte) 1 : (byte) 0);
        dest.writeInt(this.level);
    }

    public Light() {
    }

    protected Light(Parcel in) {
        this.isOn = in.readByte() != 0;
        this.level = in.readInt();
    }

    public Light(boolean isOn, int level) {
        this.isOn = isOn;
        this.level = level;
    }

    public final Parcelable.Creator<Light> CREATOR = new Parcelable.Creator<Light>() {
        @Override
        public Light createFromParcel(Parcel source) {
            return new Light(source);
        }

        @Override
        public Light[] newArray(int size) {
            return new Light[size];
        }
    };
}
