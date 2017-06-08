package co.com.romero.sellmything.sellmything.utilities.persistence.manager;

import android.util.Log;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import co.com.romero.sellmything.sellmything.utilities.persistence.DataManager;
import co.com.romero.sellmything.sellmything.utilities.pojos.classifiers;

/**
 * Created by bonam_000 on 07/06/2017.
 */

public class classifiersManager extends DataManager {

    private static classifiersManager instance;

    public classifiersManager() {
        super();
    }

    public static classifiersManager getInstance() {
        if (instance == null) {
            instance = new classifiersManager();
        }
        return instance;
    }

    public static void saveClassifiersLocal(classifiers classifier) {
        try {
            helper.getClassifiersDao().create(classifier);
            Log.d("@@@ DEBUG", "onResponse: SUCCESS ON create " + classifier.getImage());
            //ClassifyPerClassifierManager.getInstance().saveClassifyPerClassifierListLocal(classifier.getClassifiers());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void dropTable() {
        try {
            List<classifiers> list = helper.getClassifiersDao().queryForAll();
            if (!list.isEmpty()) {
                helper.getClassifiersDao().delete(list);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static classifiers getClassifiersLocal() {
        List<classifiers> classifierses = new ArrayList<>();
        try {
            classifierses = helper.getClassifiersDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return classifierses.get(0);
    }

    public static void saveClassifierListLocal(Collection<classifiers> classifiersList){
        classifiersManager.getInstance().dropTable();
        for (classifiers cl : classifiersList) {
            classifiersManager.getInstance().saveClassifiersLocal(cl);
        }
    }
}
