package com.example.moneymanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WeeklyMonthlyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG="WeeklyMonthlyAdapter";
    private Context context;
    private List<DataClass> list;
    public WeeklyMonthlyAdapter(Context context, List<DataClass> list){
        this.context=context;
        this.list=list;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.weekly_monthly_tab_contents,parent,false);
        return new WeeklyMonthlyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        DataClass dataClass=list.get(position);
        WeeklyMonthlyViewHolder weeklyMonthlyViewHolder=(WeeklyMonthlyViewHolder) holder;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public static class WeeklyMonthlyViewHolder extends RecyclerView.ViewHolder{
        TextView weekDays,weeklyIncome,weeklyExpense;
        public WeeklyMonthlyViewHolder(@NonNull View itemView) {
            super(itemView);
            weeklyExpense=itemView.findViewById(R.id.weekly_tab_expense);
            weeklyIncome=itemView.findViewById(R.id.weekly_tab_income);
            weekDays=itemView.findViewById(R.id.weekly_tab_days);
        }
    }
}
