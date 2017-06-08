package co.com.romero.sellmything.sellmything.utilities.persistence;

import com.j256.ormlite.android.apptools.OpenHelperManager;

import java.util.ArrayList;
import java.util.List;

import co.com.romero.sellmything.sellmything.activities.SellMyThing;

/**
 * Created by Santiago Romero on 20/06/2016.
 */
public class DataManager {
    protected static DBHelper helper;
    private List<OnDataChangeListener> listeners;

    public DataManager() {
        listeners = new ArrayList<>();
        helper = OpenHelperManager.getHelper(
                SellMyThing.getContext(), DBHelper.class);
    }

    public void addListener(OnDataChangeListener listener) {
        listeners.add(listener);
    }

    public void notifyDataChanged() {
        for (OnDataChangeListener listener : listeners) {
            listener.onDataChange();
        }
    }

}
