package com.example.imageview.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.imageview.R;
import com.example.imageview.databinding.FragmentContestsBinding;

import java.util.ArrayList;

import Adapters.PastContestAdapter;
import Adapters.UpcommingContestAdapter;
import models.Example;
import viewmodels.ContestListViewModel;

public class ContestsFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public ContestsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    FragmentContestsBinding contestsBindingXml;

    ArrayList<Example> contestList;
    ContestListViewModel listViewModel;
    UpcommingContestAdapter upcommingContestAdapter;
    PastContestAdapter pastContestAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        contestsBindingXml = DataBindingUtil.inflate(inflater, R.layout.fragment_contests, container, false);
        View view = contestsBindingXml.getRoot();

        contestsBindingXml.recyclerUpcomingContest.setLayoutManager(new LinearLayoutManager(getContext()));
        upcommingContestAdapter = new UpcommingContestAdapter(contestList);
        contestsBindingXml.recyclerUpcomingContest.setAdapter(upcommingContestAdapter);

        contestsBindingXml.recyclerPastContest.setLayoutManager(new LinearLayoutManager(getContext()));
        pastContestAdapter = new PastContestAdapter(contestList);
        contestsBindingXml.recyclerPastContest.setAdapter(pastContestAdapter);


        contestsBindingXml.recyclerUpcomingContest.setNestedScrollingEnabled(false);
        contestsBindingXml.recyclerPastContest.setNestedScrollingEnabled(false);

        listViewModel = new ViewModelProvider(getActivity()).get(ContestListViewModel.class);
        listViewModel.getContestListObserver().observe(getActivity(), new Observer<ArrayList<Example>>() {
            @Override
            public void onChanged(ArrayList<Example> examples) {

                if (examples != null) {
                    contestList = examples;
                    upcommingContestAdapter.updateimagelist(examples);
                    pastContestAdapter.updateimagelist2(examples);
                } else {
                    Log.d("Onfail", "fail: ");
                }
            }
        });
        listViewModel.makeContestApiCall();


        return view;


    }
}