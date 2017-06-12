package co.com.romero.sellmything.sellmything.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import co.com.romero.sellmything.sellmything.R;
import co.com.romero.sellmything.sellmything.activities.SellMyThing;
import co.com.romero.sellmything.sellmything.adapters.RecycleAdapter;
import co.com.romero.sellmything.sellmything.utilities.persistence.manager.ItemResultsManager;
import co.com.romero.sellmything.sellmything.utilities.pojos.mercadolibre.ItemResults;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListMercadolibreItemsFragment extends BaseFragment {

    private RecyclerView rvItems;
    private LinearLayoutManager linearLayoutManager;
    private RecycleAdapter mAdapter;
    private ArrayList<ItemResults> itemResults;

    public ListMercadolibreItemsFragment() {
        super();
        // Required empty public constructor
    }

    public static ListMercadolibreItemsFragment newInstance() {
        ListMercadolibreItemsFragment fragment = new ListMercadolibreItemsFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list_mercadolibre_items, container, false);
        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvItems = (RecyclerView) view.findViewById(R.id.rv_items);
        linearLayoutManager = new LinearLayoutManager(SellMyThing.getContext());
        rvItems.setLayoutManager(linearLayoutManager);

        // Crear la fome esa de lista weona po qlia

        itemResults.addAll(ItemResultsManager.getInstance().getItemResultsLocal());
        mAdapter = new RecycleAdapter(itemResults);
        rvItems.setAdapter(mAdapter);


    }

    private int getLastVisibleItemPosition() {
        return linearLayoutManager.findLastVisibleItemPosition();
    }



}
