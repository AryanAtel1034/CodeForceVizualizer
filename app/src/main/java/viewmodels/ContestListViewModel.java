package viewmodels;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.imageview.MainScreen;

import java.io.Closeable;
import java.util.ArrayList;

import Retrofit.APIServices;
import Retrofit.RetrofitInstance;
import models.Example;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContestListViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Example>> contestList; // In this we get JSON fetched data
    private ArrayList<Example> contestArrayList;



    public  ContestListViewModel(){
        contestList=new MutableLiveData<>();
        contestArrayList= new ArrayList<>();
    }
    public MutableLiveData<ArrayList<Example>> getContestListObserver() {
        return contestList;
    }
    public  void makeContestApiCall(){
        APIServices apiServices = RetrofitInstance.getRetroClient().create(APIServices.class);
        Call<Example> call= apiServices.getUpcomingcontest(false);
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                contestArrayList.add(response.body());
                contestList.postValue(contestArrayList);
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Log.d("Contests", "onFailure: ");
                contestList.postValue(null);

            }
        });

    }
}
