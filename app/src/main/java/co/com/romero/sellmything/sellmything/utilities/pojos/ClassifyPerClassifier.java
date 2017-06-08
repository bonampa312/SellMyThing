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
public class ClassifyPerClassifier {

    public static final String NAME = "name";
    public static final String CLASSIFIER_ID = "classifier_id";
    public static final String CLASSES = "classes";
    public static final String CLASSIFIERS_FIELD_NAME = "classifiers_id";
    public static final String ID = "id";

    @DatabaseField(columnName = ID, generatedId = true)
    private int id;
    @DatabaseField(columnName = NAME)
    @SerializedName("name")
    private String name;
    @DatabaseField(columnName = CLASSIFIER_ID)
    @SerializedName("classifier_id")
    private String classifierId;
    @ForeignCollectionField(columnName = CLASSES)
    @SerializedName("classes")
    private Collection<ClassResult> classes;
    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = CLASSIFIERS_FIELD_NAME)
    private classifiers classifiers;

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

    public Collection<ClassResult> getClasses() {
        return classes;
    }

    public void setClasses(Collection<ClassResult> classes) {
        this.classes = classes;
    }
}
