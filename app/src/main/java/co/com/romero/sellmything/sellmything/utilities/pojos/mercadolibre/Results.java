package co.com.romero.sellmything.sellmything.utilities.pojos.mercadolibre;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Romero-Pc on 11/06/2017.
 */

public class Results {

    @SerializedName("id")
    private String id;
    @SerializedName("results")
    private List<ItemResults> resultsList;
    @SerializedName("query")
    private String query;

    public List<ItemResults> getResultsList() {
        return resultsList;
    }

    public void setResultsList(List<ItemResults> resultsList) {
        this.resultsList = resultsList;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}
