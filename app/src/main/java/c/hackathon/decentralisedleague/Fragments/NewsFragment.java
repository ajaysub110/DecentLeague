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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import c.hackathon.decentralisedleague.R;
import c.hackathon.decentralisedleague.RecyclerAdapters.NewsAdapter;
import c.hackathon.decentralisedleague.RecyclerModels.NewsModel;
import c.hackathon.decentralisedleague.Utils.SessionManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment {


    private SessionManager sessionManager;
    private RecyclerView UpcomingElectionsRecycler;
    private List<NewsModel> newsList = new ArrayList<>();
    private NewsAdapter newsRecyclerAdapter;
    private DatabaseReference mdatabase;
    private ProgressDialog pd;

    public NewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_news, container, false);

        sessionManager = new SessionManager(this.getActivity());

        UpcomingElectionsRecycler = v.findViewById(R.id.newsRecycler);

        pd = new ProgressDialog(getActivity());
        pd.setMessage("loading");
        pd.setCancelable(false);

        mdatabase = FirebaseDatabase.getInstance().getReference().child("news");

        sessionManager = new SessionManager(this.getActivity());

        RecyclerView.LayoutManager newsLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext(),LinearLayoutManager.VERTICAL,false);

        newsRecyclerAdapter = new NewsAdapter(newsList);

        UpcomingElectionsRecycler.setLayoutManager(newsLayoutManager);
        UpcomingElectionsRecycler.setItemAnimator(new DefaultItemAnimator());
        UpcomingElectionsRecycler.setAdapter(newsRecyclerAdapter);


        mdatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                pd.dismiss();
                newsList.clear();
                for(DataSnapshot AllNews : dataSnapshot.getChildren()){
                    NewsModel news = new NewsModel();
                    news.setTitle(AllNews.child("title").getValue().toString());
                    news.setDesc(AllNews.child("desc").getValue().toString());
                    newsList.add(news);
                }
                newsRecyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

//        NewsModel news = new NewsModel();
//        news.setTitle("diwhf");
//        news.setDesc("wdjnef");
//        newsList.add(news);
//        newsList.add(news);
//        newsList.add(news);

        return v;

    }

}
