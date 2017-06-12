package co.com.romero.sellmything.sellmything.fragments;


import android.app.FragmentManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;


import com.j256.ormlite.stmt.query.In;

import java.util.List;

import co.com.romero.sellmything.sellmything.R;
import co.com.romero.sellmything.sellmything.activities.SellMyThing;
import co.com.romero.sellmything.sellmything.utilities.persistence.manager.ClassResultManager;
import co.com.romero.sellmything.sellmything.utilities.persistence.manager.ItemResultsManager;
import co.com.romero.sellmything.sellmything.utilities.pojos.mercadolibre.Results;
import co.com.romero.sellmything.sellmything.utilities.pojos.recognition.ClassResult;
import co.com.romero.sellmything.sellmything.utilities.rest.APIClient;
import co.com.romero.sellmything.sellmything.utilities.rest.APIInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListClassifiersFragment extends BaseFragment implements Button.OnClickListener {

    private Button btnGetMercadolibre;
    private RelativeLayout listRelativeLayout;
    private Spinner spinnerCountry;
    private String countrySelectedCode;
    private static RadioButton[] radioButtons;
    private static int valueSelected;
    private String[] codes;
    private List<ClassResult> classResultList;
    private RelativeLayout rlLoading;
    private ScrollView svContentClassifiers;

    public ListClassifiersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_classifiers, container, false);
        // Inflate the layout for this fragment
        codes = getResources().getStringArray(R.array.country_codes);


        setupUi(view);

        createRadiogroup();

        spinnerCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int index = spinnerCountry.getSelectedItemPosition();
                countrySelectedCode = codes[index];
                // Toast.makeText(SellMyThing.getContext(), "Code: "+countrySelectedCode, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                countrySelectedCode = codes[0];
                Toast.makeText(SellMyThing.getContext(), "Code nothing: "+countrySelectedCode, Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    public static ListClassifiersFragment newInstance() {
        ListClassifiersFragment instance = new ListClassifiersFragment();
        return instance;
    }

    private void setupUi(View view) {

        listRelativeLayout = (RelativeLayout) view.findViewById(R.id.rl_classes_list);

        btnGetMercadolibre = (Button) view.findViewById(R.id.btn_get_items);
        btnGetMercadolibre.setOnClickListener(this);

        spinnerCountry = (Spinner) view.findViewById(R.id.spinner_countries);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(SellMyThing.getContext(),
                R.array.country_names, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCountry.setAdapter(adapter);

        rlLoading = (RelativeLayout) view.findViewById(R.id.rl_loading);
        svContentClassifiers = (ScrollView) view.findViewById(R.id.sv_content_classifiers);
    }

    private void createRadiogroup(){
        RadioGroup rg = new RadioGroup(SellMyThing.getContext());
        classResultList = ClassResultManager.getInstance().getClassResultLocal();
        radioButtons = new RadioButton[classResultList.size()];
        rg.setOrientation(RadioGroup.VERTICAL);
        rg.setId(R.id.rg_list_classes);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        rg.setLayoutParams(lp);

        for (int i = 0; i < classResultList.size(); i++) {
            ClassResult classResult = classResultList.get(i);
            radioButtons[i] = new RadioButton(SellMyThing.getContext());
            radioButtons[i].setText(classResult.getClase()+ " - "+classResult.getScore() + "%");
            radioButtons[i].setTextColor(Color.BLACK);
            rg.addView(radioButtons[i]);
        }
        listRelativeLayout.addView(rg);
        // llMercadolibreBtns.setVisibility(View.VISIBLE);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected
                valueSelected = checkedId-1;
            }
        });
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case (R.id.btn_get_items):
                rlLoading.setVisibility(View.VISIBLE);
                setClickable(svContentClassifiers, false);
                APIInterface apiInterface = APIClient.getClientMercadolibre().create(APIInterface.class);
                String itemName = classResultList.get(valueSelected).getClase();
                Call<Results> call = apiInterface.getItems(countrySelectedCode, itemName);
                call.enqueue(new Callback<Results>() {
                    @Override
                    public void onResponse(Call<Results> call, Response<Results> response) {
                        Log.d("@@@ DEBUG", "onResponse: mercadolibre: "+response.body());
                        ItemResultsManager.getInstance().saveItemResultsesListLocal(response.body().getResultsList());
                        changeFragment();
                    }

                    @Override
                    public void onFailure(Call<Results> call, Throwable t) {
                        Log.d("@@@ DEBUG", "onFailure: ERROR MERCADOLIBRE: "+t);
                        setClickable(svContentClassifiers, true);
                        rlLoading.setVisibility(View.INVISIBLE);
                    }
                });

                break;
        }
    }

    private void changeFragment(){
        new Handler().post(new Runnable() {
            public void run() {
                FragmentManager fragmentManager = getFragmentManager();
                BaseFragment targetFragment = ListMercadolibreItemsFragment.newInstance();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, targetFragment)
                        .commit();
            }
        });

    }

    public void setClickable(View view, boolean flag) {
        if (view != null) {
            view.setClickable(flag);
            if (view instanceof ViewGroup) {
                ViewGroup vg = ((ViewGroup) view);
                for (int i = 0; i < vg.getChildCount(); i++) {
                    setClickable(vg.getChildAt(i), flag);
                }
            }
        }
    }

}