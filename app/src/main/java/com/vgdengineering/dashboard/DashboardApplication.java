package com.vgdengineering.dashboard;

import android.app.Application;
import android.content.Context;

import com.activeandroid.ActiveAndroid;
import com.vgdengineering.dashboard.database.communication.Dao;

public class DashboardApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        ActiveAndroid.initialize(this);
        Dao.create();
        Dao.createDummyData();
    }

    public static Context getAppContext() {
        return context;
    }
}
