package com.example.covid19app.ui.home;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covid19app.NewsModel.Article;
import com.example.covid19app.R;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Locale;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>
{
    List<Article> articles;
    Context context;
    public Adapter(Context context, List<Article> articles)
    {
        this.articles = articles;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        final Article art = articles.get(position);
        String url = art.getUrl();
        holder.newsTitles.setText(art.getTitle());
        String imageUrl = art.getUrlToImage();
            Picasso.get().load(imageUrl).into(holder.imageView);


    }

    @Override
    public int getItemCount()
    {
        return articles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView newsTitles,newsDate;
        ImageView imageView;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            newsTitles = itemView.findViewById(R.id.newsTitle);
            imageView = itemView.findViewById(R.id.imageNews);
            cardView = itemView.findViewById(R.id.cardview);
        }
    }

    public String getCountry()
    {
        Locale locale = Locale.getDefault();
        String country  = locale.getCountry();
        return country.toLowerCase();
    }
}
