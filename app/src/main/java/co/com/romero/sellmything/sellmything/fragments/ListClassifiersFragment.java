package co.com.romero.sellmything.sellmything.fragments;


import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
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
import android.widget.Spinner;
import android.widget.Toast;

import com.mercadolibre.android.sdk.ApiResponse;
import com.mercadolibre.android.sdk.Identity;
import com.mercadolibre.android.sdk.Meli;

import java.util.List;

import co.com.romero.sellmything.sellmything.R;
import co.com.romero.sellmything.sellmything.activities.LoginScreen;
import co.com.romero.sellmything.sellmything.activities.MainActivity;
import co.com.romero.sellmything.sellmything.activities.SellMyThing;
import co.com.romero.sellmything.sellmything.utilities.MyConstants;
import co.com.romero.sellmything.sellmything.utilities.persistence.manager.ClassResultManager;
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

    private Button getListButton;
    private Button btnLogin;
    private Button btnGetMercadolibre;
    private Button btnPost;
    private RelativeLayout listRelativeLayout;
    private LinearLayout llMercadolibreBtns;
    private static String classSelected;
    private Spinner spinnerCountry;
    private String countrySelectedCode;
    private static RadioButton[] radioButtons;
    private static int valueSelected;
    private String[] codes;

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

        spinnerCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int index = spinnerCountry.getSelectedItemPosition();
                countrySelectedCode = codes[index];
                Toast.makeText(SellMyThing.getContext(), "Code: "+countrySelectedCode, Toast.LENGTH_SHORT).show();
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
        llMercadolibreBtns = (LinearLayout) view.findViewById(R.id.ll_mercadolibre_btns);

        getListButton = (Button) view.findViewById(R.id.btn_get_list);
        getListButton.setOnClickListener(this);

        btnLogin = (Button) view.findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(this);

        btnPost = (Button) view.findViewById(R.id.btn_post);
        btnPost.setOnClickListener(this);

        btnGetMercadolibre = (Button) view.findViewById(R.id.btn_get_items);
        btnGetMercadolibre.setOnClickListener(this);

        spinnerCountry = (Spinner) view.findViewById(R.id.spinner_countries);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(SellMyThing.getContext(),
                R.array.country_names, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCountry.setAdapter(adapter);


    }

    @Override
    public void onClick(View view) {
        RadioGroup rg = new RadioGroup(SellMyThing.getContext());
        List<ClassResult> classResultList = ClassResultManager.getInstance().getClassResultLocal();
        radioButtons = new RadioButton[classResultList.size()];
        switch (view.getId()) {
            case (R.id.btn_get_list):
                rg.setOrientation(RadioGroup.VERTICAL);
                rg.setId(R.id.rg_list_classes);
                RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                rg.setLayoutParams(lp);

                for (int i = 0; i < classResultList.size(); i++) {
                    ClassResult classResult = classResultList.get(i);
                    radioButtons[i] = new RadioButton(SellMyThing.getContext());
                    radioButtons[i].setText(classResult.getClase());
                    radioButtons[i].setTextColor(Color.BLACK);
                    rg.addView(radioButtons[i]);
                }
                listRelativeLayout.addView(rg);
                getListButton.setVisibility(View.INVISIBLE);
                // llMercadolibreBtns.setVisibility(View.VISIBLE);
                btnGetMercadolibre.setVisibility(View.VISIBLE);
                rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        // checkedId is the RadioButton selected
                        valueSelected = checkedId-1;
                    }
                });
                break;
            case (R.id.btn_login):
                /*Intent intent = new Intent(getActivity(), LoginScreen.class);
                startActivity(intent);*/
                break;
            case (R.id.btn_post):
                /*classSelected = classResultList.get(valueSelected).getClase();
                new GetAsycTask().execute(new Command() {
                    @Override
                    ApiResponse executeCommand() {
                        return Meli.post("/items", getJson(), Meli.getCurrentIdentity(SellMyThing.getContext()));
                    }
                });*/
                break;
            case (R.id.btn_get_items):
                APIInterface apiInterface = APIClient.getClientMercadolibre().create(APIInterface.class);
                String itemName = classResultList.get(valueSelected).getClase();
                Call<Results> call = apiInterface.getItems(countrySelectedCode, itemName);
                call.enqueue(new Callback<Results>() {
                    @Override
                    public void onResponse(Call<Results> call, Response<Results> response) {
                        Log.d("@@@ DEBUG", "onResponse: mercadolibre: "+response.body());
                    }

                    @Override
                    public void onFailure(Call<Results> call, Throwable t) {
                        Log.d("@@@ DEBUG", "onFailure: ERROR MERCADOLIBRE: "+t);
                    }
                });

                break;
        }
    }


    private String getUserID() {
        Identity identity = Meli.getCurrentIdentity(SellMyThing.getContext());
        if (identity == null) {
            return null;
        } else {
            return identity.getUserId();
        }
    }

    private class GetAsycTask extends AsyncTask<Command, Void, ApiResponse> {

        @Override
        protected void onPostExecute(ApiResponse apiResponse) {
            if (apiResponse == null) {
                Toast.makeText(SellMyThing.getContext(), "Authenticate first!!!", Toast.LENGTH_SHORT).show();
            } else {
                ResultDialogFragment fragment = ResultDialogFragment.newInstance(apiResponse);
                MainActivity main = (MainActivity) getActivity();
                fragment.show(main.getSupportFragmentManager(), "RESULT");
            }
        }

        @Override
        protected ApiResponse doInBackground(Command... params) {
            return params[0].executeCommand();
        }
    }


    private abstract class Command {
        abstract ApiResponse executeCommand();
    }

private String getJson(){
    final String ITEM_JSON = "{\n" +
            "  \"title\": \"" + classSelected + "\",\n" +
            "  \"category_id\":\"MLA3530\",\n" +
            "  \"price\":10,\n" +
            "  \"currency_id\":\"ARS\",\n" +
            "  \"available_quantity\":1,\n" +
            "  \"buying_mode\":\"buy_it_now\",\n" +
            "  \"listing_type_id\":\"bronze\",\n" +
            "  \"condition\":\"new\",\n" +
            "  \"description\": \"Lorem ipsum dolor sit amet, an est odio timeam quaerendum.\",\n" +
            "  \"video_id\": \"YOUTUBE_ID_HERE\",\n" +
            "  \"warranty\": \"12 months\",\n" +
            "  \"pictures\":[\n" +
            "    {\"source\":\"" + MyConstants.IMAGE_FILE_PATH + "\"},\n" +
            "  ]\n" +
            "}";
    return ITEM_JSON;
}
    private static final String PUT_JSON = "{\n" +
            "  \"status\":\"paused\"\n" +
            "}";



}