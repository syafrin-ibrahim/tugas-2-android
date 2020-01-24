package com.example.submission2_movie.fragment;


import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;


import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.submission2_movie.Detail;
import com.example.submission2_movie.R;
import com.example.submission2_movie.adapter.MovieAdapter;
import com.example.submission2_movie.model.Movie;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class F_Movie extends Fragment{
    private MenuItem mSearchItem;
    private SearchView sv;
    private RecyclerView rvMovies;
    private String mSearchString;
    private MovieAdapter filter;
    private ArrayList<Movie> list = new ArrayList<>();
    private static final String SEARCH_KEY = "search";



    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_movie, container, false);
        setHasOptionsMenu(true);
        if (savedInstanceState != null) {
            mSearchString = savedInstanceState.getString(SEARCH_KEY);
        }
        // bagian recycleview
        rvMovies = (RecyclerView)view.findViewById(R.id.rv_movie);
        rvMovies.setHasFixedSize(true);
        list.addAll(getListMovies());
        showRecyclerList();

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mSearchString = sv.getQuery().toString();
        outState.putString(SEARCH_KEY, mSearchString);
    }

    private ArrayList<Movie> getListMovies(){
        String[] dataTitle = getResources().getStringArray(R.array.data_name1);
        String[] dataDescription = getResources().getStringArray(R.array.data_description1);
        TypedArray dataPhoto = getResources().obtainTypedArray(R.array.data_photo1);

        ArrayList<Movie> MyMovies = new ArrayList<>();

        for(int i = 0; i < dataTitle.length; i++){
            Movie mv = new Movie();
            mv.setJudul(dataTitle[i]);
            mv.setDesc(dataDescription[i]);
            mv.setPhoto(dataPhoto.getResourceId(i, -1));

            MyMovies.add(mv);
        }
        return  MyMovies;
    }

    private void showRecyclerList(){
        rvMovies.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvMovies.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        MovieAdapter mvp = new MovieAdapter(list);
        rvMovies.setAdapter(mvp);
        mvp.setOnItemClickCallBack(new MovieAdapter.onItemClickCallBack() {
            @Override
            public void onItemClicked(Movie data) {
                Intent i = new Intent(getActivity(), Detail.class);
                i.putExtra(Detail.EXTRA_MODEL, data);
                startActivity(i);
            }
        });

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.main_menu, menu);
        mSearchItem = menu.findItem(R.id.search);
        sv  = (SearchView) MenuItemCompat.getActionView(mSearchItem);
        sv.setMaxWidth(Integer.MAX_VALUE);  sv.setIconified(false);

        sv.setQueryHint(getResources().getString(R.string.search));

        if (mSearchString != null && !mSearchString.isEmpty()) {
            mSearchItem.expandActionView();
            sv.setQuery(mSearchString, true);
          //  sv.clearFocus();
            sv.setFocusable(true);
            filter =  new MovieAdapter(list);
            String textinput = mSearchString.toLowerCase();
            ArrayList<Movie> getMoviews = new ArrayList<>();
            for(Movie m : list){
                String Judul = m.getJudul().toLowerCase();
                if(Judul.startsWith(textinput)){
                    getMoviews.add(m);
                }
            }
            filter.filterMovie(getMoviews);
            rvMovies.setAdapter(filter);
        }
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String nextText) {

                filter =  new MovieAdapter(list);
                String textinput = nextText.toLowerCase();
                ArrayList<Movie> getMoviews = new ArrayList<>();
                for(Movie m : list){
                    String Judul = m.getJudul().toLowerCase();
                    if(Judul.startsWith(textinput)){
                        getMoviews.add(m);
                    }
                }
                filter.filterMovie(getMoviews);
                rvMovies.setAdapter(filter);
                filter.setOnItemClickCallBack(new MovieAdapter.onItemClickCallBack() {
                    @Override
                    public void onItemClicked(Movie data) {
                        Intent i = new Intent(getActivity(), Detail.class);
                        i.putExtra(Detail.EXTRA_MODEL, data);
                        startActivity(i);
                    }
                });
                return true;
            }
        });
        mSearchItem.setActionView(sv);
       super.onCreateOptionsMenu(menu, inflater);

    }
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_change_settings) {
            Intent mIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(mIntent);
        }
        return true;
    }



}
