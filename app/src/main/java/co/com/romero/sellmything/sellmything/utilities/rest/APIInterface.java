package co.com.romero.sellmything.sellmything.utilities.rest;


import co.com.romero.sellmything.sellmything.utilities.pojos.ClassifyPost;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by anupamchugh on 09/01/17.
 */

interface APIInterface {

    @Multipart
    @POST("v3/classify?")
    Call<ClassifyPost> classifyImage(@Query("api_key") String apiKey, @Query("version") String apiVersion, @Part("image_file") RequestBody imageFile);

}
