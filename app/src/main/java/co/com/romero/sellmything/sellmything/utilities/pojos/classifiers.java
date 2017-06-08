package co.com.romero.sellmything.sellmything.utilities.pojos;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Collection;

/**
 * Created by bonam_000 on 07/06/2017.
 */
@DatabaseTable
public class classifiers {

    public static final String CLASSIFIERS = "classifiers";
    public static final String IMAGE = "image";
    public static final String CLASSIFY_POST_FIELD_NAME = "classify_post_id";
    public static final String ID = "id";

    @DatabaseField(columnName = ID, generatedId = true)
    private int id;
    @ForeignCollectionField(columnName = CLASSIFIERS, eager = true)
    @SerializedName("classifiers")
    private Collection<ClassifyPerClassifier> classifiers;
    @DatabaseField(columnName = IMAGE)
    @SerializedName("image")
    private String image;
    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = CLASSIFY_POST_FIELD_NAME)
    private ClassifyPost classifyPost;

    public Collection<ClassifyPerClassifier> getClassifiers() {
        return classifiers;
    }

    public void setClassifiers(Collection<ClassifyPerClassifier> classifiers) {
        this.classifiers = classifiers;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
