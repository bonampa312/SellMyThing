package co.com.romero.sellmything.sellmything.utilities.rest;

import android.util.Log;
import android.widget.Toast;

import java.io.File;

import co.com.romero.sellmything.sellmything.activities.SellMyThing;
import co.com.romero.sellmything.sellmything.utilities.MyConstants;
import co.com.romero.sellmything.sellmything.utilities.persistence.manager.ClassifyPostManager;
import co.com.romero.sellmything.sellmything.utilities.pojos.recognition.ClassifyPost;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by bonam_000 on 06/06/2017.
 */

public class VisualRecognitionManager {

    private static VisualRecognitionManager instance;
    private APIInterface apiInterface;

    private VisualRecognitionManager() {
        apiInterface = APIClient.getClientWatson().create(APIInterface.class);
    }

    public static VisualRecognitionManager getInstance() {
        if (instance == null) {
            instance = new VisualRecognitionManager();
        }
        return instance;
    }

    public void recognizeImage(final String imageUrl) {
        File file = new File(imageUrl);
        RequestBody image = RequestBody.create(MediaType.parse("image/*"), file);
        Call<ClassifyPost> call = apiInterface.classifyImage(MyConstants.WATSON_API_KEY, MyConstants.WATSON_VERSION, image);
        call.enqueue(new Callback<ClassifyPost>() {

            @Override
            public void onResponse(Call<ClassifyPost> call, Response<ClassifyPost> response) {
                ClassifyPostManager.getInstance().saveClassifyPostLocal(response.body());
                Toast.makeText(SellMyThing.getContext(), "Ready to classify :D", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ClassifyPost> call, Throwable t) {
                Log.d("@@@ DEBUG", "onResponse: FAILURE ON CALL", t);
            }
        });
    }
}
