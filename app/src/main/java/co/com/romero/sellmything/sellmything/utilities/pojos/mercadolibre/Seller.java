package co.com.romero.sellmything.sellmything.utilities.pojos.mercadolibre;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Romero-Pc on 11/06/2017.
 */

public class Seller {
    @SerializedName("id")
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
