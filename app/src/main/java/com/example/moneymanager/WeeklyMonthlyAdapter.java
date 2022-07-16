package com.example.moneymanager;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.util.List;

public class WeeklyMonthlyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG="WeeklyMonthlyAdapter";
    private Context context;
    private List<String> weeksInMonth;
    private List<DataClass> list;
    public WeeklyMonthlyAdapter(Context context, List<DataClass> list, List<String> weeksInMonth){
        this.context=context;
        this.list=list;
        this.weeksInMonth=weeksInMonth;
        Log.d(TAG,"///"+weeksInMonth.size());
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
        //DataClass dataClass=list.get(position);
        String weekString=weeksInMonth.get(position);
        WeeklyMonthlyViewHolder weeklyMonthlyViewHolder=(WeeklyMonthlyViewHolder) holder;
        weeklyMonthlyViewHolder.weekDays.setText(weekString);
        weeklyMonthlyViewHolder.weeklyIncome.setText("cbzx");
        weeklyMonthlyViewHolder.weeklyExpense.setText("zcx");
    }

    @Override
    public int getItemCount() {
        return weeksInMonth.size();
    }
    public static class WeeklyMonthlyViewHolder extends RecyclerView.ViewHolder{
        TextView weekDays,weeklyIncome,weeklyExpense;
        public WeeklyMonthlyViewHolder(@NonNull View itemView) {
            super(itemView);
            weeklyExpense=itemView.findViewById(R.id.expense_in_weekly);
            weeklyIncome=itemView.findViewById(R.id.income_in_weekly);
            weekDays=itemView.findViewById(R.id.weeks_in_weekly);
        }
    }
}
