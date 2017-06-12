package co.com.romero.sellmything.sellmything.utilities.rest;

import co.com.romero.sellmything.sellmything.utilities.pojos.mercadolibre.Results;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Romero-Pc on 11/06/2017.
 */

public class MercadolibreItemsManager {
    private static MercadolibreItemsManager instance;
    private APIInterface apiInterface;

    private MercadolibreItemsManager(){
        apiInterface = APIClient.getClient(2).create(APIInterface.class);
    }

    public static MercadolibreItemsManager getInstance(){
        if (instance==null){
            instance = new MercadolibreItemsManager();
        }
        return instance;
    }

    public void getItems(final String countryCode, final String itemName){
        Call<Results> call = apiInterface.getItems(countryCode, itemName);
        call.enqueue(new Callback<Results>() {
            @Override
            public void onResponse(Call<Results> call, Response<Results> response) {

            }

            @Override
            public void onFailure(Call<Results> call, Throwable t) {

            }
        });
    }

}
