package co.com.romero.sellmything.sellmything.activities;

import android.app.Application;
import android.content.Context;
/**
 * Created by bonam_000 on 07/06/2017.
 */

public class SellMyThing extends Application {
    private static Context context;
    private static MainActivity mainActivity;

    public SellMyThing() {}

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        SellMyThing.context = context;
    }

    public static MainActivity getMainActivity() {
        return mainActivity;
    }

    public static void setMainActivity(MainActivity mainActivity) {
        SellMyThing.mainActivity = mainActivity;
    }
}
