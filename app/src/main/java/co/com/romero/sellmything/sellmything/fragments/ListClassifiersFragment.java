package co.com.romero.sellmything.sellmything.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

import co.com.romero.sellmything.sellmything.R;
import co.com.romero.sellmything.sellmything.utilities.persistence.manager.ClassResultManager;
import co.com.romero.sellmything.sellmything.utilities.persistence.manager.ClassifyPostManager;
import co.com.romero.sellmything.sellmything.utilities.pojos.ClassResult;
import co.com.romero.sellmything.sellmything.utilities.pojos.ClassifyPerClassifier;
import co.com.romero.sellmything.sellmything.utilities.pojos.ClassifyPost;
import co.com.romero.sellmything.sellmything.utilities.pojos.classifiers;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListClassifiersFragment extends BaseFragment implements Button.OnClickListener {

    private Button getListButton;

    public ListClassifiersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_classifiers, container, false);
        // Inflate the layout for this fragment
        getListButton = (Button) view.findViewById(R.id.btn_get_list);

        getListButton.setOnClickListener(this);
        return view;
    }

    public static ListClassifiersFragment newInstance() {
        ListClassifiersFragment instance = new ListClassifiersFragment();
        return instance;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case (R.id.btn_get_list):
                ClassifyPost classifyPost = ClassifyPostManager.getInstance().getClassifyPostLocal();
                List<ClassResult> classResultList = ClassResultManager.getInstance().getClassResultLocal();
                for (ClassResult classResult : classResultList) {
                    Log.d("@@@ DEBUG", "Class: " + classResult.getClase() + " Score: " + classResult.getClase());
                }

                Log.d("@@@ DEBUG", "onClick: SUCCESS MADAFACA  " + classifyPost);
                break;
        }
    }
}
