package co.com.romero.sellmything.sellmything.utilities.pojos;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by bonam_000 on 07/06/2017.
 */

public class ClassifyPost {
    @SerializedName("images")
    private List<classifiers> images;
    @SerializedName("custom_classes")
    private String customClasses;
    @SerializedName("images_processed")
    private String imagesProcessed;

    public List<classifiers> getImages() {
        return images;
    }

    public void setImages(List<classifiers> images) {
        this.images = images;
    }

    public String getCustomClasses() {
        return customClasses;
    }

    public void setCustomClasses(String customClasses) {
        this.customClasses = customClasses;
    }

    public String getImagesProcessed() {
        return imagesProcessed;
    }

    public void setImagesProcessed(String imagesProcessed) {
        this.imagesProcessed = imagesProcessed;
    }

}