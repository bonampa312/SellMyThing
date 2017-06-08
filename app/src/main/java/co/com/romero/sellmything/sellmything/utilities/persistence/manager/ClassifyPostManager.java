package co.com.romero.sellmything.sellmything.utilities.persistence.manager;

import android.util.Log;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import co.com.romero.sellmything.sellmything.utilities.persistence.DataManager;
import co.com.romero.sellmything.sellmything.utilities.pojos.ClassResult;
import co.com.romero.sellmything.sellmything.utilities.pojos.ClassifyPerClassifier;
import co.com.romero.sellmything.sellmything.utilities.pojos.ClassifyPost;
import co.com.romero.sellmything.sellmything.utilities.pojos.classifiers;

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
            helper.dropDatabase();
            for (classifiers classifier: classifyPost.getImages()) {
                for (ClassifyPerClassifier cpc : classifier.getClassifiers()) {
                    for (ClassResult result : cpc.getClasses()) {
                        ClassResultManager.getInstance().saveClassResultLocal(result);
                    }
                    ClassifyPerClassifierManager.getInstance().saveClassyfyPerClassifierLocal(cpc);
                }
                classifiersManager.getInstance().saveClassifiersLocal(classifier);
            }
            helper.getClassifyPostDao().create(classifyPost);
            Log.d("@@@ DEBUG", "onResponse: SUCCESS ON create" + classifyPost.getImagesProcessed());
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

    public static void saveClassifyPostListLocal(Collection<ClassifyPost> list){
        for (ClassifyPost cl :
                list) {
            ClassifyPostManager.getInstance().saveClassifyPostLocal(cl);
        }
    }
}
