package com.example.moneymanager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MonthlyFragment extends Fragment {
    private RecyclerView mRecyclerView;

    public static MonthlyFragment newInstance(){
        return new MonthlyFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup,Bundle savedInstanceState){
        View view=layoutInflater.inflate(R.layout.monthly_tab,viewGroup,false);
        mRecyclerView=view.findViewById(R.id.monthly_tab_recView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }
}
