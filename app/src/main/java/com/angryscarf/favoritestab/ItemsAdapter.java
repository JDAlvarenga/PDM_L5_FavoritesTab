package com.angryscarf.favoritestab;

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

    public ItemsAdapter(ArrayList<Item> items, ArrayList<Item> favItems) {
        this.items = items;
        this.favItems = favItems;
    }

    @Override
    public ItemsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_cardview, parent, false);
        return (new ItemsViewHolder(v));
    }

    //TODO: Add listener to image button to addd/remove from favorites (abstract)
    @Override
    public void onBindViewHolder(ItemsViewHolder holder, int position) {
        Item it = items.get(position);
        holder.title.setText(it.getName());
        holder.subtitle.setText(it.getDescription());
        holder.favorite.setImageResource(
                favItems.contains(it)? R.drawable.ic_launcher_foreground : R.drawable.ic_launcher_background
        );

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
