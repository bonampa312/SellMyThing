package co.com.romero.sellmything.sellmything.utilities.persistence.manager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.com.romero.sellmything.sellmything.utilities.persistence.DataManager;
import co.com.romero.sellmything.sellmything.utilities.pojos.ClassifyPost;

/**
 * Created by bonam_000 on 07/06/2017.
 */

public class ClassifyPostManager extends DataManager {

    private static ClassifyPostManager instance;

    public ClassifyPostManager() {
        super();
    }

    public static ClassifyPostManager getInstance() {
        if (instance == null) {
            instance = new ClassifyPostManager();
        }
        return instance;
    }

    public static void saveClassifyPostLocal(ClassifyPost classifyPost) {
        try {
            ClassifyPostManager.getInstance().dropTable();
            helper.getClassifyPostDao().create(classifyPost);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void dropTable() {
        try {
            List<ClassifyPost> list = helper.getClassifyPostDao().queryForAll();
            if (!list.isEmpty()) {
                helper.getClassifyPostDao().delete(list);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ClassifyPost getClassifyPostLocal() {
        List<ClassifyPost> classifyPosts = new ArrayList<>();
        try {
            classifyPosts = helper.getClassifyPostDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return classifyPosts.get(0);
    }
}
