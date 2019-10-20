package c.hackathon.decentralisedleague.Fragments;


import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import c.hackathon.decentralisedleague.ApiModels.GetAllMarketPlacePlayers;
import c.hackathon.decentralisedleague.R;
import c.hackathon.decentralisedleague.RecyclerAdapters.AllPlayersAdapter;
import c.hackathon.decentralisedleague.RecyclerAdapters.NewsAdapter;
import c.hackathon.decentralisedleague.RecyclerModels.AllPlayersModel;
import c.hackathon.decentralisedleague.RecyclerModels.NewsModel;
import c.hackathon.decentralisedleague.Retrofit.AzureApiClient;
import c.hackathon.decentralisedleague.Utils.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListAllPlayers extends Fragment {


    private RecyclerView AllPlayersRecycler;
    private List<AllPlayersModel> playersList = new ArrayList<>();
    private AllPlayersAdapter playersRecyclerAdapter;
    private DatabaseReference mdatabase;
    private ProgressDialog pd;

    public ListAllPlayers() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_list_all_players, container, false);
        SessionManager sessionManager = new SessionManager(this.getActivity());

        AllPlayersRecycler = v.findViewById(R.id.allPlayersRecycler);

        RecyclerView.LayoutManager newsLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        GridLayoutManager mGridLayoutManager = new GridLayoutManager(this.getActivity(), 2);
        AllPlayersRecycler.setLayoutManager(mGridLayoutManager);


        pd = new ProgressDialog(getActivity());
        pd.setMessage("loading");
        pd.setCancelable(false);

        mdatabase = FirebaseDatabase.getInstance().getReference().child("allPlayers");

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

        mdatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                pd.dismiss();
                playersList.clear();
                for(DataSnapshot AllNews : dataSnapshot.getChildren()){
                    AllPlayersModel player = new AllPlayersModel();
                    player.setName(AllNews.child("name").getValue().toString());
                    player.setParty(AllNews.child("position").getValue().toString());
                    player.setAge(AllNews.child("form").getValue().toString());
                    player.setAssets(AllNews.child("price").getValue().toString());
                    player.setOrigin("0");
                    playersList.add(player);
                }
                playersRecyclerAdapter.notifyDataSetChanged();

                CallAPi();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        playersRecyclerAdapter = new AllPlayersAdapter(playersList,this.getActivity(),"0");

        AllPlayersRecycler.setLayoutManager(mGridLayoutManager);
        AllPlayersRecycler.setItemAnimator(new DefaultItemAnimator());
        AllPlayersRecycler.setAdapter(playersRecyclerAdapter);





        return v;
    }

    private void CallAPi() {

        Call<GetAllMarketPlacePlayers> call = AzureApiClient.getClient().getAllMarketPlacePlayers("11");
        call.enqueue(new Callback<GetAllMarketPlacePlayers>() {
            @Override
            public void onResponse(Call<GetAllMarketPlacePlayers> call, Response<GetAllMarketPlacePlayers> response) {
                if(response.code()==200){
                    if(response.body().getContracts().size()!=0){
                        for(int i=0;i<response.body().getContracts().size();i++){
                            if(Integer.parseInt(response.body().getContracts().get(i).getContractProperties().get(0).getValue())==0){
                                AllPlayersModel allCandidates = new AllPlayersModel();
                                allCandidates.setName(response.body().getContracts().get(i).getContractProperties().get(1).getValue());
                                allCandidates.setAssets(response.body().getContracts().get(i).getContractProperties().get(2).getValue());
                                allCandidates.setParty("ejffb");
                                allCandidates.setAge("20");
                                allCandidates.setOrigin("1");
                                allCandidates.setId(Integer.toString(response.body().getContracts().get(i).getId()));
                                playersList.add(allCandidates);
                            }
                        }
                        playersRecyclerAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<GetAllMarketPlacePlayers> call, Throwable t) {

            }
        });
    }

}
