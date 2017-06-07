package com.vgdengineering.dashboard.services;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.activeandroid.query.Select;
import com.vgdengineering.dashboard.database.entity.Parktronik;

public class ServiceParktronik extends IntentService {
    private static final String TAG = ServiceParktronik.class.getSimpleName();
    public static final String PARKTRONIK = "PARKTRONIK";

    public ServiceParktronik() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Parktronik parktronik = null;
        if(intent!= null){
            parktronik = intent.getParcelableExtra(PARKTRONIK);
        }
        saveParktronik(parktronik);
    }

    private static void saveParktronik(Parktronik parktronik){
        if (parktronik == null) {
            Log.e(TAG, "The Parktronik object is null, cannot save it!");
            return;
        }
        Log.d(TAG, "new parktronik values: " + parktronik.toString());
        Parktronik oldParktronik = new Select().from(Parktronik.class).executeSingle();
        if (oldParktronik == null) {
            parktronik.save();
        } else {
            Log.d(TAG, "old parktronik values: " + oldParktronik.toString());
            oldParktronik.setFront(parktronik.getFront());
            oldParktronik.setRear(parktronik.getRear());
            Log.d(TAG, "old parktronik with new values: " + oldParktronik.toString());
            oldParktronik.save();
        }
    }
}
