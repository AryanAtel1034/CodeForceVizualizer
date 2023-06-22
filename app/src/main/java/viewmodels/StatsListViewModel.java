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

public class StatsListViewModel extends ViewModel {


    static String userName = "";
    private MutableLiveData<List<Example>> statsList; // In this we get JSON fetched data
    private List<Example> ststsArrayList;

    public StatsListViewModel() {
        statsList = new MutableLiveData<>();
        ststsArrayList = new ArrayList<>();
    }

    public void getUserName(String name) {

        userName = name;

    }

    public MutableLiveData<List<Example>> getStatsListObserve() {
        return statsList;
    }

    public void statsApiCall() {

        APIServices apiServices = RetrofitInstance.getRetroClient().create(APIServices.class);
        Call<Example> call = apiServices.getUserRating(userName);
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                ststsArrayList.add(response.body());
                statsList.setValue(ststsArrayList);
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Log.d("Contests", "onFailure: ");
                statsList.postValue(null);
            }
        });
    }
}
