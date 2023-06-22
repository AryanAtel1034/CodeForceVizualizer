package com.example.imageview.Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.imageview.R;
import com.example.imageview.databinding.FragmentUserInfoBinding;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import Adapters.UpcommingContestAdapter;
import models.Example;
import viewmodels.InfoListViewModel;

public class UserInfoFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    ArrayList<Example> infoList = new ArrayList<>();
    ArrayList<Example> submissionList = new ArrayList<Example>();
    InfoListViewModel listViewModel = new InfoListViewModel();
    UpcommingContestAdapter adapter;
    FragmentUserInfoBinding infoBindingXml;
    int[] colors = new int[25];
    private String mParam1;
    private String mParam2;
    private int size = 0;
    public UserInfoFragment() {
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        infoBindingXml = DataBindingUtil.inflate(inflater, R.layout.fragment_user_info, container, false);
        View view = infoBindingXml.getRoot();
        getColors();


        listViewModel = new ViewModelProvider(getActivity()).get(InfoListViewModel.class);
        listViewModel.getInfoListObserver().observe(getActivity(), new Observer<ArrayList<Example>>() {
            @Override
            public void onChanged(ArrayList<Example> examples) {

                if (examples != null) {
                    infoList = examples;
                    setUserInfo();


                } else {
                    Log.d("Onfail", "fail: ");
                }

            }
        });

        listViewModel.makeApiCall();


        listViewModel = new ViewModelProvider(getActivity()).get(InfoListViewModel.class);
        listViewModel.getSubmissionObserver().observe(getActivity(), new Observer<ArrayList<Example>>() {
            @Override
            public void onChanged(ArrayList<Example> examples) {

                if (examples != null) {
                    submissionList = examples;
                    getBarChart_Level();
                    getPieChart_Language();


                } else {
                    Log.d("Onfail", "fail: ");
                }

            }
        });
        listViewModel.submissionApiCall();


        return view;

    }

    private void setUserInfo() {
        infoBindingXml.txtUserName.setText(infoList.get(0).getResult().get(0).getFirstName());
        infoBindingXml.txtRankNo.setText(infoList.get(0).getResult().get(0).getMaxRank());
        infoBindingXml.txtUserSurname.setText(infoList.get(0).getResult().get(0).getLastName());
        infoBindingXml.txtUserCollege.setText(infoList.get(0).getResult().get(0).getOrganization());
        infoBindingXml.txtMaxRankNo.setText(infoList.get(0).getResult().get(0).getMaxRank());
        infoBindingXml.txtUserCountry.setText(infoList.get(0).getResult().get(0).getCountry());
        infoBindingXml.txtRatingNo.setText(infoList.get(0).getResult().get(0).getRating());
        infoBindingXml.txtMaxRatingNo.setText(infoList.get(0).getResult().get(0).getMaxRating());
        String imageUrl = infoList.get(0).getResult().get(0).getTitlePhoto();

        if (getActivity() == null) {

            return;

        } else {
            if (imageUrl.equals(null)) {


            } else {

                Glide.with(getActivity()).load(imageUrl).into(infoBindingXml.ImgUser); // We use glide library to upload the image
            }
        }

    }

    void getBarChart_Level() {
        size = submissionList.get(0).getResult().size();
        float a = 0, b = 0, c = 0, d = 0, e = 0, f = 0, g = 0, h = 0, i = 0, j = 0, k = 0, l = 0, m = 0, o = 0, q = 0, r = 0;
        Log.d("size", String.valueOf(size));

        for (int z = 0; z < size; z++) {
            String level = submissionList.get(0).getResult().get(z).getProblem().getIndex().toString();

//            Log.d("selected", level);

            if (level.equals("A")) {
                a++;
            } else if (level.equals("B")) {
                b++;

            } else if (level.equals("C")) {
                c++;
            } else if (level.equals("D")) {
                d++;
            } else if (level.equals("E")) {
                e++;
            } else if (level.equals("F")) {
                f++;
            } else if (level.equals("G")) {
                g++;
            } else if (level.equals("H")) {
                h++;
            } else if (level.equals("I")) {
                i++;
            } else if (level.equals("J")) {
                j++;
            } else if (level.equals("K")) {
                k++;
            } else if (level.equals("L")) {
                l++;
            } else if (level.equals("M")) {
                m++;
            } else if (level.equals("O")) {
                o++;
            } else if (level.equals("Q")) {
                q++;
            } else if (level.equals("R")) {
                r++;
            }


        }
        Log.d("selected", String.valueOf(a));
        ArrayList<BarEntry> barRecords = new ArrayList<>();
        barRecords.add(new BarEntry(1, a));
        barRecords.add(new BarEntry(2, b));
        barRecords.add(new BarEntry(3, c));
        barRecords.add(new BarEntry(4, d));
        barRecords.add(new BarEntry(5, e));
        barRecords.add(new BarEntry(6, f));
        barRecords.add(new BarEntry(7, g));
        barRecords.add(new BarEntry(8, h));
        barRecords.add(new BarEntry(9, i));
        barRecords.add(new BarEntry(10, j));
        barRecords.add(new BarEntry(11, k));
        barRecords.add(new BarEntry(12, l));
        barRecords.add(new BarEntry(13, m));
        barRecords.add(new BarEntry(14, o));
        barRecords.add(new BarEntry(15, q));
        barRecords.add(new BarEntry(16, r));


        BarDataSet dataSet1 = new BarDataSet(barRecords, "labels");
        dataSet1.setColors(colors);
        dataSet1.setValueTextColor(Color.BLACK);
        dataSet1.setValueTextSize(8f);

        BarData barData = new BarData(dataSet1);
        infoBindingXml.barChartTopics.setFitBars(true);
        infoBindingXml.barChartTopics.setData(barData);
        infoBindingXml.barChartTopics.animateY(2000);
        XAxis xAxis = infoBindingXml.barChartTopics.getXAxis();
        xAxis.setLabelCount(barRecords.size(), true);
        infoBindingXml.barChartTopics.getXAxis().setSpaceMax(10f);
        infoBindingXml.barChartTopics.getXAxis().setEnabled(false);
        infoBindingXml.barChartTopics.getAxisRight().setDrawAxisLine(false);
        barData.setBarWidth(0.5f);
        dataSet1.setBarShadowColor(Color.WHITE);

        infoBindingXml.barChartTopics.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {

                if (e.getX() == 1) {
                    Toast.makeText(getActivity(), "the value of level A: " + String.valueOf(e.getY()), Toast.LENGTH_SHORT).show();
                } else if (e.getX() == 2) {
                    Toast.makeText(getActivity(), "the value of level B: " + String.valueOf(e.getY()), Toast.LENGTH_SHORT).show();
                } else if (e.getX() == 3) {
                    Toast.makeText(getActivity(), "the value of level C: " + String.valueOf(e.getY()), Toast.LENGTH_SHORT).show();
                } else if (e.getX() == 4) {
                    Toast.makeText(getActivity(), "the value of level D: " + String.valueOf(e.getY()), Toast.LENGTH_SHORT).show();
                } else if (e.getX() == 5) {
                    Toast.makeText(getActivity(), "the value of level E: " + String.valueOf(e.getY()), Toast.LENGTH_SHORT).show();
                } else if (e.getX() == 6) {
                    Toast.makeText(getActivity(), "the value of level F: " + String.valueOf(e.getY()), Toast.LENGTH_SHORT).show();
                } else if (e.getX() == 7) {
                    Toast.makeText(getActivity(), "the value of level G: " + String.valueOf(e.getY()), Toast.LENGTH_SHORT).show();
                } else if (e.getX() == 8) {
                    Toast.makeText(getActivity(), "the value of level H: " + String.valueOf(e.getY()), Toast.LENGTH_SHORT).show();
                } else if (e.getX() == 9) {
                    Toast.makeText(getActivity(), "the value of level I: " + String.valueOf(e.getY()), Toast.LENGTH_SHORT).show();
                } else if (e.getX() == 10) {
                    Toast.makeText(getActivity(), "the value of level J: " + String.valueOf(e.getY()), Toast.LENGTH_SHORT).show();
                } else if (e.getX() == 11) {
                    Toast.makeText(getActivity(), "the value of level K: " + String.valueOf(e.getY()), Toast.LENGTH_SHORT).show();
                } else if (e.getX() == 12) {
                    Toast.makeText(getActivity(), "the value of level L: " + String.valueOf(e.getY()), Toast.LENGTH_SHORT).show();
                } else if (e.getX() == 13) {
                    Toast.makeText(getActivity(), "the value of level M: " + String.valueOf(e.getY()), Toast.LENGTH_SHORT).show();
                } else if (e.getX() == 14) {
                    Toast.makeText(getActivity(), "the value of level O: " + String.valueOf(e.getY()), Toast.LENGTH_SHORT).show();
                } else if (e.getX() == 15) {
                    Toast.makeText(getActivity(), "the value of level Q: " + String.valueOf(e.getY()), Toast.LENGTH_SHORT).show();
                } else if (e.getX() == 16) {
                    Toast.makeText(getActivity(), "the value of level R: " + String.valueOf(e.getY()), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onNothingSelected() {

                //TODO

            }
        });


    }

    void getColors() {
        int counter = 0;

        for (int color : ColorTemplate.JOYFUL_COLORS) {
            colors[counter] = color;
            counter++;
        }

        for (int color : ColorTemplate.MATERIAL_COLORS) {
            colors[counter] = color;
            counter++;
        }
        for (int color : ColorTemplate.COLORFUL_COLORS) {
            colors[counter] = color;
            counter++;
        }
        for (int color : ColorTemplate.PASTEL_COLORS) {
            colors[counter] = color;
            counter++;
        }
    }

    void getPieChart_Language() {

        float countC20 = 0, countPyPy = 0, countC1764 = 0, countRust = 0, countC17 = 0, countClang17 = 0, countClang20 = 0, countJava17 = 0, countPython3 = 0, countC14 = 0, countC11 = 0, countKotlin = 0, countText = 0, countQ = 0, countpypy3 = 0;
        float gnucpp = 0;

        for (int i = 0; i < size; i++) {
            String language = submissionList.get(0).getResult().get(i).getProgrammingLanguage().toString();

            if (language.equals("GNU C++17")) {
                countC17++;
            } else if (language.equals("GNU C++20 (64)")) {
                countC20++;

            } else if (language.equals("PyPy 3-64")) {
                countPyPy++;

            } else if (language.equals("GNU C++17 (64)")) {
                countC1764++;

            } else if (language.equals("Rust 2021")) {
                countRust++;

            } else if (language.equals("Clang++17 Diagnostics")) {
                countClang17++;

            } else if (language.equals("Clang++20 Diagnostics")) {
                countClang20++;

            } else if (language.equals("Java 17")) {
                countJava17++;

            } else if (language.equals("Python 3")) {
                countPython3++;

            } else if (language.equals("GNU C++14")) {
                countC14++;

            } else if (language.equals("GNU C11")) {
                countC11++;

            } else if (language.equals("Kotlin 1.4")) {
                countKotlin++;

            } else if (language.equals("Text")) {
                countText++;

            } else if (language.equals("Q#")) {
                countQ++;

            } else if (language.equals("PyPy 3")) {
                countpypy3++;

            } else if (language.equals("GNU C++")) {
                gnucpp++;

            }

        }

        ArrayList<PieEntry> records = new ArrayList<>();
        Log.d("cont", "the vale is" + countC11);
        records.add(new PieEntry(countC17, "GNU C++17"));
        records.add(new PieEntry(countC20, "GNU C++20 (64)"));
        records.add(new PieEntry(countPyPy, "PyPy 3-64"));
        records.add(new PieEntry(countC1764, "GNU C++17 (64)"));
        records.add(new PieEntry(countRust, "Rust 2021"));
        records.add(new PieEntry(countClang17, "Clang++17 "));
        records.add(new PieEntry(countClang20, "Clang++20 "));
        records.add(new PieEntry(countJava17, "Java 17"));
        records.add(new PieEntry(countPython3, "Python 3"));
        records.add(new PieEntry(countC14, "GNU C++14"));
        records.add(new PieEntry(countC11, "GNU C11"));
        records.add(new PieEntry(countKotlin, "Kotlin 1.4"));
        records.add(new PieEntry(countText, "Text"));
        records.add(new PieEntry(countQ, "Q#"));
        records.add(new PieEntry(countPyPy, "PyPy 3"));
        records.add(new PieEntry(gnucpp, "GNU C++"));

        PieDataSet dataSet = new PieDataSet(records, "Languages");

        dataSet.setColors(colors);

        dataSet.setValueTextColor(Color.BLACK);
        dataSet.setValueTextSize(17f);


        PieData pieData = new PieData(dataSet);
        infoBindingXml.pieChartTopics.setData(pieData);
        infoBindingXml.pieChartTopics.getDescription().setEnabled(false);
        infoBindingXml.pieChartTopics.setCenterText("Used Languages");
        infoBindingXml.pieChartTopics.setElevation(4.5f);
        infoBindingXml.pieChartTopics.setEntryLabelTextSize(17f);
        infoBindingXml.pieChartTopics.setHoleColor(Color.BLACK);
        infoBindingXml.pieChartTopics.setCenterTextColor(Color.WHITE);
        Legend legend = infoBindingXml.pieChartTopics.getLegend();
        legend.setOrientation(Legend.LegendOrientation.VERTICAL);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        dataSet.setDrawValues(false);
        infoBindingXml.pieChartTopics.setDrawSliceText(false);
        infoBindingXml.pieChartTopics.invalidate();
        infoBindingXml.pieChartTopics.animate();
        infoBindingXml.pieChartTopics.setHighlightPerTapEnabled(true);


        infoBindingXml.pieChartTopics.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {


                Toast.makeText(getActivity().getApplicationContext(), " value = " + String.valueOf(e.getY()), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected() {

                //TODO

            }
        });
    }

}