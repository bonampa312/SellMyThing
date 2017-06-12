package co.com.romero.sellmything.sellmything.utilities.pojos.recognition;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by bonam_000 on 07/06/2017.
 */
@DatabaseTable
public class ClassResult {
    public static final String CLASS = "class";
    public static final String SCORE = "score";
    public static final String TYPE_HIERARCHY = "type_hierarchy";
    public static final String CLASS_RESULT_FIELD_NAME = "class_result_id";
    public static final String ID = "id";

    @DatabaseField(columnName = ID, generatedId = true)
    private int id;
    @DatabaseField(columnName = CLASS)
    @SerializedName("class")
    private String clase;
    @DatabaseField(columnName = SCORE)
    @SerializedName("score")
    private float score;
    @DatabaseField(columnName = TYPE_HIERARCHY)
    @SerializedName("type_hierarchy")
    private String typeHierarchy;
    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = CLASS_RESULT_FIELD_NAME)
    private ClassifyPerClassifier classifyPerClassifier;

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String getTypeHierarchy() {
        return typeHierarchy;
    }

    public void setTypeHierarchy(String typeHierarchy) {
        this.typeHierarchy = typeHierarchy;
    }
}
