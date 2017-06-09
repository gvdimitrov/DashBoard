package com.vgdengineering.dashboard.database.entity;


import android.os.Parcel;
import android.os.Parcelable;

import com.activeandroid.Model;

public class BeltsWarning extends Model implements Parcelable {

    public static final String LOW_ = "low";
    public static final String HIGH_ = "high";

    private boolean warningForSeatBelt;
    private int warningSeverity;

    public BeltsWarning() {}

    public BeltsWarning(boolean warningForSeatBelt, String warningSeverity){
        setWarningForSeatBelt(warningForSeatBelt);
        setWarningSeverity(warningSeverity);
    }

    public boolean isWarningForSeatBelt() {
        return warningForSeatBelt;
    }

    public void setWarningForSeatBelt(boolean warningForSeatBelt) {
        this.warningForSeatBelt = warningForSeatBelt;
    }

    public String getWarningSeverity() {
        return Priority.getPriorityName(warningSeverity);
    }

    public void setWarningSeverity(String warningSeverity) {
        this.warningSeverity = Priority.getPriorityId(warningSeverity);
    }

    @Override
    public String toString() {
        return "BeltsWarning{" +
                "warningForSeatBelt=" + warningForSeatBelt +
                ", warningSeverity=" + warningSeverity +
                '}';
    }

    public enum Priority {

        LOW(LOW_, 1), HIGH(HIGH_, 2);

        private String priority;
        private int id;

        Priority(String priority, int id) {
            this.priority = priority;
            this.id = id;
        }

        public static int getPriorityId(String name){
            if(name== null || name.isEmpty()){
                return 0;
            }
            for(Priority p: values()){
                if(p.priority.equals(name)){
                    return p.id;
                }
            }

            return 0;
        }

        public static String getPriorityName(int id){
            if(id<1){
                return LOW_;
            }
            for(Priority p: values()){
                if(p.id == id){
                    return p.priority;
                }
            }
            return LOW_;
        }

        public String getPriority() {
            return priority;
        }

        public int getId() {
            return id;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.warningForSeatBelt ? (byte) 1 : (byte) 0);
        dest.writeInt(this.warningSeverity);
    }

    protected BeltsWarning(Parcel in) {
        this.warningForSeatBelt = in.readByte() != 0;
        this.warningSeverity = in.readInt();
    }

    public static final Parcelable.Creator<BeltsWarning> CREATOR = new Parcelable.Creator<BeltsWarning>() {
        @Override
        public BeltsWarning createFromParcel(Parcel source) {
            return new BeltsWarning(source);
        }

        @Override
        public BeltsWarning[] newArray(int size) {
            return new BeltsWarning[size];
        }
    };
}
