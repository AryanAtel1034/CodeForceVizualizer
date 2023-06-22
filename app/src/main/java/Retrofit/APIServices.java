package Retrofit;

import models.Example;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIServices {



    @GET("user.info")
    Call<Example> getUserInfo(@Query("handles") String username);

    @GET("user.rating")
    Call<Example> getUserRating(@Query("handle") String username);

    @GET("user.status")
    Call<Example> getUserSubmission(@Query("handle") String username);

    @GET("contest.list")
    Call<Example>getUpcomingcontest(@Query("gym") boolean flag);
}
