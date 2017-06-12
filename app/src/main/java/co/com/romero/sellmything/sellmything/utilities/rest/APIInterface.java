package co.com.romero.sellmything.sellmything.utilities.rest;


import co.com.romero.sellmything.sellmything.utilities.pojos.mercadolibre.ItemResults;
import co.com.romero.sellmything.sellmything.utilities.pojos.mercadolibre.Results;
import co.com.romero.sellmything.sellmything.utilities.pojos.recognition.ClassifyPost;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by anupamchugh on 09/01/17.
 */

public interface APIInterface {

    @Multipart
    @Headers("Accept-Language: es")
    @POST("v3/classify?")
    Call<ClassifyPost> classifyImage(@Query("api_key") String apiKey, @Query("version") String apiVersion, @Part("image_file") RequestBody imageFile);

    @GET("sites/{country_code}/search?q={item_name}")
    Call<Results> getItems(@Path("country_code") String countryCode, @Path("item_name") String itemName);

}
