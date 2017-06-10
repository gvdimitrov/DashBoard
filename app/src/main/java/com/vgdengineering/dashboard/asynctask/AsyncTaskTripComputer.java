package com.vgdengineering.dashboard.asynctask;

import android.os.AsyncTask;
import android.util.Log;

import com.activeandroid.query.Select;
import com.vgdengineering.dashboard.database.entity.TripComputer;
import com.vgdengineering.dashboard.observables.ObservableDatabaseData;

public class AsyncTaskTripComputer extends AsyncTask<TripComputer, Void, Void>{
    private static final String TAG = AsyncTaskTripComputer.class.getSimpleName();

    private void saveGearBox(TripComputer trip) {
        if (trip == null) {
            Log.e(TAG, "The Headlights object is null, cannot save it!");
            return;
        }
        Log.d(TAG, "new trip values: " + trip.toString());
        TripComputer oldTrip = new Select().from(TripComputer.class).executeSingle();
        if (oldTrip == null) {
            trip.save();
        } else {
            Log.d(TAG, "old trip values: " + oldTrip.toString());
            oldTrip.setDistance(trip.getDistance());
            Log.d(TAG, "old trip with new values: " + oldTrip.toString());
            oldTrip.save();
        }
    }
    @Override
    protected Void doInBackground(TripComputer... params) {
        saveGearBox(params[0]);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        ObservableDatabaseData.getInstance().notifyDateSetChange();
    }
}
