package com.example.imageview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.imageview.databinding.ActivityMainBinding;

import viewmodels.InfoListViewModel;
import viewmodels.RecentListViewModel;
import viewmodels.StatsListViewModel;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding mainXml;

    InfoListViewModel infoListViewModel;
    RecentListViewModel recentListViewModel;
    StatsListViewModel statsListViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainXml = DataBindingUtil.setContentView(this, R.layout.activity_main);
        infoListViewModel= new InfoListViewModel();
        recentListViewModel=new RecentListViewModel();
        statsListViewModel=new StatsListViewModel();


        mainXml.btnsearch.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {


                String name = "";
                name = mainXml.edtName.getText().toString();

                if (name.equals("")) {
                    Toast.makeText(getApplicationContext(), "Enter Details", Toast.LENGTH_LONG).show();
                } else {


                    Intent intent = new Intent(MainActivity.this, MainScreen.class);
                    infoListViewModel.getUserName(name);
                    recentListViewModel.getUserName(name);
                    statsListViewModel.getUserName(name);
                    startActivity(intent);

                }


            }
        });


    }
}