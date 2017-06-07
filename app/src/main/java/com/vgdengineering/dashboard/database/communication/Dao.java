package com.vgdengineering.dashboard.database.communication;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.activeandroid.query.Select;
import com.vgdengineering.dashboard.DashboardApplication;
import com.vgdengineering.dashboard.database.entity.Climatronic;
import com.vgdengineering.dashboard.database.entity.GearBox;
import com.vgdengineering.dashboard.database.entity.Parktronik;
import com.vgdengineering.dashboard.services.ServiceClimatronic;
import com.vgdengineering.dashboard.services.ServiceGearBox;
import com.vgdengineering.dashboard.services.ServiceParktronik;

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
        Context context = DashboardApplication.getAppContext();
        Intent startService = new Intent(context, ServiceGearBox.class);
        startService.putExtra(ServiceGearBox.GEAR_BOX, gearBox);
        context.startService(startService);
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
        Context context = DashboardApplication.getAppContext();
        Intent startService = new Intent(context, ServiceClimatronic.class);
        startService.putExtra(ServiceClimatronic.CLIMATRONIC, climatronic);
        context.startService(startService);
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
        Context context = DashboardApplication.getAppContext();
        Intent startService = new Intent(context, ServiceParktronik.class);
        startService.putExtra(ServiceParktronik.PARKTRONIK, parktronik);
        context.startService(startService);
    }

    @Override
    public Parktronik getParktronik() {
        Parktronik parktronik = new Select().from(Parktronik.class).executeSingle();
        if(parktronik== null){
            Log.d(TAG, "The Parktrnik table is empty");
        }
        return parktronik;
    }
}
