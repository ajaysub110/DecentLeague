package c.hackathon.decentralisedleague.RecyclerAdapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import c.hackathon.decentralisedleague.Fragments.BuyPlayerFragment;
import c.hackathon.decentralisedleague.Fragments.SellPlayerFragment;
import c.hackathon.decentralisedleague.R;
import c.hackathon.decentralisedleague.RecyclerModels.AllPlayersModel;
import c.hackathon.decentralisedleague.Utils.SessionManager;

public class AllPlayersAdapter extends RecyclerView.Adapter<AllPlayersAdapter.MyViewHolder> {


    private List<AllPlayersModel> playersList;
    private View itemView;
    private Context mcontext;
    private SessionManager sessionManager;
    private String intent;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name,desc,form;

        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.playerName);
            desc = view.findViewById(R.id.playerStyle);
            form = view.findViewById(R.id.playerForm);

            sessionManager = new SessionManager(mcontext);

        }
    }

    public AllPlayersAdapter(List<AllPlayersModel> playersList, Context context, String intent){
        this.playersList = playersList;
        this.mcontext = context;
        this.intent = intent;
    }




    @NonNull
    @Override
    public AllPlayersAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.player_detail_card_view, parent, false);
        return new AllPlayersAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AllPlayersAdapter.MyViewHolder holder, final int position) {
        AllPlayersModel candidates = playersList.get(position);
        holder.name.setText(candidates.getName());
        holder.desc.setText(candidates.getParty());
        holder.form.setText(candidates.getAge());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(intent=="0"){
                    FragmentManager fragmentManager = ((AppCompatActivity) mcontext).getSupportFragmentManager();
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    BuyPlayerFragment buy = new BuyPlayerFragment();
                    Bundle bundle=new Bundle();
                    bundle.putString("name",playersList.get(position).getName());
                    bundle.putString("price",playersList.get(position).getAssets());
                    bundle.putString("origin",playersList.get(position).getOrigin());

                    if(playersList.get(position).getOrigin().equals("1")){
                        bundle.putString("id",playersList.get(position).getId());
                    }
//                      sessionManager.setCONTRACT_ID(playersList.get(position).getId());
                    buy.setArguments(bundle);
                    transaction.replace(R.id.content, buy).addToBackStack("tag").commit();
                }else{
                    FragmentManager fragmentManager = ((AppCompatActivity) mcontext).getSupportFragmentManager();
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    SellPlayerFragment sell = new SellPlayerFragment();
                    Bundle bundle=new Bundle();
                    bundle.putString("name",playersList.get(position).getName());
                    bundle.putString("price",playersList.get(position).getAssets());

//                    sessionManager.setCONTRACT_ID(playersList.get(position).getId());
                    sell.setArguments(bundle);
                    transaction.replace(R.id.content,sell).addToBackStack("tag").commit();
                }
//                FragmentManager fragmentManager = ((AppCompatActivity) mcontext).getSupportFragmentManager();
//                FragmentTransaction transaction = fragmentManager.beginTransaction();
//                SellPlayerFragment Candidate = new SellPlayerFragment();
//                Bundle bundle=new Bundle();
//                bundle.putString("id",playersList.get(position).getId());
//                bundle.putString("name",playersList.get(position).getName());
//                bundle.putString("party",playersList.get(position).getParty());
//                sessionManager.setCONTRACT_ID(playersList.get(position).getId());
//                Candidate.setArguments(bundle);
//                transaction.replace(R.id.content, Candidate).addToBackStack("tag").commit();
            }
        });

    }

    @Override
    public int getItemCount() {
        return playersList.size();
    }
}
