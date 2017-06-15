package com.vgdengineering.dashboard.asynctask;

import android.os.AsyncTask;
import android.util.Log;

import com.activeandroid.query.Select;
import com.vgdengineering.dashboard.database.entity.Parktronik;
import com.vgdengineering.dashboard.observables.ObservableDatabaseData;

public class AsyncTaskParktronik extends AsyncTask<Parktronik, Void, Void> {
    private static final String TAG = AsyncTaskParktronik.class.getSimpleName();

    private static void saveParktronik(Parktronik parktronik){
        if (parktronik == null) {
            Log.e(TAG, "The Parktronik object is null, cannot save it!");
            return;
        }
        Parktronik oldParktronik = new Select().from(Parktronik.class).executeSingle();
        if (oldParktronik == null) {
            parktronik.save();
        } else {
            oldParktronik.setFront(parktronik.getFront());
            oldParktronik.setRear(parktronik.getRear());
            oldParktronik.save();
        }
    }

    @Override
    protected Void doInBackground(Parktronik... params) {
        saveParktronik(params[0]);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        ObservableDatabaseData.getInstance().notifyDateSetChange();
    }
}
