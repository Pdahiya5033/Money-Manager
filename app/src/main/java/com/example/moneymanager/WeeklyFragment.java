package com.example.moneymanager;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class WeeklyFragment extends Fragment {
    private static final String TAG="WeeklyFragment";
    private RecyclerView mRecyclerView;
    private WeeklyMonthlyAdapter weeklyMonthlyAdapter;
    private List<DataClass> listObj=new ArrayList<>();
    private List<String> weeksInMonth=new ArrayList<>();
    private static LocalDate selectedDate;
    private static DataClass dataClass;
    public static WeeklyFragment newInstance(LocalDate localDate){
        selectedDate=localDate;
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


        weeksInMonth=getWeeksList(selectedDate);
        mRecyclerView=view.findViewById(R.id.weekly_tab_recView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        weeklyMonthlyAdapter=new WeeklyMonthlyAdapter(getContext(),listObj,weeksInMonth);
        mRecyclerView.setAdapter(weeklyMonthlyAdapter);
        return view;
    }
    public List<String> getWeeksList(LocalDate localDate){
        Calendar calendar=Calendar.getInstance();
        calendar.set(Calendar.YEAR,localDate.getYear());
        calendar.set(Calendar.DAY_OF_MONTH,1);
        calendar.set(Calendar.MONTH,localDate.getMonth().getValue());
        int numOfWeeks=calendar.getActualMaximum(Calendar.WEEK_OF_MONTH)+1;
        Log.d(TAG,"..//"+numOfWeeks);
        List<String> weeksList=new ArrayList<>();
        for(int i=0;i<numOfWeeks;i++){
            weeksList.add(daysInWeekArray(localDate.plusWeeks(i)));
        }
        for(int i=0;i<weeksList.size();i++)
            Log.d(TAG,"////"+weeksList.get(i));
        return weeksList;
    }
    public static String formattedDate(LocalDate date)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM");
        return date.format(formatter);
    }
    public static String monthYearFromDate(LocalDate date)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        return date.format(formatter);
    }
//    public static ArrayList<LocalDate> daysInMonthArray(LocalDate date) {
//        ArrayList<LocalDate> daysInMonthArray = new ArrayList<>();
//        YearMonth yearMonth = YearMonth.from(date);
//
//        int daysInMonth = yearMonth.lengthOfMonth();
//
//        LocalDate firstOfMonth = selectedDate.withDayOfMonth(1);
//        int dayOfWeek = firstOfMonth.getDayOfWeek().getValue();
//
//        for(int i = 1; i <= 42; i++)
//        {
//            if(i <= dayOfWeek || i > daysInMonth + dayOfWeek)
//                daysInMonthArray.add(null);
//            else
//                daysInMonthArray.add(LocalDate.of(selectedDate.getYear(),selectedDate.getMonth(),i - dayOfWeek));
//        }
//        return  daysInMonthArray;
//    }
    public static String daysInWeekArray(LocalDate selectedDate) {
        ArrayList<LocalDate> days = new ArrayList<>();
        String str="";
        LocalDate current = sundayForDate(selectedDate);
        LocalDate endDate = current.plusWeeks(1);
        LocalDate start;
        LocalDate end;
        while (current.isBefore(endDate)) {
            days.add(current);
            current = current.plusDays(1);
        }
        start=days.get(0);
        end=days.get(days.size()-1);
        str=formattedDate(start)+" "+formattedDate(end);
        return str;
    }
    private static LocalDate sundayForDate(LocalDate current) {
        LocalDate oneWeekAgo = current.minusWeeks(1);

        while (current.isAfter(oneWeekAgo))
        {
            if(current.getDayOfWeek() == DayOfWeek.SUNDAY)
                return current;

            current = current.minusDays(1);
        }

        return null;
    }
}
