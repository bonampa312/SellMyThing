package co.com.romero.sellmything.sellmything.utilities.pojos;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by bonam_000 on 07/06/2017.
 */

public class classifiers {
    @SerializedName("classifiers")
    private List<ClassifyPerClassifier> classifiers;
    @SerializedName("image")
    private String image;

    public List<ClassifyPerClassifier> getClassifiers() {
        return classifiers;
    }

    public void setClassifiers(List<ClassifyPerClassifier> classifiers) {
        this.classifiers = classifiers;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
