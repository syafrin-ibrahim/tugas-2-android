package com.example.submission2_movie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.ImageView;

import android.widget.TextView;

import com.example.submission2_movie.model.Tvshow;

public class Detail_t extends AppCompatActivity {
    public static final String EXTRA_MODEL = "DATA";

    private TextView jdl, desc;
    private ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_t);

        Toolbar tbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getResources().getString(R.string.title));
        getSupportActionBar().setElevation(0);

        Tvshow mytvshow = getIntent().getParcelableExtra(EXTRA_MODEL);
        jdl = findViewById(R.id.text_title);
        desc = findViewById(R.id.text_description);
        img = findViewById(R.id.image);

        jdl.setText(mytvshow.getJudul());
        desc.setText(mytvshow.getDesc());
        img.setImageResource(mytvshow.getPhoto());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.side_menu, menu);


        return super.onCreateOptionsMenu(menu);
    }
   @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_change_settings) {
            Intent mIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(mIntent);
        }
       return super.onOptionsItemSelected(item);
    }

}
