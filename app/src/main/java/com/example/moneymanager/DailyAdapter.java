package com.example.moneymanager;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.List;

public class DailyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private static final String TAG="DailyAdapter";
    private List<DataClass> dataClassList;
    public DailyAdapter(Context context, List<DataClass> dataClassList){
        this.context=context;
        this.dataClassList=dataClassList;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.daily_tab_contents,parent,false);
        return new DailyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Log.d(TAG,"??????"+"inside onBindviewHolder");
        DataClass dc=dataClassList.get(position);
        String str="dd-MMM-yyyy";
        SimpleDateFormat sdf=new SimpleDateFormat(str);
        String dateString=sdf.format(dc.getDate());
        DailyViewHolder viewHolder=(DailyViewHolder) holder;
        viewHolder.dateText.setText(dateString);
        viewHolder.catExpText.setText(String.valueOf(dc.getCatExp()));
        viewHolder.notesText.setText(dc.getNotes());
        viewHolder.incText.setText(String.valueOf(dc.getIncome()));
        viewHolder.catText.setText(dc.getCategory());
        viewHolder.expText.setText(String.valueOf(dc.getCatExp()));


    }

    @Override
    public int getItemCount() {
        return dataClassList.size();
    }
    public static class DailyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView catText,incText,expText,notesText,catExpText,monthYearText,dateText;
        LinearLayout upperLL,lowerLL;
        public DailyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            upperLL=itemView.findViewById(R.id.daily_tab_contents_upper_linLay);
            lowerLL=itemView.findViewById(R.id.daily_tab_contents_lower_linLay);
            catText=itemView.findViewById(R.id.category_text_dailyTab);
            incText=itemView.findViewById(R.id.income_text_dailyTab);
            expText=itemView.findViewById(R.id.expense_dailyTab);
            notesText=itemView.findViewById(R.id.notes_dailyTab);
            catExpText=itemView.findViewById(R.id.category_expense_dailyTab);
            monthYearText=itemView.findViewById(R.id.month_year_text_dailyTab);
            dateText=itemView.findViewById(R.id.date_text_dailyTab);
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    String text=notesText.getText().toString();
                    Log.d(TAG,"?????/"+"Long clicked"+text);
                    WriteToDB.getDB(catExpText.getContext()).deleteData(text);
                    return true;
                }
            });
        }

        @Override
        public void onClick(View view) {
//            Log.d(TAG,"?????/"+" clicked"+view.getContext());
//            WriteToDB.getDB(view.getContext()).deleteData("Sergio");
        }
    }
}

