package com.vgdengineering.dashboard.services;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.activeandroid.query.Select;
import com.vgdengineering.dashboard.database.entity.Climatronic;

public class ServiceClimatronic extends IntentService {
    private static final String TAG = ServiceGearBox.class.getSimpleName();
    public static final String CLIMATRONIC = "CLIMATRONIC";


    public ServiceClimatronic() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Climatronic climatronic = null;
        if (intent != null) {
            climatronic = intent.getParcelableExtra(CLIMATRONIC);
        }

        saveClimatronic(climatronic);
    }

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
}
