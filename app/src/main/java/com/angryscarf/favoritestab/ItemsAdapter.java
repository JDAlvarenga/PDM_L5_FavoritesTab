package com.angryscarf.favoritestab;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Jaime on 4/25/2018.
 */

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemsViewHolder> {
    private Context appcontext;
    private ArrayList<Item> items;
    private ArrayList<Item> favItems;

    public static class ItemsViewHolder extends RecyclerView.ViewHolder {
        CardView card;
        TextView title;
        TextView subtitle;
        ImageButton favorite;

        public ItemsViewHolder(View itemView) {
            super(itemView);
            //itemView is the fragment_cardview.xml layout file
            card = itemView.findViewById(R.id.card_view);
            title = itemView.findViewById(R.id.txt_title);
            subtitle = itemView.findViewById(R.id.txt_subtitle);
            favorite = itemView.findViewById(R.id.iBtn_favorite);


        }
    }

    public ItemsAdapter(Context appcontext, ArrayList<Item> items, ArrayList<Item> favItems) {
        this.appcontext = appcontext;
        this.items = items;
        this.favItems = favItems;
    }

    @Override
    public ItemsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_cardview, parent, false);
        return (new ItemsViewHolder(v));
    }

    //TODO: Add listener to image button to add/remove from favorites (abstract)
    @Override
    public void onBindViewHolder(ItemsViewHolder holder, int position) {
        Item it = items.get(position);
        holder.title.setText(it.getName());
        holder.subtitle.setText(it.getDescription());

        holder.favorite.setImageResource(

            (favItems.contains(it)? R.color.colorPrimaryDark : R.color.colorAccent)
        );



    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
