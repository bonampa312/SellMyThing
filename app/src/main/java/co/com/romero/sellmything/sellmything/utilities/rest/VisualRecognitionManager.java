package co.com.romero.sellmything.sellmything.utilities.rest;

import android.provider.SyncStateContract;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.util.List;

import co.com.romero.sellmything.sellmything.R;
import co.com.romero.sellmything.sellmything.utilities.MyConstants;
import co.com.romero.sellmything.sellmything.utilities.persistence.manager.ClassifyPostManager;
import co.com.romero.sellmything.sellmything.utilities.pojos.ClassResult;
import co.com.romero.sellmything.sellmything.utilities.pojos.ClassifyPost;
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
        apiInterface = APIClient.getClient().create(APIInterface.class);
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
                Log.d("@@@ DEBUG", "onResponse: SUCCESS ON CALL :D" + response);
                ClassifyPostManager.getInstance().saveClassifyPostLocal(response.body());
            }

            @Override
            public void onFailure(Call<ClassifyPost> call, Throwable t) {
                Log.d("@@@ DEBUG", "onResponse: FAILURE ON CALL", t);
            }
        });
    }
}
