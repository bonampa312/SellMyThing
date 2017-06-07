package co.com.romero.sellmything.sellmything.utilities.pojos;

import com.google.gson.annotations.SerializedName;

/**
 * Created by bonam_000 on 07/06/2017.
 */

public class ClassResult {
    @SerializedName("class")
    private String clase;
    @SerializedName("score")
    private float score;
    @SerializedName("type_hierarchy")
    private String typeHierarchy;

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
