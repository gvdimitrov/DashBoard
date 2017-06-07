package com.vgdengineering.dashboard.database.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "climatronic")
public class Climatronic extends Model implements Parcelable {
    @Column(name = "current_degrees")
    private int currentDegrees;
    @Column(name = "desired_degrees")
    private int desiredDegrees;
    @Column(name = "blower_power")
    private int blowerPower;

    public void setCurrentDegrees(int currentDegrees) {
        this.currentDegrees = currentDegrees;
    }

    public void setDesiredDegrees(int desiredDegrees) {
        this.desiredDegrees = desiredDegrees;
    }

    public void setBlowerPower(int blowerPower) {
        if (blowerPower >= 1 && blowerPower <= 8) {
            this.blowerPower = blowerPower;
        } else {
            blowerPower = 1;
        }
    }

    public int getCurrentDegrees() {
        return currentDegrees;
    }

    public int getDesiredDegrees() {
        return desiredDegrees;
    }

    public int getBlowerPower() {
        return blowerPower;
    }

    public Climatronic(int currentDegrees, int desiredDegrees, int blowerPower) {
        setCurrentDegrees(currentDegrees);
        setDesiredDegrees(desiredDegrees);
        setBlowerPower(blowerPower);
    }

    public Climatronic(){}

    @Override
    public String toString() {
        return "Climatronic{" +
                "currentDegrees=" + currentDegrees +
                ", desiredDegrees=" + desiredDegrees +
                ", blowerPower=" + blowerPower +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.currentDegrees);
        dest.writeInt(this.desiredDegrees);
        dest.writeInt(this.blowerPower);
    }

    protected Climatronic(Parcel in) {
        this.currentDegrees = in.readInt();
        this.desiredDegrees = in.readInt();
        this.blowerPower = in.readInt();
    }

    public static final Parcelable.Creator<Climatronic> CREATOR = new Parcelable.Creator<Climatronic>() {
        @Override
        public Climatronic createFromParcel(Parcel source) {
            return new Climatronic(source);
        }

        @Override
        public Climatronic[] newArray(int size) {
            return new Climatronic[size];
        }
    };
}
