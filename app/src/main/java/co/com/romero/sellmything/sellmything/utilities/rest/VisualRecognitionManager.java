package co.com.romero.sellmything.sellmything.utilities.rest;

import android.os.AsyncTask;
import android.util.Log;

import java.io.File;
import java.util.List;
import java.util.Date;

import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifyImagesOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ImageClassification;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualClassification;

/**
 * Created by bonam_000 on 06/06/2017.
 */

public class VisualRecognitionManager {

    private static VisualRecognitionManager instance;
    private final VisualRecognition vision;

    private VisualRecognitionManager() {
        vision = new VisualRecognition(VisualRecognition.VERSION_DATE_2016_05_20);
        vision.setApiKey("e51965d6a5c80c6e38fb84f68e69d689f1ebf161");
    }

    public static VisualRecognitionManager getInstance() {
        if (instance == null) {
            instance = new VisualRecognitionManager();
        }
        return instance;
    }

    public void recognizeImage(final String imageUrl) {
        ClassifyImage classifyImage = new ClassifyImage();
        classifyImage.execute(imageUrl);
 /*
        try {
            try {
                ClassifyImagesOptions classifyOptions = new ClassifyImagesOptions.Builder().images(new File(imageUrl)).build();
                VisualClassification execute = vision.classify(classifyOptions).execute();
                List<ImageClassification> imageClassifiers = execute.getImages();
                if (!imageClassifiers.isEmpty()) {
                    Log.d("@@@ DEBUG", "recognizeImage: SUCCESS");
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.d("@@@ DEBUG", "recognizeImage: ERROR2",e);
            }
        } catch (Exception e) {
            Log.d("@@@ DEBUG", "recognizeImage: ERROR3",e);
        }
    }

                Thread thread = new Thread(new Runnable(){
                    public void run() {
                        try {
                            ClassifyImagesOptions classifyOptions = new ClassifyImagesOptions.Builder().images(new File(imageUrl)).build();
                            VisualClassification execute = vision.classify(classifyOptions).execute();
                            List<ImageClassification> imageClassifiers = execute.getImages();
                            if (!imageClassifiers.isEmpty()) {
                                Log.d("@@@ DEBUG", "recognizeImage: SUCCESS");
                            }
                        } catch (Exception e) {
                            Log.d("@@@ DEBUG", "recognizeImage: ERROR1",e);
                        }
                    }
                });
                thread.start();*/
    }

    private class ClassifyImage extends AsyncTask<String, Integer, Boolean>{

        @Override
        protected Boolean doInBackground(String... params) {
            try {
                String imageUrl = params[0];
                Log.d("@@@ DEBUG", "recognizeImage: "+imageUrl);
                ClassifyImagesOptions classifyOptions = new ClassifyImagesOptions.Builder().images(new File(imageUrl)).build();
                VisualClassification execute = vision.classify(classifyOptions).execute();
                List<ImageClassification> imageClassifiers = execute.getImages();
                if (!imageClassifiers.isEmpty()) {
                    Log.d("@@@ DEBUG", "recognizeImage: SUCCESS");
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.d("@@@ DEBUG", "recognizeImage: ERROR2",e);
                return false;
            }
            return false;
        }
    }
}
