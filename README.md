# News App 📰

![App Screenshot](news_app_homepage.png)

A modern Android news application built with RecyclerViews and Fragments that displays top stories and latest news in a clean, responsive interface.

## Features ✨

- **Top Stories** horizontal carousel with auto-scrolling
- **Latest News** in a 2-column grid layout
- **News Detail** view with related stories
- **Category-based** organization
- **Smooth navigation** between screens
- **Optimized image loading** with Glide

## Screenshots 📸

| Home Screen | News Detail |
|-------------|-------------|
| ![Home](news_app_homepage.png) | ![Detail](news_app_news_info.png) |
| ![Home 2](news_app_homepage2.png) | ![Related](news_app_related news.png) |

## Technical Implementation ⚙️

### Architecture
- **Fragments** for screen management
- **RecyclerViews** for efficient lists
- **Model-View-Presenter** pattern
- **Interface-based** navigation

### Components
- `HomeFragment` - Main news feed
- `NewsDetailFragment` - Article details
- `NewsAdapter` - Regular news grid
- `TopStoriesAdapter` - Horizontal carousel
- `RelatedNewsAdapter` - Related stories list

### Libraries
- [Glide](https://github.com/bumptech/glide) - Image loading
- AndroidX - Modern Android components

## Setup 🛠️

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/news-app.git
