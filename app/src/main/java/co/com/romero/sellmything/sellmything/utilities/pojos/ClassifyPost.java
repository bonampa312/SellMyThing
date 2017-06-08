package co.com.romero.sellmything.sellmything.utilities.pojos;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Collection;
import java.util.List;

/**
 * Created by bonam_000 on 07/06/2017.
 */
@DatabaseTable
public class ClassifyPost {
    public static final String IMAGES = "images";
    public static final String CUSTOM_CLASSES = "custom_classes";
    public static final String IMAGES_PROCESSED = "images_processed";
    public static final String ID = "id";

    @DatabaseField(columnName = ID, generatedId = true)
    private int id;
    @ForeignCollectionField(columnName = IMAGES)
    @SerializedName("images")
    private Collection<classifiers> images;
    @DatabaseField(columnName = CUSTOM_CLASSES)
    @SerializedName("custom_classes")
    private String customClasses;
    @DatabaseField(columnName = IMAGES_PROCESSED)
    @SerializedName("images_processed")
    private String imagesProcessed;

    public Collection<classifiers> getImages() {
        return images;
    }

    public void setImages(Collection<classifiers> images) {
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