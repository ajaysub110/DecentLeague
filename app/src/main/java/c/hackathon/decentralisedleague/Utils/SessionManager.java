package c.hackathon.decentralisedleague.Utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    int PRIVATE_MODE = 0;
    Context ctx;
    private static final String PREF_NAME = "VotZure";

    private String BEARER_TOKEN = "bearer_token";
    private String EMAIL = "email";



    public SessionManager(Context context) {
        this.ctx = context;
        sharedPreferences = ctx.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }


    public  void setEMAIL(String email){
        editor.putString(EMAIL,email);
        editor.commit();
    }

    public String getEMAIL(){
        return sharedPreferences.getString(EMAIL,null);
    }
    public String getBEARER_TOKEN() {
        return sharedPreferences.getString(BEARER_TOKEN,null);
    }

    public void setBEARER_TOKEN(String token) {
        editor.putString(BEARER_TOKEN,token);
        editor.commit();
    }



}
