package co.com.romero.sellmything.sellmything.utilities.persistence;

/**
 * Created by bonam_000 on 07/06/2017.
 */
import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import co.com.romero.sellmything.sellmything.utilities.pojos.ClassResult;
import co.com.romero.sellmything.sellmything.utilities.pojos.ClassifyPerClassifier;
import co.com.romero.sellmything.sellmything.utilities.pojos.ClassifyPost;
import co.com.romero.sellmything.sellmything.utilities.pojos.classifiers;

public class DBHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "sell_my_db.db";
    private static final int DATABASE_VERSION = 1;

    private Dao<classifiers, Integer> classifiersDao;
    private Dao<ClassifyPerClassifier, Integer> classifyPerClassifierDao;
    private Dao<ClassifyPost, Integer> classifyPostDao;
    private Dao<ClassResult, Integer> classResultDao;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, classifiers.class);
            TableUtils.createTable(connectionSource, ClassifyPerClassifier.class);
            TableUtils.createTable(connectionSource, ClassifyPost.class);
            TableUtils.createTable(connectionSource, ClassResult.class);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropDatabase(){
        try {
            TableUtils.dropTable(getConnectionSource(), classifiers.class, true);
            TableUtils.dropTable(getConnectionSource(), ClassifyPost.class, true);
            TableUtils.dropTable(getConnectionSource(), ClassifyPerClassifier.class, true);
            TableUtils.dropTable(getConnectionSource(), ClassResult.class, true);

            TableUtils.createTable(getConnectionSource(), ClassResult.class);
            TableUtils.createTable(getConnectionSource(), ClassifyPerClassifier.class);
            TableUtils.createTable(getConnectionSource(), classifiers.class);
            TableUtils.createTable(getConnectionSource(), ClassifyPost.class);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        onCreate(db, connectionSource);
    }

    public Dao<classifiers, Integer> getClassifiersDao() throws SQLException {
        if (classifiersDao == null){
            classifiersDao = getDao(classifiers.class);
        }
        return classifiersDao;
    }

    public Dao<ClassifyPerClassifier, Integer> getClassifyPerClassifierDao() throws SQLException {
        if (classifyPerClassifierDao == null){
            classifyPerClassifierDao = getDao(ClassifyPerClassifier.class);
        }
        return classifyPerClassifierDao;
    }

    public Dao<ClassifyPost, Integer> getClassifyPostDao() throws SQLException {
        if (classifyPostDao == null){
            classifyPostDao = getDao(ClassifyPost.class);
        }
        return classifyPostDao;
    }

    public Dao<ClassResult, Integer> getClassResultDao() throws SQLException {
        if (classResultDao == null){
            classResultDao = getDao(ClassResult.class);
        }
        return classResultDao;
    }

    @Override
    public void close() {
        super.close();
        classifiersDao = null;
        classifyPostDao = null;
        classResultDao = null;
        classifyPerClassifierDao= null;
    }

}