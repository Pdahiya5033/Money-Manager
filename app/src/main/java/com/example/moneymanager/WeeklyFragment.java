package com.example.moneymanager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class WeeklyFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private WeeklyMonthlyAdapter weeklyMonthlyAdapter;
    private List<DataClass> listObj=new ArrayList<>();
    private static DataClass dataClass;
    public static WeeklyFragment newInstance(DataClass dc){
        dataClass=dc;
        return new WeeklyFragment();
    }
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup,Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.weekly_tab,viewGroup,false);
        listObj.add(dataClass);
        mRecyclerView=view.findViewById(R.id.weekly_tab_recView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        weeklyMonthlyAdapter=new WeeklyMonthlyAdapter(getContext(),listObj);
        mRecyclerView.setAdapter(weeklyMonthlyAdapter);
        return view;
    }
}
