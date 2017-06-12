package co.com.romero.sellmything.sellmything.utilities.pojos.mercadolibre;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Romero-Pc on 11/06/2017.
 */

public class Address {
    @SerializedName("state_name")
    private String stateName;
    @SerializedName("city_name")
    private String cityName;

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
