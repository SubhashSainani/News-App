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

public class TopStoriesAdapter extends RecyclerView.Adapter<TopStoriesAdapter.TopStoryViewHolder> {

    private List<News> topStories;
    private Context context;
    private NewsInterface newsInterface;

    public TopStoriesAdapter(Context context, List<News> topStories, NewsInterface newsInterface) {
        this.context = context;
        this.topStories = topStories;
        this.newsInterface = newsInterface;
    }

    @NonNull
    @Override
    public TopStoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_top_story, parent, false);
        return new TopStoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopStoryViewHolder holder, int position) {
        News news = topStories.get(position);

        holder.tvTopStoryTitle.setText(news.getTitle());
        holder.tvTopStoryCategory.setText(news.getCategory());

        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.placeholder_image)
                .error(R.drawable.error_image)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop();

        Glide.with(context)
                .load(news.getImageUrl())
                .apply(requestOptions)
                .into(holder.ivTopStory);

        holder.cardTopStory.setOnClickListener(new View.OnClickListener() {
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
        return topStories.size();
    }

    static class TopStoryViewHolder extends RecyclerView.ViewHolder {
        ImageView ivTopStory;
        TextView tvTopStoryTitle;
        TextView tvTopStoryCategory;
        CardView cardTopStory;

        public TopStoryViewHolder(@NonNull View itemView) {
            super(itemView);
            ivTopStory = itemView.findViewById(R.id.iv_top_story);
            tvTopStoryTitle = itemView.findViewById(R.id.tv_top_story_title);
            tvTopStoryCategory = itemView.findViewById(R.id.tv_top_story_category);
            cardTopStory = itemView.findViewById(R.id.card_top_story);
        }
    }
}
