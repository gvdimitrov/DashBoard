package com.vgdengineering.dashboard.asynctask;

import android.os.AsyncTask;
import android.util.Log;

import com.activeandroid.query.Select;
import com.vgdengineering.dashboard.database.entity.GearBox;
import com.vgdengineering.dashboard.observables.ObservableDatabaseData;

public class AsyncTaskGearBox extends AsyncTask<GearBox, Void, Void> {

    private static final String TAG = AsyncTaskGearBox.class.getSimpleName();

    private void saveGearBox(GearBox gearBox) {
        if (gearBox == null) {
            Log.e(TAG, "The GearBox object is null, cannot save it!");
            return;
        }
        GearBox oldGearBox = new Select().from(GearBox.class).executeSingle();
        if (oldGearBox == null) {
            gearBox.save();
        } else {
            oldGearBox.setNextGear(gearBox.getNextGear());
            oldGearBox.setCurrentGear(gearBox.getCurrentGear());
            oldGearBox.save();
        }
    }

    @Override
    protected Void doInBackground(GearBox... params) {
        saveGearBox(params[0]);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        ObservableDatabaseData.getInstance().notifyDateSetChange();
    }
}
