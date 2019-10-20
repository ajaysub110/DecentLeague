package c.hackathon.decentralisedleague.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
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

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import c.hackathon.decentralisedleague.ApiModels.SellPlayerBodyModel;
import c.hackathon.decentralisedleague.R;
import c.hackathon.decentralisedleague.Retrofit.AzureApiClient;
import c.hackathon.decentralisedleague.Utils.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class SellPlayerFragment extends Fragment {


    private EditText priceEdtTxt;
    private TextView nameTxtView;
    String name,price;
    private Button sellBtn;
    private List<SellPlayerBodyModel.WorkflowActionParameter> list = new ArrayList<>();
    private DatabaseReference teamSellingPlayers;
    private SessionManager sessionManager;
    private String buyerTeam;
    private String sellerTeam;
    private TextView balance;
    private DatabaseReference mdatabase;

    public SellPlayerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_sell_player, container, false);

        priceEdtTxt = v.findViewById(R.id.playerPrice);
        nameTxtView = v.findViewById(R.id.playerName);
        Bundle bundle = this.getArguments();

        sessionManager = new SessionManager(this.getContext());

        if(bundle!=null){
            name = bundle.getString("name");
            price = bundle.getString("price");
        }

        nameTxtView.setText(name);
        priceEdtTxt.setText(price);
        sellBtn = v.findViewById(R.id.sellBtn);

        if(sessionManager.getEMAIL().equals("nipun.agarwal12@hotmail.com")){
            buyerTeam = "teamA";
            sellerTeam = "teamB";
        }else{
            buyerTeam = "teamB";
            sellerTeam = "teamA";
        }


        teamSellingPlayers = FirebaseDatabase.getInstance().getReference().child(buyerTeam).child("sellingPlayers");


        sellBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                final FragmentTransaction transaction = fragmentManager.beginTransaction();


                price = priceEdtTxt.getText().toString();
                if(!TextUtils.isEmpty(price)){
                    final SellPlayerBodyModel model = new SellPlayerBodyModel();
                    model.setWorkflowFunctionId(32);
                    SellPlayerBodyModel.WorkflowActionParameter parameter1 = new SellPlayerBodyModel.WorkflowActionParameter();
                    parameter1.setName("PlayerName");
                    parameter1.setValue(name);
                    SellPlayerBodyModel.WorkflowActionParameter parameter2 = new SellPlayerBodyModel.WorkflowActionParameter();
                    parameter2.setName("PlayerPrice");
                    parameter2.setValue(price);
                    list.add(parameter1);
                    list.add(parameter2);
                    model.setWorkflowActionParameters(list);

                    Call call = AzureApiClient.getClient().sellPlayer("28",model);
                    call.enqueue(new Callback() {
                        @Override
                        public void onResponse(Call call, Response response) {
                            if(response.code()==200){

                                DatabaseReference m1 = teamSellingPlayers.push();
                                m1.child("name").setValue(name);
                                m1.child("desc").setValue("Defender");
                                m1.child("form").setValue("15");

                                transaction.replace(R.id.content, new MyTeamFragment()).addToBackStack("tag").commit();

                                Toast.makeText(getActivity(), "Player successfully posted for listing", Toast.LENGTH_SHORT).show();
                            }else{
                                Log.e("sellPlayer",model.getWorkflowActionParameters().get(0).getName());
                                Log.e("sellPlayer",model.getWorkflowActionParameters().get(0).getValue());
                                Log.e("sellPlayer",Integer.toString(response.code()));
                                Log.e("sellPlayer",response.message());
                                Toast.makeText(getActivity(), "Failed to sell", Toast.LENGTH_SHORT).show();

                            }
                        }

                        @Override
                        public void onFailure(Call call, Throwable t) {
                            Toast.makeText(getActivity(), "API Error", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        });


        return v;
    }

}
