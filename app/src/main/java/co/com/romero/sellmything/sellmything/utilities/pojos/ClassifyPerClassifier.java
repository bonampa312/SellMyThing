package co.com.romero.sellmything.sellmything.utilities.pojos;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by bonam_000 on 07/06/2017.
 */

public class ClassifyPerClassifier {
    @SerializedName("name")
    private String name;
    @SerializedName("classifier_id")
    private String classifierId;
    @SerializedName("classes")
    private List<ClassResult> classes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassifierId() {
        return classifierId;
    }

    public void setClassifierId(String classifierId) {
        this.classifierId = classifierId;
    }

    public List<ClassResult> getClasses() {
        return classes;
    }

    public void setClasses(List<ClassResult> classes) {
        this.classes = classes;
    }
}
