package com.vgdengineering.dashboard.asynctask;

import android.os.AsyncTask;
import android.util.Log;

import com.activeandroid.query.Select;
import com.vgdengineering.dashboard.database.entity.Climatronic;
import com.vgdengineering.dashboard.observables.ObservableDatabaseData;

public class AsyncTaskClimatronic extends AsyncTask<Climatronic, Void, Void> {
    private static final String TAG = AsyncTaskGearBox.class.getSimpleName();

    private static void saveClimatronic(Climatronic climatronic) {
        if (climatronic == null) {
            Log.e(TAG, "The Climatronic object is null, cannot save it!");
            return;
        }
        Log.d(TAG, "new climatronic values: " + climatronic.toString());
        Climatronic oldClimatronic = new Select().from(Climatronic.class).executeSingle();
        if (oldClimatronic == null) {
            climatronic.save();
        } else {
            Log.d(TAG, "old climatronic values: " + oldClimatronic.toString());
            oldClimatronic.setCurrentDegrees(climatronic.getCurrentDegrees());
            oldClimatronic.setDesiredDegrees(climatronic.getDesiredDegrees());
            oldClimatronic.setBlowerPower(climatronic.getBlowerPower());
            Log.d(TAG, "old climatronic with new values: " + oldClimatronic.toString());
            oldClimatronic.save();
        }
    }

    @Override
    protected Void doInBackground(Climatronic... params) {
        saveClimatronic(params[0]);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        ObservableDatabaseData.getInstance().notifyDateSetChange();
    }
}
