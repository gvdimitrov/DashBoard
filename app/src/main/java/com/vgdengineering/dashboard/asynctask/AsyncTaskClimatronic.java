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
            return;
        }
        Climatronic oldClimatronic = new Select().from(Climatronic.class).executeSingle();
        if (oldClimatronic == null) {
            climatronic.save();
        } else {
            oldClimatronic.setCurrentDegrees(climatronic.getCurrentDegrees());
            oldClimatronic.setDesiredDegrees(climatronic.getDesiredDegrees());
            oldClimatronic.setBlowerPower(climatronic.getBlowerPower());
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
