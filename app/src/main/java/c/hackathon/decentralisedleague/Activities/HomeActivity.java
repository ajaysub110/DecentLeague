package c.hackathon.decentralisedleague.Activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import c.hackathon.decentralisedleague.Fragments.BuyPlayerFragment;
import c.hackathon.decentralisedleague.Fragments.ListAllPlayers;
import c.hackathon.decentralisedleague.Fragments.MyTeamFragment;
import c.hackathon.decentralisedleague.Fragments.NewsFragment;
import c.hackathon.decentralisedleague.Fragments.ResultsFragment;
import c.hackathon.decentralisedleague.Fragments.SellPlayerFragment;
import c.hackathon.decentralisedleague.Fragments.TeamSellingPlayersFragment;
import c.hackathon.decentralisedleague.R;
import c.hackathon.decentralisedleague.Utils.SessionManager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);

        SessionManager sessionManager = new SessionManager(this);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Objects.requireNonNull(getSupportActionBar()).setTitle("DeCent League");

        toolbar.setTitle("DeCent League");
//        FloatingActionButton fab = findViewById(R.id.fab);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.colorAccent));

//        Toast.makeText(this, sessionManager.getEMAIL(), Toast.LENGTH_SHORT).show();

        FragmentManager fragmentManager = getSupportFragmentManager();
        final FragmentTransaction transaction = fragmentManager.beginTransaction();



        transaction.replace(R.id.content, new NewsFragment()).addToBackStack("tag").commit();

//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                FragmentManager fragmentManager = getSupportFragmentManager();
//
//                FragmentTransaction transaction = fragmentManager.beginTransaction();
//                ListAllPlayers frg1 = new ListAllPlayers ();
//                transaction.replace(R.id.content, frg1).addToBackStack("tag").commit();
////                LaunchCamera();
//            }
//        });

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_news) {

            transaction.replace(R.id.content, new NewsFragment()).addToBackStack("tag").commit();

            // Handle the camera action
        }else if (id == R.id.nav_buy_player) {

            transaction.replace(R.id.content, new ListAllPlayers()).addToBackStack("tag").commit();

        }else if(id == R.id.nav_sell_player){

            transaction.replace(R.id.content, new MyTeamFragment()).addToBackStack("tag").commit();
//            transaction.replace(R.id.content, new TrendingFragment()).addToBackStack("tag").commit();
        }else if(id == R.id.nav_results){
//            signOut();
            transaction.replace(R.id.content, new ResultsFragment()).addToBackStack("tag").commit();

        }else if(id == R.id.nav_your_team){

            transaction.replace(R.id.content, new MyTeamFragment()).addToBackStack("tag").commit();
//            transaction.replace(R.id.content, new CartFragment()).addToBackStack("tag").commit();
        }else if(id == R.id.nav_share){

            String shareBody = "Hi, I am inviting you to vote for the election using VotZure which makes the voting extremely secure using 3FA and blockchain services";
            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "VotZure");
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
            startActivity(Intent.createChooser(sharingIntent, "Share via"));

        }else if(id == R.id.nav_selling_players){
            transaction.replace(R.id.content, new TeamSellingPlayersFragment()).addToBackStack("tag").commit();
        }




        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(this, "wjeufb", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }
}
