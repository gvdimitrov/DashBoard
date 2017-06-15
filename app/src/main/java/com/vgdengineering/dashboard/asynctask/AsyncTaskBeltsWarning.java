package com.vgdengineering.dashboard.asynctask;


import android.os.AsyncTask;
import android.util.Log;

import com.activeandroid.query.Select;
import com.vgdengineering.dashboard.database.entity.BeltsWarning;
import com.vgdengineering.dashboard.observables.ObservableDatabaseData;

public class AsyncTaskBeltsWarning extends AsyncTask<BeltsWarning, Void,BeltsWarning> {
    private static final String TAG = AsyncTaskBeltsWarning.class.getSimpleName();

    private static void saveBeltsWarning(BeltsWarning beltsWarning) {
        if (beltsWarning == null) {
            Log.e(TAG, "The BeltsWarning object is null, cannot save it!");
            return;
        }
        BeltsWarning oldbeltsWarning = new Select().from(BeltsWarning.class).executeSingle();
        if (oldbeltsWarning == null) {
            beltsWarning.save();
        } else {
            oldbeltsWarning.setWarningForSeatBelt(beltsWarning.isWarningForSeatBelt());
            oldbeltsWarning.setWarningSeverity(beltsWarning.getWarningSeverity());
            oldbeltsWarning.save();
        }
    }
    @Override
    protected BeltsWarning doInBackground(BeltsWarning... params) {
        saveBeltsWarning(params[0]);
        return params[0];
    }

    @Override
    protected void onPostExecute(BeltsWarning beltsWarning) {
        ObservableDatabaseData.getInstance().notifyDateSetChange(beltsWarning);
    }
}
