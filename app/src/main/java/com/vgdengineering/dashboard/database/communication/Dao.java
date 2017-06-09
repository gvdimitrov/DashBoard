package com.vgdengineering.dashboard.database.communication;

import android.util.Log;

import com.activeandroid.query.Select;
import com.vgdengineering.dashboard.asynctask.AsyncTaskBeltsWarning;
import com.vgdengineering.dashboard.database.entity.BeltsWarning;
import com.vgdengineering.dashboard.database.entity.Climatronic;
import com.vgdengineering.dashboard.database.entity.GearBox;
import com.vgdengineering.dashboard.database.entity.Parktronik;
import com.vgdengineering.dashboard.asynctask.AsyncTaskClimatronic;
import com.vgdengineering.dashboard.asynctask.AsyncTaskGearBox;
import com.vgdengineering.dashboard.asynctask.AsyncTaskParktronik;

public class Dao implements IDao {

    private static final String TAG = Dao.class.getSimpleName();

    private static Dao instance;

    private Dao() {
    }

    /**
     * Use this method only to create object
     * of this class for the first time
     *
     * @return
     */
    public static void create() {
        if (instance == null) {
            instance = new Dao();
        }
    }

    /**
     * Use this method to get instance of this object
     *
     * @return
     */
    public static Dao getInstance() {
        if (instance == null) {
            Log.e(TAG, "The instance is null");
        }
        return instance;
    }

    @Override
    public void saveGearBox(GearBox gearBox) {
        if (gearBox == null) {
            Log.e(TAG, "gear box is null");
            return;
        }
        new AsyncTaskGearBox().execute(gearBox);
    }

    @Override
    public GearBox getGearBox() {
        GearBox gearBox = new Select().from(GearBox.class).executeSingle();
        if (gearBox != null) {
            return gearBox;
        }
        Log.e(TAG, "The gear box table is empty! ");
        return null;
    }

    @Override
    public void saveClimatronic(Climatronic climatronic) {
        if (climatronic == null) {
            Log.e(TAG, "climatronic is null");
            return;
        }
        new AsyncTaskClimatronic().execute(climatronic);
    }

    @Override
    public Climatronic getClimatronic() {
        Climatronic climatronic = new Select().from(Climatronic.class).executeSingle();
        if (climatronic != null) {
            return climatronic;
        }
        Log.e(TAG, "The Climatronic table is empty! ");
        return null;
    }

    @Override
    public void saveParktronik(Parktronik parktronik) {
        if(parktronik == null){
            Log.e(TAG, "Parktronik is null, cannot save it");
            return;
        }
        new AsyncTaskParktronik().execute(parktronik);
    }

    @Override
    public Parktronik getParktronik() {
        Parktronik parktronik = new Select().from(Parktronik.class).executeSingle();
        if(parktronik== null){
            Log.e(TAG, "The Parktronik table is empty");
        }
        return parktronik;
    }

    @Override
    public void saveBeltWarning(BeltsWarning beltsWarning) {
        if(beltsWarning == null){
            Log.e(TAG, "BeltsWarning is null, cannot save it");
            return;
        }
        new AsyncTaskBeltsWarning().execute(beltsWarning);
    }

    @Override
    public BeltsWarning getBeltsWarning() {
        BeltsWarning beltsWarning = new Select().from(BeltsWarning.class).executeSingle();
        if(getParktronik() == null){
            Log.e(TAG, "The BeltsWarning is empty");
        }
        return beltsWarning;
    }

    public static void createDummyData(){
            instance.saveClimatronic(new Climatronic(28,21,3));
            instance.saveParktronik(new Parktronik(0,2));
            instance.saveGearBox(new GearBox(2,3));
            instance.saveBeltWarning(new BeltsWarning(true, BeltsWarning.Priority.HIGH.getPriority()));
    }

}
