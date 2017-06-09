package com.vgdengineering.dashboard.asynctask;


import android.os.AsyncTask;
import android.util.Log;

import com.activeandroid.query.Select;
import com.vgdengineering.dashboard.database.entity.BeltsWarning;
import com.vgdengineering.dashboard.observables.ObservableDatabaseData;

public class AsyncTaskBeltsWarning extends AsyncTask<BeltsWarning, Void,Void> {
    private static final String TAG = AsyncTaskBeltsWarning.class.getSimpleName();

    private static void saveBeltsWarning(BeltsWarning beltsWarning) {
        if (beltsWarning == null) {
            Log.e(TAG, "The BeltsWarning object is null, cannot save it!");
            return;
        }
        Log.d(TAG, "new beltsWarning values: " + beltsWarning.toString());
        BeltsWarning oldbeltsWarning = new Select().from(BeltsWarning.class).executeSingle();
        if (oldbeltsWarning == null) {
            beltsWarning.save();
        } else {
            Log.d(TAG, "old beltsWarning values: " + oldbeltsWarning.toString());
            oldbeltsWarning.setWarningForSeatBelt(beltsWarning.isWarningForSeatBelt());
            oldbeltsWarning.setWarningSeverity(beltsWarning.getWarningSeverity());
            Log.d(TAG, "old beltsWarning with new values: " + oldbeltsWarning.toString());
            oldbeltsWarning.save();
        }
    }
    @Override
    protected Void doInBackground(BeltsWarning... params) {
        saveBeltsWarning(params[0]);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        ObservableDatabaseData.getInstance().notifyDateSetChange();
    }
}
