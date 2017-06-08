package co.com.romero.sellmything.sellmything.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import co.com.romero.sellmything.sellmything.R;
import co.com.romero.sellmything.sellmything.utilities.MyConstants;

/**
 * A simple {@link Fragment} subclass.
 */
public class StartSplashFragment extends BaseFragment {

    public static final int ID = MyConstants.SPLASH_FRAGMENT;

    /**
     * Default empty constructor.
     */
    public StartSplashFragment() {
        super();
    }

    /**
     * Static factory method
     *
     * @return
     */
    public static StartSplashFragment newInstance() {
        StartSplashFragment fragment = new StartSplashFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_start_splash, container, false);
    }

}
