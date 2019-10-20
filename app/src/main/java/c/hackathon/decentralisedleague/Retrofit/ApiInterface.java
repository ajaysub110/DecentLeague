package c.hackathon.decentralisedleague.Retrofit;

import c.hackathon.decentralisedleague.ApiModels.GetAllMarketPlacePlayers;
import c.hackathon.decentralisedleague.ApiModels.GetUserInfo;
import c.hackathon.decentralisedleague.ApiModels.SellPlayerBodyModel;
import c.hackathon.decentralisedleague.RecyclerModels.BuyPlayerModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("api/v1/users/me")
    Call<GetUserInfo> getUserInfo();

    @GET("api/v1/contracts")
    Call<GetAllMarketPlacePlayers> getAllMarketPlacePlayers(@Query("workflowId") String id);

    @POST("api/v1/contracts/{id}/actions")
    Call<Void> sellPlayer(@Path("id") String id, @Body SellPlayerBodyModel body);

    @POST("api/v1/contracts/{id}/actions")
    Call<Void> buyPlayer(@Path("id") String id, @Body BuyPlayerModel body);

}
