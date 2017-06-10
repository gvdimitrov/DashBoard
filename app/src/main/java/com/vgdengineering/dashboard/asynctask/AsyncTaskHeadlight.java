package com.vgdengineering.dashboard.asynctask;

import android.os.AsyncTask;
import android.util.Log;

import com.activeandroid.query.Select;
import com.vgdengineering.dashboard.database.entity.Headlights;
import com.vgdengineering.dashboard.observables.ObservableDatabaseData;

public class AsyncTaskHeadlight extends AsyncTask<Headlights, Void, Void> {

    private static final String TAG = AsyncTaskHeadlight.class.getSimpleName();

    private void saveGearBox(Headlights headlights) {
        if (headlights == null) {
            Log.e(TAG, "The Headlights object is null, cannot save it!");
            return;
        }
        Log.d(TAG, "new headlights values: " + headlights.toString());
        Headlights oldHeadlight = new Select().from(Headlights.class).executeSingle();
        if (oldHeadlight == null || (oldHeadlight.getFogLight() == null || oldHeadlight.getHighBeams() == null ||
                headlights.getLowBeams() == null)) {
            headlights.save();
        } else {
            Log.d(TAG, "old headlights values: " + oldHeadlight.toString());
            oldHeadlight.setHighBeams(headlights.getHighBeams());
            oldHeadlight.setLowBeams(headlights.getLowBeams());
            oldHeadlight.setFogLight(headlights.getFogLight());
            Log.d(TAG, "old headlights with new values: " + oldHeadlight.toString());

            oldHeadlight.save();
        }
    }

    @Override
    protected Void doInBackground(Headlights... params) {
        saveGearBox(params[0]);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        ObservableDatabaseData.getInstance().notifyDateSetChange();
    }
}
