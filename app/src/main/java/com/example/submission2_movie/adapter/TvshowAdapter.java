package com.example.submission2_movie.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.submission2_movie.R;
import com.example.submission2_movie.model.Tvshow;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TvshowAdapter extends RecyclerView.Adapter<TvshowAdapter.TvshowViewHolder> {
   // private Context ctx;
    private ArrayList<Tvshow> tvshows;
    private onItemClickCallBack ocb;

    public TvshowAdapter(ArrayList<Tvshow> list_tvshow) {
        this.tvshows = list_tvshow;
    }


    @NonNull
    @Override
    public TvshowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new TvshowViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final TvshowViewHolder holder, int position) {
        Tvshow tsw  = tvshows.get(position);
        holder.txtJudul.setText(tsw.getJudul());
        holder.txtSinopsis.setText(tsw.getDesc());
        holder.imgPhoto.setImageResource(tsw.getPhoto());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ocb.onItemClicked(tvshows.get(holder.getAdapterPosition()));
            }
        });
    }


    @Override
    public int getItemCount() {
        return tvshows.size();
    }

    public class TvshowViewHolder extends RecyclerView.ViewHolder{
        private TextView txtJudul, txtSinopsis;
        private ImageView imgPhoto;
        public TvshowViewHolder(@NonNull View itemView) {
            super(itemView);
            txtJudul = itemView.findViewById(R.id.item_title);
            txtSinopsis = itemView.findViewById(R.id.item_description);
            imgPhoto = itemView.findViewById(R.id.img_item_photo);
        }
    }

    public void setOnItemClickCallBack(onItemClickCallBack onitemclickcallback){
        this.ocb = onitemclickcallback;
    }
    public interface onItemClickCallBack{
        void onItemClicked(Tvshow data);
    }

    public void filterTvShow(ArrayList<Tvshow> newtvshow){
        tvshows = new ArrayList<>();
        tvshows.addAll(newtvshow);
        notifyDataSetChanged();
    }
}
