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
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.imageview.R;
import com.example.imageview.databinding.FragmentUserRecentBinding;

import java.util.ArrayList;
import java.util.List;

import Adapters.RecentAdapter;
import models.Example;
import viewmodels.RecentListViewModel;


public class UserRecentFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    FragmentUserRecentBinding userRecentBinding;
    List<Example> recentList;
    RecentListViewModel listViewModel;
    RecentAdapter recentAdapter;
    private String mParam1;
    private String mParam2;

    public UserRecentFragment() {
        // Required empty public constructor
    }

    public static UserRecentFragment newInstance(String param1, String param2) {
        UserRecentFragment fragment = new UserRecentFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        userRecentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_user_recent, container, false);
        View view = userRecentBinding.getRoot();


        userRecentBinding.recentRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recentAdapter = new RecentAdapter(recentList, getActivity());
        userRecentBinding.recentRecyclerView.setAdapter(recentAdapter);
        userRecentBinding.recentRecyclerView.setNestedScrollingEnabled(false);

        listViewModel = ViewModelProviders.of(getActivity()).get(RecentListViewModel.class);

        listViewModel.getRecentListObserver().observe(getActivity(), new Observer<List<Example>>() {
            @Override
            public void onChanged(List<Example> examples) {
                if (examples != null) {
                    recentList = examples;
                    recentAdapter.updateimagelist3(examples);
                } else {
                    Log.d("Onfail", "fail: ");
                }

            }
        });
        listViewModel.makeRecentApiCall();


        return view;
    }
}