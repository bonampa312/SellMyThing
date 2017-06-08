package co.com.romero.sellmything.sellmything.utilities.persistence.manager;

import java.sql.SQLException;
import java.util.ArrayList;
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
            classifiersManager.getInstance().dropTable();
            helper.getClassifiersDao().create(classifier);
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

    public static classifiers getClassifyPostLocal() {
        List<classifiers> classifierses = new ArrayList<>();
        try {
            classifierses = helper.getClassifiersDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return classifierses.get(0);
    }
}
