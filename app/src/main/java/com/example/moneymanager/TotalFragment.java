package com.example.moneymanager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class TotalFragment extends Fragment {
    private RecyclerView mRecyclerView;

    public static TotalFragment newInstance(){
        return new TotalFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.total_tab,viewGroup,false);
        mRecyclerView=view.findViewById(R.id.total_tab_recView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }
}
