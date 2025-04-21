package com.example.newsapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.NewsInterface;
import com.example.newsapp.R;
import com.example.newsapp.adapters.NewsAdapter;
import com.example.newsapp.adapters.TopStoriesAdapter;
import com.example.newsapp.models.News;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView rvTopStories;
    private RecyclerView rvNews;
    private TopStoriesAdapter topStoriesAdapter;
    private NewsAdapter newsAdapter;
    private NewsInterface newsInterface;
    private LinearLayoutManager topStoriesLayout;
    private List<News> topStoriesList = new ArrayList<>();
    private int currentPosition = 0;
    private Handler handler = new Handler();
    private static final long SCROLL_DELAY = 3000; // 3 seconds

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
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize RecyclerViews
        rvTopStories = view.findViewById(R.id.rv_top_stories);
        rvNews = view.findViewById(R.id.rv_news);

        // Top Stories RecyclerView setup
        topStoriesLayout = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rvTopStories.setLayoutManager(topStoriesLayout);

        // Change to GridLayoutManager for news (2 columns) to match wireframe
        GridLayoutManager newsLayout = new GridLayoutManager(getContext(), 2);
        rvNews.setLayoutManager(newsLayout);

        // Set fixed heights for better visibility
        rvTopStories.setMinimumHeight(getResources().getDimensionPixelSize(R.dimen.top_stories_height));
        rvNews.setMinimumHeight(getResources().getDimensionPixelSize(R.dimen.news_height));

        rvTopStories.setItemViewCacheSize(4);
        rvNews.setItemViewCacheSize(4);
        rvTopStories.setDrawingCacheEnabled(true);
        rvNews.setDrawingCacheEnabled(true);
        rvTopStories.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        rvNews.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);

        // Load sample data
        loadSampleData();

        // Start auto-scrolling
        startAutoScroll();
    }

    private void loadSampleData() {
        // Sample data for top stories with fixed URLs that will definitely work
        topStoriesList.add(new News("1", "Global Climate Summit Reaches Agreement",
                "World leaders have reached a landmark agreement on climate change during the global summit...",
                "https://images.unsplash.com/photo-1542601906990-b4d3fb778b09", "World", "April 10, 2025", true));
        topStoriesList.add(new News("2", "New AI Breakthrough in Medical Research",
                "Scientists announced a major breakthrough in AI-assisted medical research that could revolutionize disease diagnosis...",
                "https://images.unsplash.com/photo-1550751827-4bd374c3f58b", "Technology", "April 9, 2025", true));
        topStoriesList.add(new News("3", "Olympic Committee Announces Host City for 2036",
                "The International Olympic Committee has announced the host city for the 2036 Summer Olympic Games...",
                "https://images.unsplash.com/photo-1612872087720-bb876e2e67d1", "Sports", "April 8, 2025", true));
        topStoriesList.add(new News("4", "Major Economic Reform Package Unveiled",
                "The government has unveiled a comprehensive economic reform package aimed at boosting growth and reducing inequality...",
                "https://images.unsplash.com/photo-1526304640581-d334cdbbf45e", "Business", "April 7, 2025", true));

        // Sample data for regular news
        List<News> newsList = new ArrayList<>();
// Technology
        newsList.add(new News("1", "Apple Unveils Foldable iPhone with Holographic Display",
                "The tech giant surprises the world with a revolutionary foldable design and holographic projection capabilities",
                "https://images.unsplash.com/photo-1601784551446-20c9e07cdbdb", "Technology", "April 15, 2025", true));

        newsList.add(new News("2", "Google Announces AI-Powered Search Engine Overhaul",
                "The new AI model understands complex queries and provides summarized answers instead of links",
                "https://images.unsplash.com/photo-1633356122544-f134324a6cee", "Technology", "April 14, 2025", false));

// Business
        newsList.add(new News("3", "Tesla Stock Soars After Battery Breakthrough",
                "New solid-state battery technology promises 500-mile range with 5-minute charging",
                "https://images.unsplash.com/photo-1551836022-d5d88e9218df", "Business", "April 13, 2025", false));

// Health
        newsList.add(new News("4", "FDA Approves First Alzheimer's Treatment That Slows Disease",
                "The groundbreaking drug shows 35% reduction in cognitive decline during clinical trials",
                "https://images.unsplash.com/photo-1576091160550-2173dba999ef", "Health", "April 12, 2025", true));

// Sports
        newsList.add(new News("5", "2026 World Cup Stadium Construction Reaches Final Stages",
                "All 16 host cities complete their stadiums six months ahead of schedule",
                "https://images.unsplash.com/photo-1540747913346-19e32dc3e97e", "Sports", "April 11, 2025", false));

// Entertainment
        newsList.add(new News("6", "Streaming Wars Intensify as Disney+ Surpasses Netflix Subscribers",
                "The mouse house reaches 250 million subscribers with exclusive Marvel and Star Wars content",
                "https://images.unsplash.com/photo-1489599849927-2ee91cede3ba", "Entertainment", "April 10, 2025", false));

// Politics
        newsList.add(new News("7", "Global Climate Agreement Reached at Emergency Summit",
                "196 countries commit to carbon neutrality by 2040 after record heat waves",
                "https://images.unsplash.com/photo-1508784411316-02b8cd4d3a3a", "Politics", "April 9, 2025", true));

// Science
        newsList.add(new News("8", "NASA Confirms Evidence of Microbial Life on Mars",
                "Perseverance rover discovers organic compounds in Jezero Crater samples",
                "https://images.unsplash.com/photo-1454789548928-9efd52dc4031", "Science", "April 8, 2025", false));
// Culture
        newsList.add(new News("10", "Van Gogh Painting Sells for $250 Million at Private Auction",
                "The previously unknown work was discovered in a French attic last year",
                "https://images.unsplash.com/photo-1579783902614-a3fb3927b6a5", "Culture", "April 6, 2025", true));

// Education
        newsList.add(new News("11", "Harvard Announces Free Online Degrees for Developing Nations",
                "The Ivy League school will offer 20 programs through its digital platform",
                "https://images.unsplash.com/photo-1523050854058-8df90110c9f1", "Education", "April 5, 2025", false));

// Travel
        newsList.add(new News("12", "Japan Reopens Borders with Digital Nomad Visa Program",
                "The new visa allows remote workers to stay for up to 3 years",
                "https://images.unsplash.com/photo-1492571350019-22de08371fd3", "Travel", "April 4, 2025", false));

// Food
        newsList.add(new News("13", "Lab-Grown Meat Approved for Sale in US Supermarkets",
                "The FDA clears cultured chicken products with identical taste to conventional meat",
                "https://images.unsplash.com/photo-1546069901-ba9599a7e63c", "Food", "April 3, 2025", false));

// Fashion
        newsList.add(new News("14", "Sustainable Fashion Week Draws Record Attendance in Paris",
                "Designers showcase collections made entirely from recycled materials",
                "https://images.unsplash.com/photo-1483985988355-763728e1935b", "Fashion", "April 2, 2025", true));

// Automotive
        newsList.add(new News("15", "Flying Car Completes First Inter-City Flight",
                "The 200-mile journey between Dallas and Houston marks a transportation milestone",
                "https://images.unsplash.com/photo-1503376780353-7e6692767b70", "Automotive", "April 1, 2025", false));
        // Set adapters
        topStoriesAdapter = new TopStoriesAdapter(getContext(), newsList, newsInterface);
        rvTopStories.setAdapter(topStoriesAdapter);

        newsAdapter = new NewsAdapter(getContext(), newsList, newsInterface);
        rvNews.setAdapter(newsAdapter);
    }

    private void startAutoScroll() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (currentPosition < topStoriesAdapter.getItemCount() - 1) {
                    currentPosition++;
                } else {
                    currentPosition = 0;
                }
                rvTopStories.smoothScrollToPosition(currentPosition);
                handler.postDelayed(this, SCROLL_DELAY);
            }
        }, SCROLL_DELAY);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        stopAutoScroll();
    }

    private void stopAutoScroll() {
        handler.removeCallbacksAndMessages(null);
    }
}
