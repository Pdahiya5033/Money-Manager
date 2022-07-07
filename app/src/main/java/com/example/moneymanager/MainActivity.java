package com.example.moneymanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG="MainActivity";
    private ViewPager mViewPager;
    private FloatingActionButton addBtn;
    private int index=0;
    private List<DataClass> dataClassList;
    private TextView dailyText,calendarText,weeklyText,monthlyText,totalText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataClassList=WriteToDB.getDB(getApplicationContext()).getDCObjDB();
        getSupportActionBar().hide();
        addBtn=findViewById(R.id.addButton);
        mViewPager=(ViewPager) findViewById(R.id.homesrc_viewpager);
        dailyText=findViewById(R.id.daily_text_activity_main);
        weeklyText=findViewById(R.id.weekly_text_activity_main);
        calendarText=findViewById(R.id.calendar_text_activity_main);
        monthlyText=findViewById(R.id.monthly_text_activity_main);
        totalText=findViewById(R.id.total_text_activity_main);
        if(savedInstanceState==null){
            FragmentManager fm=getSupportFragmentManager();
            mViewPager.setAdapter(new FragmentStatePagerAdapter(fm) {
                @Override
                public int getCount() {
                    return dataClassList.size();
                }
                @NonNull
                @Override
                public Fragment getItem(int position) {
                    return DailyFragment.newInstance(dataClassList);
                }

            });

        }
        dailyText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG,"daily clicked");
                FragmentManager fm=getSupportFragmentManager();
                mViewPager.setAdapter(new FragmentStatePagerAdapter(fm) {
                    @Override
                    public int getCount() {
                        return dataClassList.size();
                    }
                    @NonNull
                    @Override
                    public Fragment getItem(int position) {
                        return DailyFragment.newInstance(dataClassList);
                    }

                });
            }
        });
        calendarText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG,"calendar clicked");

            }
        });
        weeklyText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm=getSupportFragmentManager();
                mViewPager.setAdapter(new FragmentStatePagerAdapter(fm) {
                    @Override
                    public int getCount() {
                        return dataClassList.size();
                    }

                    @NonNull
                    @Override
                    public Fragment getItem(int position) {
                        return WeeklyFragment.newInstance(dataClassList.get(position));
                    }
                });
            }
        });
        monthlyText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm=getSupportFragmentManager();
                Fragment fragment=MonthlyFragment.newInstance();
                fm.beginTransaction().replace(R.id.fragment_container,fragment,"daily").setReorderingAllowed(true)
                        .addToBackStack(null).commit();
            }
        });
        totalText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm=getSupportFragmentManager();
                Fragment fragment=TotalFragment.newInstance();
                fm.beginTransaction().replace(R.id.fragment_container,fragment,"daily").setReorderingAllowed(true)
                        .addToBackStack(null).commit();
            }
        });
        Log.d(TAG,"inside onCreate Main activity");
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 Fragment fr=getSupportFragmentManager().getFragments().get(index);
                Log.d(TAG,">>>."+fr);
                Intent intent=DataInputActivity.newIntent(MainActivity.this);
                startActivity(intent);

            }
        });
    }
    @Override
    public void onResume(){
        super.onResume();
        Log.d(TAG,"inside onResume Main activity");

    }
    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG,"inside onStart Main activity");
    }
    @Override
    public void onStop(){
        super.onStop();
        Log.d(TAG,"inside on stop");
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(TAG,"iNSIDE on destroy");
    }
    @Override
    public void onPause(){
        super.onPause();
        Log.d(TAG,"inside on pAUSE");
    }

}