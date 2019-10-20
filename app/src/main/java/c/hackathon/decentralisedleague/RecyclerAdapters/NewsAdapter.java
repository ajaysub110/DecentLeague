package c.hackathon.decentralisedleague.RecyclerAdapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import c.hackathon.decentralisedleague.R;
import c.hackathon.decentralisedleague.RecyclerModels.NewsModel;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> {

    private List<NewsModel> newsList;
    private View itemView;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title,desc;

        public MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.titleUpcomingElection);
            desc = view.findViewById(R.id.descUpcomingElection);

        }
    }

    public NewsAdapter(List<NewsModel> newsList) {
        this.newsList = newsList;
    }

    @NonNull
    @Override
    public NewsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_card_layout, parent, false);
        return new NewsAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.MyViewHolder holder, int position) {
        NewsModel news = newsList.get(position);
        holder.title.setText(news.getTitle());
        holder.desc.setText(news.getDesc());
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }
}
