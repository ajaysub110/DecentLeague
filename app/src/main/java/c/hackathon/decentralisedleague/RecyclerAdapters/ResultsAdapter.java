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
import c.hackathon.decentralisedleague.RecyclerModels.ResultsModel;

public class ResultsAdapter extends RecyclerView.Adapter<ResultsAdapter.MyViewHolder> {

    private List<ResultsModel> resultsList;
    private View itemView;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title,desc;

        public MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.teamEmailAddress);
            desc = view.findViewById(R.id.teamScore);

        }
    }

    public ResultsAdapter(List<ResultsModel> resultsList) {
        this.resultsList = resultsList;
    }



    @NonNull
    @Override
    public ResultsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.results_detail_card, parent, false);
        return new ResultsAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultsAdapter.MyViewHolder holder, int position) {
        ResultsModel result = resultsList.get(position);
        holder.title.setText(result.getTitle());
        holder.desc.setText(result.getDesc());
    }

    @Override
    public int getItemCount() {
        return resultsList.size();
    }




}
