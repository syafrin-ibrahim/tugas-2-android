package com.example.submission2_movie.fragment;


import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;


import androidx.annotation.NonNull;
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


import com.example.submission2_movie.Detail_t;
import com.example.submission2_movie.R;
import com.example.submission2_movie.adapter.TvshowAdapter;

import com.example.submission2_movie.model.Tvshow;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class F_Tvshow extends Fragment {
    private MenuItem mSearchItem;
    private SearchView sv;
    private RecyclerView rvTvshow;
    private TvshowAdapter filter;
    private ArrayList<Tvshow> list = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_tvshow, container, false);
        setHasOptionsMenu(true);
        rvTvshow = (RecyclerView)view.findViewById(R.id.rv_tvshow);
        rvTvshow.setHasFixedSize(true);
        list.addAll(getListMovies());
        showRecyclerList();
        return view;
    }

    private ArrayList<Tvshow> getListMovies(){
        String[] dataTitle = getResources().getStringArray(R.array.data_name2);
        String[] dataDescription = getResources().getStringArray(R.array.data_description2);
        TypedArray dataPhoto = getResources().obtainTypedArray(R.array.data_photo2);

        ArrayList<Tvshow> MyTvshows = new ArrayList<>();

        for(int i = 0; i < dataTitle.length; i++){
            Tvshow ts = new Tvshow();
            ts.setJudul(dataTitle[i]);
            ts.setDesc(dataDescription[i]);
            ts.setPhoto(dataPhoto.getResourceId(i,0));
            MyTvshows.add(ts);
        }
        return MyTvshows;
    }

    private void showRecyclerList() {
        rvTvshow.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvTvshow.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        TvshowAdapter tsp = new TvshowAdapter(list);
        rvTvshow.setAdapter(tsp);
        tsp.setOnItemClickCallBack(new TvshowAdapter.onItemClickCallBack() {
            @Override
            public void onItemClicked(Tvshow data) {
                Intent i = new Intent(getActivity(), Detail_t.class);
                i.putExtra(Detail_t.EXTRA_MODEL, data);
                startActivity(i);
            }
        });
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu, menu);
        mSearchItem = menu.findItem(R.id.search);
        sv = new SearchView(getActivity());
        sv.setQueryHint("Cari Judul Tv Show");
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
               filter = new TvshowAdapter(list);
                String textinput = newText.toLowerCase();
                ArrayList<Tvshow> getTvShows = new ArrayList<>();
                for(Tvshow t : list){
                    String judul = t.getJudul().toLowerCase();
                    if(judul.startsWith(textinput)){
                        getTvShows.add(t);
                    }
                }
                filter.filterTvShow(getTvShows);
                rvTvshow.setAdapter(filter);
                filter.setOnItemClickCallBack(new TvshowAdapter.onItemClickCallBack() {
                    @Override
                    public void onItemClicked(Tvshow data) {
                        Intent i = new Intent(getActivity(), Detail_t.class);
                        i.putExtra(Detail_t.EXTRA_MODEL, data);
                        startActivity(i);
                    }
                });
                return true;
            }
        });
        mSearchItem.setActionView(sv);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_change_settings) {
            Intent mIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(mIntent);
        }

        return true;
    }
}
