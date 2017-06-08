package co.com.romero.sellmything.sellmything.fragments;

import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import co.com.romero.sellmything.sellmything.activities.MainActivity;
import co.com.romero.sellmything.sellmything.utilities.MyConstants;
import co.com.romero.sellmything.sellmything.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends BaseFragment implements Button.OnClickListener {

    public static final int ID = MyConstants.MAIN_FRAGMENT;
    private Button sellMyThingButton;

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
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        sellMyThingButton = (Button) view.findViewById(R.id.btn_main_camera);

        sellMyThingButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case (R.id.btn_main_camera):
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.setFragment(CameraFragment.ID);
                break;
        }
    }
}
