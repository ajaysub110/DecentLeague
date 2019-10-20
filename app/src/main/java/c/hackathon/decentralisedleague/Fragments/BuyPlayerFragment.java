package c.hackathon.decentralisedleague.Fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import c.hackathon.decentralisedleague.R;
import c.hackathon.decentralisedleague.RecyclerModels.AllPlayersModel;
import c.hackathon.decentralisedleague.RecyclerModels.BuyPlayerModel;
import c.hackathon.decentralisedleague.RecyclerModels.NewsModel;
import c.hackathon.decentralisedleague.Retrofit.AzureApiClient;
import c.hackathon.decentralisedleague.Utils.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class BuyPlayerFragment extends Fragment {


    private String name;
    private String price;
    private String origin;
    private String contractId;

    private EditText priceEdtTxt;
    private TextView nameTxtView;
    private List<Object> list = new ArrayList<>();
    private DatabaseReference mdatabase,mdatabase1,playerTeamDatabase;
    private int buyerBalance;
    private int sellerBalance;
    private SessionManager sessionManager;
    private String buyerTeam;
    private String sellerTeam;
    private Button buyBtn;
    private DatabaseReference allPlayersDatabase;

    public BuyPlayerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_buy_player, container, false);
        Bundle bundle = this.getArguments();

        priceEdtTxt = v.findViewById(R.id.playerPrice);
        nameTxtView = v.findViewById(R.id.playerName);
        sessionManager = new SessionManager(this.getActivity());




        buyBtn = v.findViewById(R.id.buyBtn);

        if(sessionManager.getEMAIL().equals("nipun.agarwal12@hotmail.com")){
            buyerTeam = "teamA";
            sellerTeam = "teamB";
        }else{
            buyerTeam = "teamB";
            sellerTeam = "teamA";
        }


        playerTeamDatabase = FirebaseDatabase.getInstance().getReference().child(buyerTeam).child("players");


        mdatabase = FirebaseDatabase.getInstance().getReference().child(buyerTeam);
        mdatabase1 = FirebaseDatabase.getInstance().getReference().child(sellerTeam);

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        final FragmentTransaction transaction = fragmentManager.beginTransaction();


        allPlayersDatabase = FirebaseDatabase.getInstance().getReference().child("allPlayers");

        mdatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                buyerBalance = Integer.parseInt(dataSnapshot.child("balance").getValue().toString());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mdatabase1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                sellerBalance = Integer.parseInt(dataSnapshot.child("balance").getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        if(bundle!=null){
            name = bundle.getString("name");
            price = bundle.getString("price");
            origin = bundle.getString("origin");
            contractId = bundle.getString("id");
        }

        nameTxtView.setText(name);
        priceEdtTxt.setText(price);



        buyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(origin.equals("1")){
                    BuyPlayerModel body = new BuyPlayerModel();
                    body.setWorkflowFunctionID(35);
                    body.setWorkflowActionParameters(list);


                    Log.e("buyFragment",origin);
                    Log.e("buyFragment",contractId);


                    if(Integer.parseInt(price)>=buyerBalance){
                        Toast.makeText(getActivity(), "Insufficient Balance", Toast.LENGTH_SHORT).show();
                    }else{

                        Call call = AzureApiClient.getClient().buyPlayer(contractId,body);
                        call.enqueue(new Callback() {
                            @Override
                            public void onResponse(Call call, Response response) {
                                if(response.code()==200){
                                    buyerBalance-=Integer.parseInt(price);
                                    sellerBalance+=Integer.parseInt(price);
                                    HashMap<String,Object> map = new HashMap<>();
                                    map.put("balance",buyerBalance);
                                    HashMap<String,Object> map1 = new HashMap<>();
                                    map1.put("balance",sellerBalance);
                                    mdatabase.updateChildren(map);
                                    mdatabase1.updateChildren(map1);
                                    DatabaseReference test = playerTeamDatabase.push();
                                    test.child("name").setValue(name);
                                    test.child("desc").setValue("Attacker");
                                    test.child("form").setValue("20");
                                    Toast.makeText(getActivity(), "Player Successfully Purchased", Toast.LENGTH_SHORT).show();
                                    transaction.replace(R.id.content, new MyTeamFragment()).addToBackStack("tag").commit();
                                }else{
                                    Toast.makeText(getActivity(), "Failed to purchase Products", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call call, Throwable t) {
                                Toast.makeText(getActivity(), "Error Occured", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }

                }else{


                    allPlayersDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for(DataSnapshot AllNews : dataSnapshot.getChildren()){

                                if(AllNews.child("name").getValue().toString().equals(name)){
                                    buyerBalance-=Double.parseDouble(price);

                                    HashMap<String,Object> map = new HashMap<>();
                                    map.put("balance",buyerBalance);

                                    mdatabase.updateChildren(map);


                                    String key = AllNews.getKey();
                                    allPlayersDatabase.child(key).removeValue();

                                    DatabaseReference test = playerTeamDatabase.push();
                                    test.child("name").setValue(name);
                                    test.child("desc").setValue("Attacker");
                                    test.child("form").setValue("20");


                                    Toast.makeText(getActivity(), "Player Successfully Purchased", Toast.LENGTH_SHORT).show();
                                    transaction.replace(R.id.content, new MyTeamFragment()).addToBackStack("tag").commit();
                                }
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
            }
        });

        return v;

    }

}
