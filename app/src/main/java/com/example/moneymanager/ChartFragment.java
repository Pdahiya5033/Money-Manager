package com.example.moneymanager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;

import java.util.ArrayList;
import java.util.List;

public class ChartFragment extends Fragment {
    private AnyChartView anyChartView;
    public static ChartFragment newInstance(){
        return new ChartFragment();
    }
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle savedInstanceState){
        View view=layoutInflater.inflate(R.layout.pie_chart_frag,viewGroup,false);
        anyChartView=view.findViewById(R.id.pie_chart_id);
        List<DataClass> dataClassList=WriteToDB.getDB(getContext()).getDCObjDB();
        List<String> category=new ArrayList<>();
        List<Float> expense=new ArrayList<>();
        for(int i=0;i<dataClassList.size();i++){
            for(int j=0;j<dataClassList.get(i).categorySize();j++){
                category.add(dataClassList.get(i).getCategory(j));
                expense.add(dataClassList.get(i).getCatExp(j));
            }
        }
        setUpPieChart(category,expense);
        return view;
    }
    public void setUpPieChart(List<String> list1,List<Float> list2){
        Pie pie= AnyChart.pie3d();
        List<DataEntry> dataEntries=new ArrayList<>();
        for(int i=0;i<list1.size();i++){
            dataEntries.add(new ValueDataEntry(list1.get(i), list2.get(i)));
        }
        pie.data(dataEntries);
        anyChartView.setChart(pie);
    }
}
