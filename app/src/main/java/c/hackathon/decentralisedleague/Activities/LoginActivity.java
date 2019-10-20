package c.hackathon.decentralisedleague.Activities;

import androidx.appcompat.app.AppCompatActivity;
import c.hackathon.decentralisedleague.ApiModels.GetUserInfo;
import c.hackathon.decentralisedleague.R;
import c.hackathon.decentralisedleague.Retrofit.AzureApiClient;
import c.hackathon.decentralisedleague.Utils.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.firebase.database.DatabaseReference;

public class LoginActivity extends AppCompatActivity {



    public WebView loginWebView;
    private String currentUrl;
    private SessionManager sessionManager;
    private ProgressDialog pd;
    private DatabaseReference mdatabase;
    private int flag = 0;
    private String newUser;
    private String activity = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        loginWebView = findViewById(R.id.loginWebView);
        sessionManager = new SessionManager(this);

        Intent i = getIntent();
        activity = i.getStringExtra("activity");

        pd = new ProgressDialog(this);
        pd.setMessage("loading");
        pd.setCancelable(false);







        final android.webkit.CookieManager cookieManager = CookieManager.getInstance();

        loginWebView.getSettings().setJavaScriptEnabled(true);
        loginWebView.loadUrl("https://login.microsoftonline.com/agarwalnipun12gmail.onmicrosoft.com/oauth2/authorize?resource=2a1185b4-c363-4341-823b-2a0da56cbe1b&client_id=2a1185b4-c363-4341-823b-2a0da56cbe1b&response_type=token&redirect_uri=https://myblockchain-wglsg7.azurewebsites.net");

        pd.show();

        loginWebView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                pd.dismiss();
                Log.e("finishedUrl", url);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                currentUrl=url;
                Log.e("currentUrl",currentUrl);
                if(currentUrl.contains("access_token")){
                    Integer start = url.lastIndexOf("access_token");
                    Integer end = url.lastIndexOf("&token_type");
                    String token = currentUrl.substring(start+13,end);
                    sessionManager.setBEARER_TOKEN(token);
                    Log.e("token",token);


                    getEmail();

//                    Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
//                    startActivity(intent);

//                    loginWebView.clearCache(true);
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                        cookieManager.removeAllCookies(new ValueCallback<Boolean>() {
//                            // a callback which is executed when the cookies have been removed
//                            @Override
//                            public void onReceiveValue(Boolean aBoolean) {
//                                Log.d("url", "Cookie removed: " + aBoolean);
//                            }
//                        });
//                    }
//                    else cookieManager.removeAllCookie();

                }else{
                    loginWebView.loadUrl(url);
                }

                return true;
            }
        });


    }

    private void getEmail() {
        Call<GetUserInfo> call = AzureApiClient.getClient().getUserInfo();
        call.enqueue(new Callback<GetUserInfo>() {
            @Override
            public void onResponse(Call<GetUserInfo> call, Response<GetUserInfo> response) {
                if(response.code()==200){
                    String email = response.body().getCurrentUser().getEmailAddress();
                    sessionManager.setEMAIL(email);
                    Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<GetUserInfo> call, Throwable t) {

            }
        });
    }
}
