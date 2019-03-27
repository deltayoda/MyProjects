package in.androidtest.bhavesh.newsapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.borjabravo.readmoretextview.ReadMoreTextView;
import com.bumptech.glide.Glide;

import java.util.List;

import in.androidtest.bhavesh.newsapp.R;
import in.androidtest.bhavesh.newsapp.models.NewsApi.Article;
import in.androidtest.bhavesh.newsapp.ui.detail.WebViewDetailActivity;

public class NewsApapter  extends RecyclerView.Adapter<NewsApapter.NewsViewHolder> {

    List<Article> articleList;
    Context context;

    public NewsApapter(List<Article> articleList, Context context) {
        this.articleList = articleList;
        this.context = context;
    }
    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.row_news,parent,false);
        return new NewsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, final int position) {
        holder.tvTitle.setText(articleList.get(position).getTitle());
        holder.tvOverview.setText(articleList.get(position).getDescription());
        Glide.with(context).load(articleList.get(position).getUrlToImage()).into(holder.ivNews);
        holder.clNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, WebViewDetailActivity.class);
                intent.putExtra("news_url",articleList.get(position).getUrl());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout clNews;
        TextView tvTitle;
        ReadMoreTextView tvOverview;
        ImageView ivNews;
        public NewsViewHolder(View itemView) {
            super(itemView);
            clNews = (ConstraintLayout) itemView.findViewById(R.id.clNews);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvOverview = (ReadMoreTextView) itemView.findViewById(R.id.tvOverView);
            ivNews = (ImageView) itemView.findViewById(R.id.ivNews);
        }
    }
}
