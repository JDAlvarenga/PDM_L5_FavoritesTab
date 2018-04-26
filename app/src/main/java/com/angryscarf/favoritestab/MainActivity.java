package com.angryscarf.favoritestab;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Item> items, favorites;

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadItems();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

    }



    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        private static ArrayList<Item> itms, favs;
        private static ItemsAdapter itmsAdapter, favsAdapter;

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber, ArrayList<Item> itm, ArrayList<Item> fav) {
            itms = itm;
            favs = fav;

            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);

            RecyclerView rv = rootView.findViewById(R.id.recycler);
            rv.setHasFixedSize(true);//Might need to change this if going to add to favorites

            LinearLayoutManager lManager = new LinearLayoutManager(this.getActivity());
            rv.setLayoutManager(lManager);

            //Switch/send different items to each tab fragment
            ItemsAdapter adapter;
            switch(getArguments().getInt(ARG_SECTION_NUMBER)) {
                case 1:
                    adapter = new ItemsAdapter(this.getContext().getApplicationContext(),itms, favs) {
                        @Override
                        public void toggleFavorite(View view, int pos) {
                            Item item = itms.get(pos);
                            if(favs.contains(item)) {
                                favs.remove(item);
                                favsAdapter.notifyItemRemoved(favs.indexOf(item));
                                favsAdapter.notifyItemRangeChanged(favs.indexOf(item), favs.size()+1);
                                setIsFavorite((ImageButton) view, false);
                            }
                            else {
                                favs.add(item);
                                favsAdapter.notifyItemInserted(favs.size());
                                //favsAdapter.notifyItemRangeChanged(0, favs.size());

                                setIsFavorite((ImageButton) view, true);

                            }

                        }

                        @Override
                        public void setIsFavorite(ImageButton iButton, boolean isFavorite) {
                            setFavorite(iButton, isFavorite);
                        }
                    };
                    this.itmsAdapter = adapter;
                    break;


                case 2:
                    adapter = new ItemsAdapter(this.getContext().getApplicationContext(),favs, favs) {
                        @Override
                        public void toggleFavorite(View view, int pos) {

                        }
                        @Override
                        public void setIsFavorite(ImageButton iButton, boolean isFavorite) {
                            setFavorite(iButton, isFavorite);
                        }
                    };
                    this.favsAdapter = adapter;
                    break;
                default:
                    adapter = null;
            }
            rv.setAdapter(adapter);

            //TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            //textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }

        //
        public void setFavorite(ImageButton ibutton, boolean isFavorite) {
            ibutton.setImageResource((isFavorite? R.color.colorPrimaryDark : R.color.colorAccent));
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1, items, favorites);
        }

        @Override
        public int getCount() {
            // Show 2 total pages.
            return 2;
        }
    }

    public void loadItems() {
        items = new ArrayList<>();
        favorites = new ArrayList<>();

        items.add(new Item("Sleep", "Sleeping on a rainy day"));
        items.add(new Item("Eat", "Eating your  favorite food"));
        items.add(new Item("Notebook", "Doing the useless physics notebook"));

        favorites.add(items.get(0));
        favorites.add(items.get(1));

    }
}
