package com.vgdengineering.dashboard.database.communication;

import android.util.Log;

import com.activeandroid.query.Select;
import com.vgdengineering.dashboard.asynctask.AsyncTaskBeltsWarning;
import com.vgdengineering.dashboard.asynctask.AsyncTaskHeadlight;
import com.vgdengineering.dashboard.asynctask.AsyncTaskTripComputer;
import com.vgdengineering.dashboard.database.entity.BeltsWarning;
import com.vgdengineering.dashboard.database.entity.Climatronic;
import com.vgdengineering.dashboard.database.entity.GearBox;
import com.vgdengineering.dashboard.database.entity.Headlights;
import com.vgdengineering.dashboard.database.entity.Light;
import com.vgdengineering.dashboard.database.entity.Parktronik;
import com.vgdengineering.dashboard.asynctask.AsyncTaskClimatronic;
import com.vgdengineering.dashboard.asynctask.AsyncTaskGearBox;
import com.vgdengineering.dashboard.asynctask.AsyncTaskParktronik;
import com.vgdengineering.dashboard.database.entity.TripComputer;

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
        if (parktronik == null) {
            Log.e(TAG, "Parktronik is null, cannot save it");
            return;
        }
        new AsyncTaskParktronik().execute(parktronik);
    }

    @Override
    public Parktronik getParktronik() {
        Parktronik parktronik = new Select().from(Parktronik.class).executeSingle();
        if (parktronik == null) {
            Log.e(TAG, "The Parktronik table is empty");
        }
        return parktronik;
    }

    @Override
    public void saveBeltWarning(BeltsWarning beltsWarning) {
        if (beltsWarning == null) {
            Log.e(TAG, "BeltsWarning is null, cannot save it");
            return;
        }
        new AsyncTaskBeltsWarning().execute(beltsWarning);
    }

    @Override
    public BeltsWarning getBeltsWarning() {
        BeltsWarning beltsWarning = new Select().from(BeltsWarning.class).executeSingle();
        if (beltsWarning == null) {
            Log.e(TAG, "The BeltsWarning is empty");
        }
        return beltsWarning;
    }

    @Override
    public void saveHeadlight(Headlights headlights) {
        if (headlights == null) {
            Log.e(TAG, "Headlights is null, cannot save it");
            return;
        }
        new AsyncTaskHeadlight().execute(headlights);
    }

    @Override
    public Headlights getHeadlights() {
        Headlights headlights = new Select().from(Headlights.class).executeSingle();
        if (headlights == null) {
            Log.e(TAG, "The Headlights is empty");
        }
        return headlights;
    }

    @Override
    public void saveTripComputer(TripComputer tripComputer) {
        if (tripComputer == null) {
            Log.d(TAG, "TripComputer is null, cannot save it");
            return;
        }
        new AsyncTaskTripComputer().execute(tripComputer);
    }

    @Override
    public TripComputer getTripComputer() {
        TripComputer tripComputer = new Select().from(TripComputer.class).executeSingle();
        if (tripComputer == null) {
            Log.e(TAG, "The Headlights is empty");
        }
        return tripComputer;
    }

    public static void createDummyData() {
        if(instance.getClimatronic() == null) {
            instance.saveClimatronic(new Climatronic(28, 21, 3));
        }

        if(instance.getParktronik() == null) {
            instance.saveParktronik(new Parktronik(0, 2));
        }

        if(instance.getGearBox() == null) {
            instance.saveGearBox(new GearBox(2, 3));
        }

        if(instance.getBeltsWarning() == null) {
            instance.saveBeltWarning(new BeltsWarning(true, BeltsWarning.Priority.HIGH.getPriority()));
        }

        if(instance.getHeadlights() == null) {
            instance.saveHeadlight(new Headlights(new Light(true, 2), new Light(false, 1), new Light(true, 5)));
        }

        if(instance.getTripComputer() == null) {
            instance.saveTripComputer(new TripComputer(1500));
        }
    }

}
