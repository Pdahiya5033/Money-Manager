package com.example.moneymanager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CalendarFragment extends Fragment {
    private RecyclerView recyclerView;
    private Button prBtn,neBtn;
    private TextView monthYearText;
    private LocalDate selectedDate;
    private ArrayList<String> daysList;
    public static CalendarFragment newInstance(){
        return new CalendarFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        selectedDate=LocalDate.now();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.calendar_fragment,parent,false);
        recyclerView=view.findViewById(R.id.calendar_rec_view);
        monthYearText=view.findViewById(R.id.monthYearCalTextView);
        neBtn=view.findViewById(R.id.next_calendar_btn);
        prBtn=view.findViewById(R.id.previous_calendar_btn);
        setMonthView();
        prBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedDate=selectedDate.minusMonths(1);
                setMonthView();
            }
        });
        neBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedDate=selectedDate.plusMonths(1);
                setMonthView();
            }
        });
        return view;
    }
    private ArrayList<String> daysInMonth(LocalDate date){
        ArrayList<String> daysInMonth=new ArrayList<>();
        YearMonth yearMonth=YearMonth.from(date);
        int daysNum= yearMonth.lengthOfMonth();
        LocalDate firstDay=selectedDate.withDayOfMonth(1);
        int dayOfWeek=firstDay.getDayOfWeek().getValue();
        for(int i=1;i<=42;i++){
            if(i<=dayOfWeek||i>daysNum+dayOfWeek){
                daysInMonth.add("");
            }
            else{
                daysInMonth.add(String.valueOf(i-dayOfWeek));
            }
        }
        return daysInMonth;
    }
    private void setMonthView(){
        monthYearText.setText(getMonthYear(selectedDate));
        daysList=daysInMonth(selectedDate);
        CalendarAdapter calendarAdapter=new CalendarAdapter(daysList);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),7));
        recyclerView.setAdapter(calendarAdapter);
    }
    private String getMonthYear(LocalDate date){
        DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("MMMM yyyy");
        return date.format(dateTimeFormatter);
    }
}
