package c.hackathon.decentralisedleague.Fragments;


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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import c.hackathon.decentralisedleague.R;
import c.hackathon.decentralisedleague.RecyclerAdapters.NewsAdapter;
import c.hackathon.decentralisedleague.RecyclerAdapters.ResultsAdapter;
import c.hackathon.decentralisedleague.RecyclerModels.NewsModel;
import c.hackathon.decentralisedleague.RecyclerModels.ResultsModel;
import c.hackathon.decentralisedleague.Utils.SessionManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class ResultsFragment extends Fragment {


    private SessionManager sessionManager;
    private RecyclerView resultsRecycler;
    private List<ResultsModel> resultList = new ArrayList<>();
    private ResultsAdapter resultsRecyclerAdapter;
    private TextView resultsTxtView;

    private DatabaseReference mdatabase,mdatabase1;

    public ResultsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_results, container, false);

        sessionManager = new SessionManager(this.getActivity());

        resultsRecycler = v.findViewById(R.id.resultsRecyclerView);
        sessionManager = new SessionManager(this.getActivity());

        resultsTxtView = v.findViewById(R.id.resultTxtView);

        RecyclerView.LayoutManager newsLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext(),LinearLayoutManager.VERTICAL,false);

        resultsRecyclerAdapter = new ResultsAdapter(resultList);

        resultsRecycler.setLayoutManager(newsLayoutManager);
        resultsRecycler.setItemAnimator(new DefaultItemAnimator());
        resultsRecycler.setAdapter(resultsRecyclerAdapter);

        mdatabase = FirebaseDatabase.getInstance().getReference().child("resultDeclared");
        mdatabase1 = FirebaseDatabase.getInstance().getReference().child("results");


        mdatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue().toString().equals("1")){
                    resultsRecycler.setVisibility(View.VISIBLE);
                    resultsTxtView.setVisibility(View.GONE);
                }else{
                    resultsRecycler.setVisibility(View.GONE);
                    resultsTxtView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        mdatabase1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ResultsModel result = new ResultsModel();
                result.setTitle("Team A");
                result.setDesc(dataSnapshot.child("teamAForm").getValue().toString());
                resultList.add(result);

                ResultsModel result1 = new ResultsModel();

                result1.setTitle("Team B");
                result1.setDesc(dataSnapshot.child("teamBForm").getValue().toString());

                resultList.add(result1);
                resultsRecyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        return v;
    }

}
