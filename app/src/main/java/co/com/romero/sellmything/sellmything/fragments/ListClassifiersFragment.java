package co.com.romero.sellmything.sellmything.fragments;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import java.util.List;

import co.com.romero.sellmything.sellmything.R;
import co.com.romero.sellmything.sellmything.activities.SellMyThing;
import co.com.romero.sellmything.sellmything.utilities.persistence.manager.ClassResultManager;
import co.com.romero.sellmything.sellmything.utilities.persistence.manager.ClassifyPostManager;
import co.com.romero.sellmything.sellmything.utilities.pojos.ClassResult;
import co.com.romero.sellmything.sellmything.utilities.pojos.ClassifyPost;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListClassifiersFragment extends BaseFragment implements Button.OnClickListener {

    private Button getListButton;
    private RelativeLayout listRelativeLayout;

    public ListClassifiersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_classifiers, container, false);
        // Inflate the layout for this fragment
        getListButton = (Button) view.findViewById(R.id.btn_get_list);
        listRelativeLayout = (RelativeLayout) view.findViewById(R.id.rl_classes_list) ;

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
                List<ClassResult> classResultList = ClassResultManager.getInstance().getClassResultLocal();
                RadioGroup rg = new RadioGroup(SellMyThing.getContext());
                rg.setOrientation(RadioGroup.VERTICAL);
                RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                RadioButton[] radioButtons = new RadioButton[classResultList.size()];
                rg.setLayoutParams(lp);

                for (int i = 0; i< classResultList.size(); i++) {
                    ClassResult classResult = classResultList.get(i);
                    radioButtons[i] = new RadioButton(SellMyThing.getContext());
                    radioButtons[i].setText(classResult.getClase());
                    radioButtons[i].setTextColor(Color.BLACK);
                    rg.addView(radioButtons[i]);
                }
                listRelativeLayout.addView(rg);
                break;
        }
    }
}