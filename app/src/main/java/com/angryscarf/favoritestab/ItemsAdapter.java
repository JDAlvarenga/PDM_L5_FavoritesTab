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

public abstract class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemsViewHolder> {
    private Context appcontext;
    private ArrayList<Item> items;
    private ArrayList<Item> favItems;



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


    @Override
    public void onBindViewHolder(ItemsViewHolder holder, final int position) {

        Item it = items.get(position);
        holder.title.setText(it.getName());
        holder.subtitle.setText(it.getDescription());

        setIsFavorite(holder.favorite, favItems.contains(it));

        holder.favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleFavorite(view, position);

            }
        });


    }

    /**
     * Called when the favorite {@link ImageButton} on the list is clicked.
     * @param view The reference to the clicked ImageButton
     * @param pos The position (index) of the clicked item.
    */
    public abstract void toggleFavorite(View view, int pos);

    /**
     * Change the layout of the button or other visual changes if the item is marked as favorite
     * This method should be called in the implementation of toggleFavorite on {@link com.angryscarf.favoritestab.ItemsAdapter, this adapter}
     * @param iButton Reference to the button
     * @param isFavorite Tells if is marked as favorite
     */
    public abstract void setIsFavorite(ImageButton iButton, boolean isFavorite);

    @Override
    public int getItemCount() {
        return items.size();
    }


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
}
