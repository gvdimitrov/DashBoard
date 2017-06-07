package com.vgdengineering.dashboard.database.communication;

import com.vgdengineering.dashboard.database.entity.Climatronic;
import com.vgdengineering.dashboard.database.entity.GearBox;

public interface IDao {
    void saveGearBox(GearBox gearBox);
    GearBox getGearBox();

    void saveClimatronic(Climatronic climatronic);
    Climatronic getClimatronic();
}
