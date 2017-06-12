package co.com.romero.sellmything.sellmything.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import co.com.romero.sellmything.sellmything.R;
import co.com.romero.sellmything.sellmything.activities.SellMyThing;
import co.com.romero.sellmything.sellmything.utilities.pojos.mercadolibre.ItemResults;

/**
 * Created by Romero-Pc on 12/06/2017.
 */

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ItemHolder>{

    private ArrayList<ItemResults> mItemsML;

    public RecycleAdapter(ArrayList<ItemResults> items) {
        mItemsML = items;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(SellMyThing.getContext())
                .inflate(R.layout.item_mercadolibre, parent, false);
        return new ItemHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        ItemResults itemResults = mItemsML.get(position);
        holder.bindItem(itemResults);
    }

    @Override
    public int getItemCount() {
        return mItemsML.size();
    }



    public static class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView mItemImage;
        private TextView mItemTitle;
        private TextView mItemPrice;

        public ItemHolder(View v) {
            super(v);

            mItemImage = (ImageView) v.findViewById(R.id.item_image);
            mItemTitle = (TextView) v.findViewById(R.id.item_title);
            mItemPrice = (TextView) v.findViewById(R.id.item_price);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(SellMyThing.getContext(), "Working list", Toast.LENGTH_SHORT).show();
        }

        public void bindItem(ItemResults item) {
            Picasso.with(mItemImage.getContext()).load(item.getThumbnail()).into(mItemImage);
            mItemTitle.setText(item.getTitle());
            mItemPrice.setText(item.getPrice());
        }
    }


}
