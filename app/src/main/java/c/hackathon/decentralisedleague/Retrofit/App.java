package c.hackathon.decentralisedleague.Retrofit;

import android.app.Application;

import c.hackathon.decentralisedleague.Utils.SessionManager;

public class App extends Application {
    private static App instance;

    private SessionManager sessionManager;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        sessionManager = new SessionManager(this);

    }
    public static App getInstance(){
        return instance;
    }

    public SessionManager getSessionManager() {
        return sessionManager;
    }
}
