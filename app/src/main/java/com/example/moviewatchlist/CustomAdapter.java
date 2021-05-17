package com.example.moviewatchlist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private ArrayList movie_id, movie_name, movie_director, movie_category;
    private Activity activity;

    int position;

    CustomAdapter(Activity activity, Context context, ArrayList movie_id, ArrayList movie_name, ArrayList movie_director,
                  ArrayList movie_category){
        this.activity = activity;
        this.context = context;
        this.movie_id = movie_id;
        this.movie_name = movie_name;
        this.movie_director = movie_director;
        this.movie_category = movie_category;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(context);
       View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        this.position = position;
        holder.movie_id_txt.setText(String.valueOf(movie_id.get(position)));
        holder.movie_name_txt.setText(String.valueOf(movie_name.get(position)));
        holder.movie_director_txt.setText(String.valueOf(movie_director.get(position)));
        holder.movie_category_txt.setText(String.valueOf(movie_category.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(movie_id.get(position)));
                intent.putExtra("name", String.valueOf(movie_name.get(position)));
                intent.putExtra("director", String.valueOf(movie_director.get(position)));
                intent.putExtra("category", String.valueOf(movie_category.get(position)));

                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movie_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView movie_id_txt, movie_name_txt, movie_director_txt, movie_category_txt;
        LinearLayout mainLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            movie_id_txt = itemView.findViewById(R.id.movie_id_txt);
            movie_name_txt = itemView.findViewById(R.id.movie_name_txt);
            movie_director_txt = itemView.findViewById(R.id.movie_director_txt);
            movie_category_txt = itemView.findViewById(R.id.movie_category_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
