package co.com.romero.sellmything.sellmything.utilities.persistence.manager;

import android.util.Log;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import co.com.romero.sellmything.sellmything.utilities.persistence.DataManager;
import co.com.romero.sellmything.sellmything.utilities.pojos.mercadolibre.ItemResults;

/**
 * Created by Romero-Pc on 12/06/2017.
 */

public class ItemResultsManager extends DataManager{

    private static ItemResultsManager instance;

    public ItemResultsManager() {
        super();
    }

    public static ItemResultsManager getInstance(){
        if (instance==null){
            instance = new ItemResultsManager();
        }
        return instance;
    }

    public static void saveItemResultsLocal(ItemResults itemResults){
        try {
            helper.getItemResultDao().create(itemResults);
        } catch (SQLException e){
            Log.d("@@@ DEBUG", "onResponse: ERROR ON create " + itemResults.getTitle());
        }
    }
    private void dropTable() {
        try {
            List<ItemResults> list = helper.getItemResultDao().queryForAll();
            if (!list.isEmpty()) {
                helper.getItemResultDao().delete(list);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<ItemResults> getItemResultsLocal() {
        List<ItemResults> itemResults = new ArrayList<>();
        try {
            itemResults = helper.getItemResultDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return itemResults;
    }

    public static void saveItemResultsesListLocal(Collection<ItemResults> itemResultses){
        ItemResultsManager.getInstance().dropTable();
        for (ItemResults cl : itemResultses) {
            AddressManager.getInstance().saveAddressLocal(cl.getAddress());
            ItemResultsManager.getInstance().saveItemResultsLocal(cl);
        }
        Log.d("@@@ DEBUG", "saveItemResultsesListLocal: SUCCES SAVING THIS");
    }
    
}
