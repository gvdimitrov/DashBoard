package com.vgdengineering.dashboard.observables;

import java.util.Observable;

public class ObservableDatabaseData extends Observable {

    private static ObservableDatabaseData instance;

    /**
     * The only method that can create new instance
     * if is need or return existing one.
     *
     * @return instance of this class
     */
    public static ObservableDatabaseData getInstance() {
        if (instance == null) {
            instance = new ObservableDatabaseData();
        }
        return instance;
    }

    /**
     * This method is called when we want to notify observers for existing change
     */
    public void notifyDateSetChange() {
        setChanged();
        notifyObservers();
    }

    private ObservableDatabaseData() {}
}
