package com.example.newsapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.newsapp.NewsInterface;
import com.example.newsapp.R;
import com.example.newsapp.adapters.RelatedNewsAdapter;
import com.example.newsapp.models.News;

import java.util.ArrayList;
import java.util.List;

public class NewsDetailFragment extends Fragment {

    private static final String ARG_NEWS = "arg_news";

    private News news;
    private ImageView ivNewsDetail;
    private TextView tvNewsDetailTitle;
    private TextView tvNewsDetailDate;
    private TextView tvNewsDetailCategory;
    private TextView tvNewsDetailDescription;
    private RecyclerView rvRelatedNews;
    private RelatedNewsAdapter relatedNewsAdapter;
    private NewsInterface newsInterface;
    private Toolbar toolbar;

    public static NewsDetailFragment newInstance(News news) {
        NewsDetailFragment fragment = new NewsDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_NEWS, news);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            news = (News) getArguments().getSerializable(ARG_NEWS);
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof NewsInterface) {
            newsInterface = (NewsInterface) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement NewsInterface");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ivNewsDetail = view.findViewById(R.id.iv_news_detail);
        tvNewsDetailTitle = view.findViewById(R.id.tv_news_detail_title);
        tvNewsDetailDate = view.findViewById(R.id.tv_news_detail_date);
        tvNewsDetailCategory = view.findViewById(R.id.tv_news_detail_category);
        tvNewsDetailDescription = view.findViewById(R.id.tv_news_detail_description);
        rvRelatedNews = view.findViewById(R.id.rv_related_news);
        toolbar = view.findViewById(R.id.toolbar);

        // Set up RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvRelatedNews.setLayoutManager(layoutManager);

        if (news != null) {
            tvNewsDetailTitle.setText(news.getTitle());
            tvNewsDetailDate.setText(news.getDate());
            tvNewsDetailCategory.setText(news.getCategory());
            tvNewsDetailDescription.setText(news.getDescription());

            // Enhanced Glide configuration for more reliable image loading
            RequestOptions requestOptions = new RequestOptions()
                    .placeholder(R.drawable.placeholder_image)
                    .error(R.drawable.error_image)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop();

            Glide.with(this)
                    .load(news.getImageUrl())
                    .apply(requestOptions)
                    .into(ivNewsDetail);

            loadRelatedNews();

            toolbar.setTitle(news.getCategory());
            toolbar.setNavigationOnClickListener(v -> requireActivity().onBackPressed());
        }
    }

    private void loadRelatedNews() {
        // Sample data for related news with reliable image URLs
        List<News> relatedNewsList = new ArrayList<>();

        // Add sample related news based on the category of the current news
        if (news.getCategory().equals("Technology")) {
            relatedNewsList.add(new News("10", "Tech Companies Announce Joint Venture",
                    "Major tech companies are joining forces in a groundbreaking partnership...",
                    "https://images.unsplash.com/photo-1518770660439-4636190af475", "Technology", "April 10, 2025", false));
            relatedNewsList.add(new News("11", "New Wearable Technology Trends",
                    "The latest trends in wearable technology are changing how we interact with devices...",
                    "https://images.unsplash.com/photo-1510557880182-3d4d3cba35a5", "Technology", "April 9, 2025", false));
            relatedNewsList.add(new News("12", "5G Implementation Accelerates Worldwide",
                    "The global rollout of 5G networks is accelerating faster than expected...",
                    "https://images.unsplash.com/photo-1520869562399-e772f042f422", "Technology", "April 8, 2025", false));
        } else if (news.getCategory().equals("Business")) {
            relatedNewsList.add(new News("13", "Stock Markets Hit Record Highs",
                    "Global stock markets have reached unprecedented highs amid economic optimism...",
                    "https://images.unsplash.com/photo-1538356386415-6822dd2abf9e", "Business", "April 11, 2025", false));
            relatedNewsList.add(new News("14", "Major Merger Creates Industry Giant",
                    "Two leading companies have merged to create the largest entity in their industry...",
                    "https://images.unsplash.com/photo-1507679799987-c73779587ccf", "Business", "April 10, 2025", false));
            relatedNewsList.add(new News("15", "Cryptocurrency Regulations Announced",
                    "New regulations on cryptocurrency trading have been announced by financial authorities...",
                    "https://images.unsplash.com/photo-1516245834210-c4c142787335", "Business", "April 9, 2025", false));
        } else {
            // Default related news for other categories
            relatedNewsList.add(new News("16", "Scientists Discover New Species",
                    "A previously unknown species has been discovered in the remote rainforest...",
                    "https://images.unsplash.com/photo-1488841714725-bb4c32d1ac94", "Science", "April 11, 2025", false));
            relatedNewsList.add(new News("17", "Study Reveals Benefits of Meditation",
                    "A new long-term study confirms significant health benefits of regular meditation...",
                    "https://images.unsplash.com/photo-1506126613408-eca07ce68773", "Health", "April 10, 2025", false));
            relatedNewsList.add(new News("18", "Cultural Festival Draws Record Crowds",
                    "The annual international cultural festival has attracted record attendance this year...",
                    "https://images.unsplash.com/photo-1508921108053-9f757ead871c", "Culture", "April 9, 2025", false));
        }

        relatedNewsAdapter = new RelatedNewsAdapter(getContext(), relatedNewsList, newsInterface);
        rvRelatedNews.setAdapter(relatedNewsAdapter);
    }
}
