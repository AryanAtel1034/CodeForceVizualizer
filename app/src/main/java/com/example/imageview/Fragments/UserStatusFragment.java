package com.example.imageview.Fragments;

import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.imageview.R;
import com.example.imageview.databinding.FragmentUserStatusBinding;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import models.Example;
import viewmodels.RecentListViewModel;
import viewmodels.StatsListViewModel;

public class UserStatusFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    FragmentUserStatusBinding fragmentUserStatusBinding;
    List<Example> statsList, recentList;
    StatsListViewModel listViewModel;
    RecentListViewModel recentListViewModel;
    int color2, color1, size = 0;
    private boolean flag = true;

    private String mParam1;
    private String mParam2;

    public UserStatusFragment() {
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
//    RecentAdapter recentAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        fragmentUserStatusBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_user_status, container, false);
        View view = fragmentUserStatusBinding.getRoot();
        color1 = getResources().getColor(R.color.lighGrrren);
        color2 = getResources().getColor(R.color.green);


        listViewModel = ViewModelProviders.of(getActivity()).get(StatsListViewModel.class);
        listViewModel.getStatsListObserve().observe(getActivity(), new Observer<List<Example>>() {
            @Override
            public void onChanged(List<Example> examples) {
                if (examples != null) {
                    statsList = examples;
                    size = statsList.get(0).getResult().size();
                    lineChart();

                } else {
                    Log.d("Onfail", "fail: ");
                }
            }
        });
        listViewModel.statsApiCall();

        recentListViewModel = ViewModelProviders.of(getActivity()).get(RecentListViewModel.class);
        recentListViewModel.getRecentListObserver().observe(getActivity(), new Observer<List<Example>>() {
            @Override
            public void onChanged(List<Example> examples) {

                if (examples != null) {
                    recentList = examples;
                    createTextView();

                } else {
                    Log.d("Onfail", "fail: ");
                }

            }
        });
        recentListViewModel.makeRecentApiCall();


        return view;
    }


    public void lineChart() {
        LineDataSet lineDataSet = new LineDataSet(lineChartData(), "Last 15 Ranks");
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSet);
        LineData lineData = new LineData(dataSets);
        XAxis xAxis = fragmentUserStatusBinding.lineChart.getXAxis();
        lineDataSet.setColor(Color.BLACK); // Your line color
        lineDataSet.setCircleColor(color2);
        lineDataSet.setLineWidth(2.5f); // Increase here for line width
        lineDataSet.setCircleRadius(5f);
        lineDataSet.setValueTextSize(10f);
        lineDataSet.setDrawFilled(true);
        lineDataSet.setFormLineWidth(10f);
        lineDataSet.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
        lineDataSet.setFormSize(15.f);
        lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        lineDataSet.setFillColor(color1);
        fragmentUserStatusBinding.lineChart.setData(lineData);
        fragmentUserStatusBinding.lineChart.invalidate();
        fragmentUserStatusBinding.lineChart.getAxisRight().setEnabled(false);
        fragmentUserStatusBinding.lineChart.getXAxis().setEnabled(false);

    }

    private ArrayList<Entry> lineChartData() {

        ArrayList<Entry> data = new ArrayList<Entry>();


        if (size > 15) {
            size = 15;
        }


        for (int i = 0; i < size; i++) {

            int rank = Integer.parseInt(statsList.get(0).getResult().get(i).getRank());
            data.add(new Entry(i, rank));


        }
        return data;

    }

    private void createTextView() {
        Set<String> tags = new HashSet<String>();

        for (int i = 0; i < recentList.get(0).getResult().size(); i++) {

            for (int j = 0; j < recentList.get(0).getResult().get(i).getProblem().getTags().size(); j++) {


                tags.add(recentList.get(0).getResult().get(i).getProblem().getTags().get(j));


            }
        }
        List<String> list = new ArrayList<String>(tags);


        for (int k = 0; k < list.size(); k++) {
            if (getActivity() == null) {
                return;
            } else if (flag == true) {
                TextView text = new TextView(getActivity());
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                text.setLayoutParams(layoutParams);
                text.setText(list.get(k));
                text.setTextColor(Color.BLACK);
                layoutParams.setMargins(15, 10, 15, 10);
                text.setPadding(10, 10, 10, 10);
                text.setBackground(getResources().getDrawable(R.drawable.greenbackgroundtag));

                fragmentUserStatusBinding.linerLayoutDynamicText.addView(text);
            }

        }
        flag = false;

    }
}