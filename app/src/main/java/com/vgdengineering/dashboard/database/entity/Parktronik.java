package com.vgdengineering.dashboard.database.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "parktronik")
public class Parktronik extends Model implements Parcelable {

    public static final String TAG = Parktronik.class.getSimpleName();
    public static final int MIN = 0;
    public static final int MAX = 5;
    @Column(name = "front")
    private int front;
    @Column(name = "rear")
    private int rear;

    public int getFront() {
        return front;
    }

    public void setFront(int front) {
        if (isValueValid(front)) {
            this.front = front;
        } else {
            Log.e(TAG, "The front value is out of range -> " + front);
            front = 0;
        }
    }

    public int getRear() {
        return rear;
    }

    public void setRear(int rear) {
        if(isValueValid(rear)) {
            this.rear = rear;
        }else{
            Log.e(TAG, "The rear value is out of range -> " + rear);
            rear = 0;
        }
    }

    public Parktronik() {
    }

    public Parktronik(int front, int rear) {
        setFront(front);
        setRear(rear);
    }

    @Override
    public String toString() {
        return "Parktronik{" +
                "front=" + front +
                ", rear=" + rear +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.front);
        dest.writeInt(this.rear);
    }

    protected Parktronik(Parcel in) {
        this.front = in.readInt();
        this.rear = in.readInt();
    }

    public static final Parcelable.Creator<Parktronik> CREATOR = new Parcelable.Creator<Parktronik>() {
        @Override
        public Parktronik createFromParcel(Parcel source) {
            return new Parktronik(source);
        }

        @Override
        public Parktronik[] newArray(int size) {
            return new Parktronik[size];
        }
    };

    private boolean isValueValid(int value) {
        return (value >= MIN && value <= MAX);
    }
}
