package com.vgdengineering.dashboard.services;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.activeandroid.query.Select;
import com.vgdengineering.dashboard.database.entity.GearBox;

public class ServiceGearBox extends IntentService {

    private static final String TAG = ServiceGearBox.class.getSimpleName();
    public static final String GEAR_BOX = "GEAR_BOX";

    public ServiceGearBox() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        GearBox gearBox = null;
        if (intent != null) {
            gearBox = intent.getParcelableExtra(GEAR_BOX);
        }
        saveGearBox(gearBox);
    }

    private void saveGearBox(GearBox gearBox) {
        if (gearBox == null) {
            Log.e(TAG, "The GearBox object is null, cannot save it!");
            return;
        }
        Log.d(TAG, "new gear values: " + gearBox.toString());
        GearBox oldGearBox = new Select().from(GearBox.class).executeSingle();
        if (oldGearBox == null) {
            gearBox.save();
        } else {
            Log.d(TAG, "old gear values: " + oldGearBox.toString());
            oldGearBox.setNextGear(gearBox.getNextGear());
            oldGearBox.setCurrentGear(gearBox.getCurrentGear());
            Log.d(TAG, "old gear with new values: " + oldGearBox.toString());
            oldGearBox.save();
        }
    }
}
