package viewmodels;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import Retrofit.APIServices;
import Retrofit.RetrofitInstance;
import models.Example;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecentListViewModel extends ViewModel {


    private MutableLiveData<List<Example>> recentList; // In this we get JSON fetched data
    private List<Example> recentArrayList;
    static String userName = "";

    public void getUserName(String name){

        userName=name;

    }


    public RecentListViewModel() {
        recentList = new MutableLiveData<>();
        recentArrayList = new ArrayList<>();
    }

    public MutableLiveData< List<Example>> getRecentListObserver() {
        return recentList;
    }


    public void makeRecentApiCall() {

        APIServices apiServices = RetrofitInstance.getRetroClient().create(APIServices.class);
        Call<Example> call= apiServices.getUserSubmission(userName);
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {

                recentArrayList.add(response.body());
                recentList.setValue(recentArrayList);

            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {

                Log.d("Contests", "onFailure: ");
                recentList.postValue(null);

            }
        });


    }
}
