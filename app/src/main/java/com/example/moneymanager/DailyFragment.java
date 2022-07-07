package com.example.moneymanager;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

public class DailyFragment extends Fragment {
    private static final String TAG="DailyFragment";
    private RecyclerView mRecyclerView;
    public DailyAdapter dailyAdapter;
    private static List<DataClass> dataList=new ArrayList<>();
    public static DailyFragment newInstance(List<DataClass> dataClassList){
        dataList=dataClassList;
        return new DailyFragment();
    }
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState){
        Log.d(TAG,">>>"+"inside on create view of daily");
        View view=inflater.inflate(R.layout.daily_tab,viewGroup,false);
        //dailyVPAdapter=new DailyVPAdapter(dataList);
//        viewPager.setAdapter(dailyAdapter);
//        viewPager.setClipToPadding(false);
//        viewPager.setClipChildren(false);
//        viewPager.setOffscreenPageLimit(2);
//        viewPager.getChildAt(0).setOverScrollMode(View.OVER_SCROLL_NEVER);
        mRecyclerView=view.findViewById(R.id.daily_tab_recView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        dailyAdapter=new DailyAdapter(getContext(),dataList);
        mRecyclerView.setAdapter(dailyAdapter);

        return view;
    }
    @Override
    public void onStart(){
        super.onStart();
        Log.d(TAG,"inside on start");
    }
    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        Log.d(TAG,"inside on attach");
    }
    @Override
    public void onStop(){
        super.onStop();
        Log.d(TAG,"inside on stop");
    }
    @Override
    public void onResume(){
        super.onResume();
        Log.d(TAG,"inside on resume");
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(TAG,"iNSIDE on destroy");
    }
    @Override
    public void onDestroyView(){
        super.onDestroyView();
        Log.d(TAG,"iNSIDE on destroy view");
    }
    @Override
    public void onPause(){
        super.onPause();
        Log.d(TAG,"inside on pAUSE");
    }
    @Override
    public void onDetach(){
        super.onDetach();
        Log.d(TAG,"iNSIDE on detach");
    }

}
