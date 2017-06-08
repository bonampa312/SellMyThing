package co.com.romero.sellmything.sellmything.utilities.persistence.manager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.com.romero.sellmything.sellmything.utilities.persistence.DataManager;
import co.com.romero.sellmything.sellmything.utilities.pojos.ClassifyPerClassifier;

/**
 * Created by bonam_000 on 07/06/2017.
 */

public class ClassifyPerClassifierManager extends DataManager {

    private static ClassifyPerClassifierManager instance;

    public ClassifyPerClassifierManager() {
        super();
    }

    public static ClassifyPerClassifierManager getInstance() {
        if (instance == null) {
            instance = new ClassifyPerClassifierManager();
        }
        return instance;
    }

    public static void saveClassyfyPerClassifierLocal(ClassifyPerClassifier classifyPerClassifier) {
        try {
            ClassifyPerClassifierManager.getInstance().dropTable();
            helper.getClassifyPerClassifierDao().create(classifyPerClassifier);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void dropTable() {
        try {
            List<ClassifyPerClassifier> list = helper.getClassifyPerClassifierDao().queryForAll();
            if (!list.isEmpty()) {
                helper.getClassifyPerClassifierDao().delete(list);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ClassifyPerClassifier getClassifyPostLocal() {
        List<ClassifyPerClassifier> classifyPerClassifiers = new ArrayList<>();
        try {
            classifyPerClassifiers = helper.getClassifyPerClassifierDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return classifyPerClassifiers.get(0);
    }
}
