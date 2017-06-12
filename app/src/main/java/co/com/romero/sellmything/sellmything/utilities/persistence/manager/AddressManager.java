package co.com.romero.sellmything.sellmything.utilities.persistence.manager;

import android.util.Log;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import co.com.romero.sellmything.sellmything.utilities.persistence.DataManager;
import co.com.romero.sellmything.sellmything.utilities.pojos.mercadolibre.Address;

/**
 * Created by Romero-Pc on 12/06/2017.
 */

public class AddressManager extends DataManager{

    private static AddressManager instance;

    public AddressManager() {
        super();
    }

    public static AddressManager getInstance(){
        if (instance==null){
            instance = new AddressManager();
        }
        return instance;
    }

    public static void saveAddressLocal(Address address){
        try {
            helper.getAddressDao().create(address);
        } catch (SQLException e){
            Log.d("@@@ DEBUG", "onResponse: ERROR ON create " + address.getCityName());
        }
    }
    private void dropTable() {
        try {
            List<Address> list = helper.getAddressDao().queryForAll();
            if (!list.isEmpty()) {
                helper.getAddressDao().delete(list);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Address> getAddressLocal() {
        List<Address> addresses = new ArrayList<>();
        try {
            addresses = helper.getAddressDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return addresses;
    }

    public static Address getAddressLocal(int id) {
        Address addresses = new Address();
        try {
            addresses = helper.getAddressDao().queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return addresses;
    }

    public static void saveAddressesListLocal(Collection<Address> addresses){
        AddressManager.getInstance().dropTable();
        for (Address cl : addresses) {
            AddressManager.getInstance().saveAddressLocal(cl);
        }
    }
}
