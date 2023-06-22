package Retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    public static String baseurl ="https://codeforces.com/api/";
    private static Retrofit retrofit;

    public  static Retrofit getRetroClient(){ // Custom function made

        if (retrofit==null){
            retrofit=new Retrofit.Builder()
                    .baseUrl(baseurl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();


        }
        return retrofit;
    }
}
