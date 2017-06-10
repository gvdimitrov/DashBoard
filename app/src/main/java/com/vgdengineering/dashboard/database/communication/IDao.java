package com.vgdengineering.dashboard.database.communication;

import com.vgdengineering.dashboard.database.entity.BeltsWarning;
import com.vgdengineering.dashboard.database.entity.Climatronic;
import com.vgdengineering.dashboard.database.entity.GearBox;
import com.vgdengineering.dashboard.database.entity.Headlights;
import com.vgdengineering.dashboard.database.entity.Parktronik;
import com.vgdengineering.dashboard.database.entity.TripComputer;

public interface IDao {
    void saveGearBox(GearBox gearBox);
    GearBox getGearBox();

    void saveClimatronic(Climatronic climatronic);
    Climatronic getClimatronic();

    void saveParktronik(Parktronik parktronik);
    Parktronik getParktronik();

    void saveBeltWarning(BeltsWarning beltsWarning);
    BeltsWarning getBeltsWarning();

    void saveHeadlight(Headlights headlights);
    Headlights getHeadlights();

    void saveTripComputer(TripComputer tripComputer);
    TripComputer getTripComputer();
}
