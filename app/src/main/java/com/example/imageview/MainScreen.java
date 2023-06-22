package com.example.imageview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.imageview.Fragments.ContestsFragment;
import com.example.imageview.Fragments.UserInfoFragment;
import com.example.imageview.Fragments.UserRecentFragment;
import com.example.imageview.Fragments.UserStatusFragment;
import com.example.imageview.databinding.ActivityMainScreenBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import viewmodels.InfoListViewModel;

public class MainScreen extends AppCompatActivity {
    //    RecyclerView recyclerviewImage;
//    ArrayList<Example> imagelist;
//    InfoListViewModel listViewModel;
//    ImageListAdapter adapter;

    ActivityMainScreenBinding userInfoXml;

    InfoListViewModel infoListViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        userInfoXml = DataBindingUtil.setContentView(this, R.layout.activity_main_screen);
        setContentView(userInfoXml.getRoot());

        userInfoXml.bottomNavigation.setDarkIcon(true);
        userInfoXml.bottomNavigation.setCircleColor(getColor(R.color.green));
        String name = getIntent().getStringExtra("EnteredName");
//        infoListViewModel.getUserName(name);

        userInfoXml.bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                int id = item.getItemId();

                if (id == R.id.userStats) {

                    loadFragment(new UserStatusFragment(), false);
                } else if (id == R.id.userRecent) {
                    loadFragment(new UserRecentFragment(), false);

                } else if (id == R.id.userUpcommingCompetition) {
                    loadFragment(new ContestsFragment(), false);
                } else {
                    loadFragment(new UserInfoFragment(), true);
                }

                return true;

            }
        });
        userInfoXml.bottomNavigation.setSelectedItemId(R.id.userHome);


//        recyclerviewImage=findViewById(R.id.recyclerviewImage);
//
//        recyclerviewImage.setLayoutManager(new LinearLayoutManager(this));
//
//        adapter=new ImageListAdapter(imagelist);
//        recyclerviewImage.setAdapter(adapter);
//
//
//        listViewModel= new ViewModelProvider(this).get(ImageListViewModel.class);
//        listViewModel.getImageListObserver().observe(this, new Observer<ArrayList<Example>>() {
//            @Override
//            public void onChanged(ArrayList<Example> examples) {
//
//                if (examples!=null){
//                    imagelist=examples;
//                    adapter.updateimagelist(examples);
//                }
//
//            }
//        });
//
//
//
//        listViewModel.makeApiCall();


    }

    public void loadFragment(Fragment fragment, boolean flag) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        if (flag) {
            ft.replace(R.id.frameContainer, fragment);
        } else {
            ft.replace(R.id.frameContainer, fragment);
        }

        ft.commit();
    }
}