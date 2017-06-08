package co.com.romero.sellmything.sellmything.utilities.persistence.manager;

import android.util.Log;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import co.com.romero.sellmything.sellmything.utilities.persistence.DataManager;
import co.com.romero.sellmything.sellmything.utilities.pojos.ClassResult;
import co.com.romero.sellmything.sellmything.utilities.pojos.ClassifyPost;

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
            helper.getClassResultDao().create(classResult);
            Log.d("@@@ DEBUG", "onResponse: SUCCESS ON create " + classResult.getClase());
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

    public static List<ClassResult> getClassResultLocal() {
        List<ClassResult> classResults = new ArrayList<>();
        try {
            classResults = helper.getClassResultDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return classResults;
    }

    public static void saveClassifyPostListLocal(Collection<ClassResult> list){
        ClassResultManager.getInstance().dropTable();
        for (ClassResult cl : list) {
            ClassResultManager.getInstance().saveClassResultLocal(cl);
        }
    }

}
