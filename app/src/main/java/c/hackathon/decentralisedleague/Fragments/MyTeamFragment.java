package c.hackathon.decentralisedleague.Fragments;


import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import c.hackathon.decentralisedleague.R;
import c.hackathon.decentralisedleague.RecyclerAdapters.AllPlayersAdapter;
import c.hackathon.decentralisedleague.RecyclerModels.AllPlayersModel;
import c.hackathon.decentralisedleague.RecyclerModels.NewsModel;
import c.hackathon.decentralisedleague.Utils.SessionManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyTeamFragment extends Fragment {


    private RecyclerView AllPlayersRecycler;
    private List<AllPlayersModel> playersList = new ArrayList<>();
    private AllPlayersAdapter playersRecyclerAdapter;
    private DatabaseReference mdatabase;
    private ProgressDialog pd;
    private TextView balance;

    public MyTeamFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_my_team, container, false);

        SessionManager sessionManager = new SessionManager(this.getActivity());

        AllPlayersRecycler = v.findViewById(R.id.yourTeamRecyclerView);

        if(sessionManager.getEMAIL().equals("nipun.agarwal12@hotmail.com")){
            mdatabase = FirebaseDatabase.getInstance().getReference().child("teamA");
        }else{
            mdatabase = FirebaseDatabase.getInstance().getReference().child("teamB");
        };


        pd = new ProgressDialog(getActivity());
        pd.setMessage("loading");
        pd.setCancelable(false);



        balance = v.findViewById(R.id.balanceRemaining);

        RecyclerView.LayoutManager newsLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        GridLayoutManager mGridLayoutManager = new GridLayoutManager(this.getActivity(), 2);
        AllPlayersRecycler.setLayoutManager(mGridLayoutManager);

//        AllPlayersModel player = new AllPlayersModel();
//        player.setAge("20");
//        player.setName("Nipun Agarwal");
//        player.setParty("wdiwfj");
//
//        playersList.add(player);
//        playersList.add(player);
//        playersList.add(player);
//        playersList.add(player);
//        playersList.add(player);
//        playersList.add(player);
//        playersList.add(player);
//        playersList.add(player);

        playersRecyclerAdapter = new AllPlayersAdapter(playersList,this.getActivity(),"1");

        AllPlayersRecycler.setLayoutManager(mGridLayoutManager);
        AllPlayersRecycler.setItemAnimator(new DefaultItemAnimator());
        AllPlayersRecycler.setAdapter(playersRecyclerAdapter);


        mdatabase.child("balance").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                balance.setText(dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        mdatabase.child("players").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                pd.dismiss();
                playersList.clear();
                for(DataSnapshot AllNews : dataSnapshot.getChildren()){
                    AllPlayersModel player = new AllPlayersModel();
                    player.setName(AllNews.child("name").getValue().toString());
                    player.setAge(AllNews.child("form").getValue().toString());
                    player.setParty(AllNews.child("desc").getValue().toString());
                    playersList.add(player);
                }
                playersRecyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return v;
    }

}
