package co.com.romero.sellmything.sellmything.utilities.rest;

/**
 * Created by bonam_000 on 06/06/2017.
 */

public class VisualRecognitionManager {

    private static VisualRecognitionManager instance;

    private VisualRecognitionManager() {
    }

    public static VisualRecognitionManager getInstance() {
        if (instance == null) {
            instance = new VisualRecognitionManager();
        }
        return instance;
    }

    public void recognizeImage(final String imageUrl) {

    }

}
