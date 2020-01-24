package com.example.submission2_movie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;


import android.os.Bundle;
import android.view.MenuItem;


import com.example.submission2_movie.adapter.SectionsPagerAdapter;

import com.google.android.material.tabs.TabLayout;




public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // set tablayout
        SectionsPagerAdapter sp = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager vp = findViewById(R.id.view_pager);
        vp.setAdapter(sp);
        TabLayout tb = findViewById(R.id.tabs);
        tb.setupWithViewPager(vp);


        Toolbar tbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(tbar);
         getSupportActionBar().setTitle(getResources().getString(R.string.title));
        getSupportActionBar().setElevation(0);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
