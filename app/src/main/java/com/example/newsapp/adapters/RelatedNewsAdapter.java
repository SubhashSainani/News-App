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

public class RelatedNewsAdapter extends RecyclerView.Adapter<RelatedNewsAdapter.RelatedNewsViewHolder> {

    private List<News> relatedNews;
    private Context context;
    private NewsInterface newsInterface;

    public RelatedNewsAdapter(Context context, List<News> relatedNews, NewsInterface newsInterface) {
        this.context = context;
        this.relatedNews = relatedNews;
        this.newsInterface = newsInterface;
    }

    @NonNull
    @Override
    public RelatedNewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_related_news, parent, false);
        return new RelatedNewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RelatedNewsViewHolder holder, int position) {
        News news = relatedNews.get(position);

        holder.tvRelatedNewsTitle.setText(news.getTitle());
        holder.tvRelatedNewsDate.setText(news.getDate());

        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.placeholder_image)
                .error(R.drawable.error_image)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop();

        Glide.with(context)
                .load(news.getImageUrl())
                .apply(requestOptions)
                .into(holder.ivRelatedNews);

        holder.cardRelatedNews.setOnClickListener(new View.OnClickListener() {
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
        return relatedNews.size();
    }

    static class RelatedNewsViewHolder extends RecyclerView.ViewHolder {
        ImageView ivRelatedNews;
        TextView tvRelatedNewsTitle;
        TextView tvRelatedNewsDate;
        CardView cardRelatedNews;

        public RelatedNewsViewHolder(@NonNull View itemView) {
            super(itemView);
            ivRelatedNews = itemView.findViewById(R.id.iv_related_news);
            tvRelatedNewsTitle = itemView.findViewById(R.id.tv_related_news_title);
            tvRelatedNewsDate = itemView.findViewById(R.id.tv_related_news_date);
            cardRelatedNews = itemView.findViewById(R.id.card_related_news);
        }
    }
}
