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
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG="MainActivity";
    private ViewPager mViewPager;
    private FloatingActionButton addBtn,chatBtn;
    private boolean calendarClicked=false;
    private List<DataClass> dataClassList;
    private LocalDate localDate;
    private ImageView pieImage;
    private TextView dailyText,calendarText,weeklyText,monthlyText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        localDate=LocalDate.now();
        dataClassList=WriteToDB.getDB(getApplicationContext()).getDCObjDB();
        getSupportActionBar().hide();
        chatBtn=findViewById(R.id.chat_btn);
        addBtn=findViewById(R.id.addButton);
        pieImage=findViewById(R.id.pie_img);
        mViewPager=findViewById(R.id.homesrc_viewpager);
        dailyText=findViewById(R.id.daily_text_activity_main);
        weeklyText=findViewById(R.id.weekly_text_activity_main);
        calendarText=findViewById(R.id.calendar_text_activity_main);
        monthlyText=findViewById(R.id.monthly_text_activity_main);
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
                if(calendarClicked){
                    List<Fragment> fragments=getSupportFragmentManager().getFragments();
                    if(fragments.size()>1){
                        final Fragment currentFragment=getSupportFragmentManager().findFragmentById(R.id.fragment_container);
                        if(currentFragment!=null){
                            getSupportFragmentManager().beginTransaction().remove(currentFragment).commit();
                            getSupportFragmentManager().popBackStack();
                        }
                        else{
                            MainActivity.super.onBackPressed();
                        }
                    }
                }
                calendarClicked=false;
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
                calendarClicked=true;
                mViewPager.removeAllViews();
                Log.d(TAG,"calendar clicked");
                FragmentManager fm=getSupportFragmentManager();
                Fragment fragment=CalendarFragment.newInstance();
                fm.beginTransaction().replace(R.id.fragment_container,fragment)
                        .addToBackStack(null).commit();
            }
        });
        weeklyText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm=getSupportFragmentManager();
                if(calendarClicked){
                    List<Fragment> fragments=getSupportFragmentManager().getFragments();
                    if(fragments.size()>1){
                        final Fragment currentFragment=getSupportFragmentManager().findFragmentById(R.id.fragment_container);
                        if(currentFragment!=null){
                            getSupportFragmentManager().beginTransaction().remove(currentFragment).commit();
                            getSupportFragmentManager().popBackStack();
                        }
                        else{
                            MainActivity.super.onBackPressed();
                        }
                    }
                }
                calendarClicked=false;
                mViewPager.setAdapter(new FragmentStatePagerAdapter(fm) {
                    @Override
                    public int getCount() {
                        return 12;
                    }
                    @NonNull
                    @Override
                    public Fragment getItem(int position) {
                        String[] strings={"01/02/2021"};
                        List<String> list=WriteToDB.getDB(getApplicationContext()).getWeeksCont(strings);
                        for(int i=0;i<list.size();i++)
                            Log.d(TAG,";;;;;"+list.get(i));
                        Month month=Month.of(position+1);
                        LocalDate ld=LocalDate.of(localDate.getYear(),month,1);
                        return WeeklyFragment.newInstance(ld);
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

        Log.d(TAG,"inside onCreate Main activity");
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 //Fragment fr=getSupportFragmentManager().getFragments().get(index);
                //Log.d(TAG,">>>."+fr);
                Intent intent=DataInputActivity.newIntent(MainActivity.this);
                startActivity(intent);

            }
        });
        chatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=ChatsActivity.newIntent(MainActivity.this);
                startActivity(intent);
            }
        });
        pieImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm=getSupportFragmentManager();
                if(calendarClicked){
                    List<Fragment> fragments=getSupportFragmentManager().getFragments();
                    if(fragments.size()>1){
                        final Fragment currentFragment=getSupportFragmentManager().findFragmentById(R.id.fragment_container);
                        if(currentFragment!=null){
                            getSupportFragmentManager().beginTransaction().remove(currentFragment).commit();
                            getSupportFragmentManager().popBackStack();
                        }
                        else{
                            MainActivity.super.onBackPressed();
                        }
                    }
                }
                calendarClicked=false;
                Fragment fragment=ChartFragment.newInstance();
                fm.beginTransaction().add(R.id.fragment_container,fragment)
                        .addToBackStack(null).commit();
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