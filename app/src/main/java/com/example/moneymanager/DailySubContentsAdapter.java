package com.example.moneymanager;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DailySubContentsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private static final String TAG="DailySubContentsAdapter";
    private Context context;
    private DataClass dataClass;
    public DailySubContentsAdapter(Context context,DataClass dataClass){
        Log.d(TAG,"??????"+"inside constructor");
        this.dataClass=dataClass;
        this.context=context;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG,"???"+"INSIDE ON CREATE VIEW HOLDER"+parent.getContext());
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.daily_tab_subcontents,parent,false);
        return new DailySubContentsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Log.d(TAG,"???"+"INSIDE ON BIND VIEW HOLDER"+position);
        DailySubContentsViewHolder dailySubContentsViewHolder=(DailySubContentsViewHolder) holder;
        dailySubContentsViewHolder.catText.setText(dataClass.getCategory(position));
        dailySubContentsViewHolder.noteText.setText(dataClass.getNotes(position));
        dailySubContentsViewHolder.expenseText.setText(String.valueOf(dataClass.getCatExp(position)));
    }

    @Override
    public int getItemCount() {
        Log.d(TAG,"<<<<"+dataClass.catExpSize());
        return dataClass.catExpSize();
    }

    public static class DailySubContentsViewHolder extends RecyclerView.ViewHolder{
        TextView catText,noteText,expenseText;
        public DailySubContentsViewHolder(@NonNull View itemView) {
            super(itemView);
            Log.d(TAG,"???"+"INSIDE ON DAILY SUB CONTENTS VIEW HOLDER");
            catText=itemView.findViewById(R.id.category_text_dailyTab);
            noteText=itemView.findViewById(R.id.notes_dailyTab);
            expenseText=itemView.findViewById(R.id.category_expense_dailyTab);
        }
    }
}
