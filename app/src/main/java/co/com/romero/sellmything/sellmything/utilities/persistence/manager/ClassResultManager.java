package co.com.romero.sellmything.sellmything.utilities.persistence.manager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.com.romero.sellmything.sellmything.utilities.persistence.DataManager;
import co.com.romero.sellmything.sellmything.utilities.pojos.ClassResult;

/**
 * Created by bonam_000 on 07/06/2017.
 */

public class ClassResultManager extends DataManager {

    private static ClassResultManager instance;

    public ClassResultManager() {
        super();
    }

    public static ClassResultManager getInstance() {
        if (instance == null) {
            instance = new ClassResultManager();
        }
        return instance;
    }

    public static void saveClassResultLocal(ClassResult classResult) {
        try {
            ClassResultManager.getInstance().dropTable();
            helper.getClassResultDao().create(classResult);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void dropTable() {
        try {
            List<ClassResult> list = helper.getClassResultDao().queryForAll();
            if (!list.isEmpty()) {
                helper.getClassResultDao().delete(list);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ClassResult getClassResultLocal() {
        List<ClassResult> classResults = new ArrayList<>();
        try {
            classResults = helper.getClassResultDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return classResults.get(0);
    }
}
