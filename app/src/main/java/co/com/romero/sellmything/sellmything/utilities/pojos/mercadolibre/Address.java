package co.com.romero.sellmything.sellmything.utilities.pojos.mercadolibre;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;

/**
 * Created by Romero-Pc on 11/06/2017.
 */

public class Address {
    public static final String CITY_NAME = "city_name";
    public static final String ID = "id";
    public static final String ADDRESS_FIELD_NAME = "address_id";


    @DatabaseField(columnName = ID, generatedId = true)
    private int id;
    @DatabaseField(columnName = CITY_NAME)
    @SerializedName("city_name")
    private String cityName;
    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = ADDRESS_FIELD_NAME)
    private ItemResults itemResults;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
