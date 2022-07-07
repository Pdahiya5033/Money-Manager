package com.example.moneymanager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.CalendarViewHolder> {
    private final ArrayList<String> daysOfMonth;
    public CalendarAdapter(ArrayList<String> daysOfMonth){
        this.daysOfMonth=daysOfMonth;
    }

    @NonNull
    @Override
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.calendar_contents,parent,false);

        return new CalendarViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return daysOfMonth.size();
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarViewHolder holder, int position) {
        holder.dayOfMonth.setText(daysOfMonth.get(position));
    }

    public static class CalendarViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView dayOfMonth;
        public CalendarViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            dayOfMonth=itemView.findViewById(R.id.calendar_daily_text);

        }

        @Override
        public void onClick(View view) {

        }
    }
}
