package viewmodels;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import Retrofit.APIServices;
import Retrofit.RetrofitInstance;
import models.Example;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InfoListViewModel extends ViewModel {


    static String userName = "";
    private final MutableLiveData<ArrayList<Example>> infoList; // In this we get JSON fetched data
    private final ArrayList<Example> userInfoArrayList;
    private final MutableLiveData<ArrayList<Example>> submissionList; // In this we get JSON fetched data
    private final ArrayList<Example> submissionArrayList;


    public InfoListViewModel() {
        infoList = new MutableLiveData<>();
        userInfoArrayList = new ArrayList<>();
        submissionList = new MutableLiveData<>();
        submissionArrayList = new ArrayList<>();

    }

    public void getUserName(String name) {
        userName = name;
        sendName();
    }

    public String sendName() {

        return userName;

    }

    public MutableLiveData<ArrayList<Example>> getInfoListObserver() {
        return infoList;

    }

    public MutableLiveData<ArrayList<Example>> getSubmissionObserver() {
        return submissionList;

    }


    public void makeApiCall() { // Custom function to make api call
        APIServices apiServices = RetrofitInstance.getRetroClient().create(APIServices.class);
        Call<Example> call = apiServices.getUserInfo(userName);
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {

                userInfoArrayList.add(response.body());
                infoList.postValue(userInfoArrayList);

            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Log.d("Onfail", "fail: ");
                infoList.postValue(null);

            }
        });


    }

    public void submissionApiCall() {
        APIServices apiServices = RetrofitInstance.getRetroClient().create(APIServices.class);
        Call<Example> call = apiServices.getUserSubmission(userName);
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                submissionArrayList.add(response.body());
                submissionList.postValue(submissionArrayList);
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Log.d("Onfail", "fail: ");
                submissionList.postValue(null);

            }
        });

    }

}
