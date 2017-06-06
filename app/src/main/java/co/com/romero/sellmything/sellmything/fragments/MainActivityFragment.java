package co.com.romero.sellmything.sellmything.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import co.com.romero.sellmything.sellmything.MyConstants;
import co.com.romero.sellmything.sellmything.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends BaseFragment implements Button.OnClickListener {

    public static final int ID = MyConstants.MAIN_FRAGMENT;

    public MainActivityFragment() {
    }

    /**
     * Static factory method
     * @return
     */
    public static MainActivityFragment newInstance() {
        MainActivityFragment fragment = new MainActivityFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onClick(View view) {

    }
}
