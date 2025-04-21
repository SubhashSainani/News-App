package com.example.newsapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.newsapp.R;
import com.example.newsapp.models.News;
import com.example.newsapp.NewsInterface;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private List<News> newsList;
    private Context context;
    private NewsInterface newsInterface;

    public NewsAdapter(Context context, List<News> newsList, NewsInterface newsInterface) {
        this.context = context;
        this.newsList = newsList;
        this.newsInterface = newsInterface;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        News news = newsList.get(position);

        holder.tvNewsTitle.setText(news.getTitle());
        holder.tvNewsDate.setText(news.getDate());
        holder.tvNewsCategory.setText(news.getCategory());

        // Enhanced Glide configuration for more reliable image loading
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.placeholder_image)
                .error(R.drawable.error_image)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop();

        Glide.with(context)
                .load(news.getImageUrl())
                .apply(requestOptions)
                .into(holder.ivNews);

        holder.cardNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (newsInterface != null) {
                    newsInterface.onNewsItemClicked(news);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    static class NewsViewHolder extends RecyclerView.ViewHolder {
        ImageView ivNews;
        TextView tvNewsTitle;
        TextView tvNewsCategory;
        TextView tvNewsDate;
        CardView cardNews;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            ivNews = itemView.findViewById(R.id.iv_news);
            tvNewsTitle = itemView.findViewById(R.id.tv_news_title);
            tvNewsCategory = itemView.findViewById(R.id.tv_news_category);
            tvNewsDate = itemView.findViewById(R.id.tv_news_date);
            cardNews = itemView.findViewById(R.id.card_news);
        }
    }
}