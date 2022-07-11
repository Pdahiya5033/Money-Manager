package com.example.moneymanager;

import android.app.ActionBar;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.tabs.TabLayout;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataInputFragment extends Fragment {
    private DataClass dataObj;
    private Date date;
    private String dateStr;
    private static final int REQUEST_DATE=0;
    private static final String SHOW_DATE="show_date";
    private static final String TAG="DataInputFragment";
    private ArrayAdapter<CharSequence> accDropAdapter;
    private ArrayAdapter<CharSequence> catDropAdapter;
    private Spinner catSpinner;
    private Spinner accSpinner;
    private TextView dateText;
    private EditText catExp,note;
    private Button saveBtn,continueBtn,incomeBtn,transferBtn,expenseBtn;
    private TextView accTextView,catTextView;
    private List<DataClass> dataClassList;
    public static DataInputFragment newInstance(){
        return new DataInputFragment();
    }
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Date mDate=new Date(2020,0,1);
//        dataObj=new DataClass();
//        dataObj.setDate(mDate);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup,Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.input_fragment,viewGroup,false);
        accTextView=view.findViewById(R.id.acc_input_textView);
        catTextView=view.findViewById(R.id.category_input_textView);
        dateText=view.findViewById(R.id.date_input_editText);
        catExp=view.findViewById(R.id.amount_input_editText);
        note=view.findViewById(R.id.note_input_editText);
        accSpinner=view.findViewById(R.id.account_dropdown);
        catSpinner=view.findViewById(R.id.category_dropdown);
        incomeBtn=view.findViewById(R.id.input_income_btn);
        expenseBtn=view.findViewById(R.id.input_expense_btn);
        transferBtn=view.findViewById(R.id.input_transfer_btn);
        saveBtn=view.findViewById(R.id.save_btn);
        continueBtn=view.findViewById(R.id.continue_btn);
        accDropAdapter=ArrayAdapter.createFromResource(getContext(),R.array.account,
                android.R.layout.simple_spinner_item);
        accDropAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        catDropAdapter=ArrayAdapter.createFromResource(getContext(), R.array.category_expense,
                android.R.layout.simple_spinner_item);
        accSpinner.setAdapter(accDropAdapter);
        catSpinner.setAdapter(catDropAdapter);
        String str=accSpinner.getSelectedItem().toString();
        Log.d(TAG,">>>>>"+str);
        dateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG,"date clicked");
                FragmentManager fragmentManager=getFragmentManager();
                DatePickerFragment datePickerFragment=DatePickerFragment.newInstance(new Date(2000,01,01));
                datePickerFragment.setTargetFragment(DataInputFragment.this,REQUEST_DATE);
                datePickerFragment.show(fragmentManager,SHOW_DATE);
            }
        });
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataObj=retDataObj(dateStr);
                Log.d(TAG,"<<<<<"+dataObj);
                if(dataObj==null){
                    List<String> catList=new ArrayList<>();
                    List<String> noteList=new ArrayList<>();
                    List<Float> catExpList=new ArrayList<>();
                    List<String> incomeCatList=new ArrayList<>();
                    List<Float> income=new ArrayList<>();
                    dataObj=new DataClass();
                    catList.add(catSpinner.getSelectedItem().toString());
                    noteList.add(note.getText().toString());
                    catExpList.add(Float.parseFloat(catExp.getText().toString()));
                    dataObj.setDate(dateStr);
                    Log.d(TAG,">>>"+dataObj.getDate());
                    dataObj.setCategory(catList);
                    dataObj.setNotes(noteList);
                    dataObj.setIncome(income);
                    dataObj.setAccount(accSpinner.getSelectedItem().toString());
                    dataObj.setCatExp(catExpList);
                    dataObj.setIncCat(incomeCatList);
                    WriteToDB.getDB(getActivity()).addData(dataObj);
                }
                else{
                    DataClass dc=new DataClass();
                    dc.setDate(dataObj.getDate());
                    List<String> incCatList=new ArrayList<>();
                    List<String> catList=dataObj.retcatList();
                    List<String> noteList=dataObj.retNoteList();
                    List<Float> catExpList=dataObj.retCatExpList();
                    List<Float> incomeList=new ArrayList<>();
                    catList.add(catSpinner.getSelectedItem().toString());
                    noteList.add(note.getText().toString());
                    catExpList.add(Float.parseFloat(catExp.getText().toString()));
                    dc.setNotes(noteList);
                    Log.d(TAG,">>"+dc.notesSize());
                    dc.setCategory(catList);
                    dc.setIncome(incomeList);
                    dc.setCatExp(catExpList);
                    dc.setIncCat(incCatList);
                    WriteToDB.getDB(getContext()).updateData(dc);
                }

                getActivity().getSupportFragmentManager().popBackStack();
                getActivity().finish();

            }
        });
        incomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                catDropAdapter=ArrayAdapter.createFromResource(getContext(),R.array.category_income
                        , android.R.layout.simple_spinner_item);
                catSpinner.setAdapter(catDropAdapter);
                accTextView.setText("Account");
                catTextView.setText("Category");

            }
        });
        expenseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                catDropAdapter=ArrayAdapter.createFromResource(getContext(),R.array.category_expense,
                        android.R.layout.simple_spinner_item);
                catSpinner.setAdapter(catDropAdapter);
                accTextView.setText("Account");
                catTextView.setText("Category");
            }
        });
        transferBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                catDropAdapter=ArrayAdapter.createFromResource(getContext(),R.array.account,
                        android.R.layout.simple_spinner_item);
                catSpinner.setAdapter(catDropAdapter);
                accDropAdapter=ArrayAdapter.createFromResource(getContext(),R.array.account,
                        android.R.layout.simple_spinner_item);
                accSpinner.setAdapter(accDropAdapter);
                accTextView.setText("From");
                catTextView.setText("To");

            }
        });
        return view;

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent){
        if(resultCode!= Activity.RESULT_OK){
            return;
        }
        if(requestCode==REQUEST_DATE){
            date=(Date) intent.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            Log.d(TAG,"/////<<<<"+date);
            dateStr=new SimpleDateFormat("dd/MM/yyyy").format(date);
            dateText.setText(dateStr);

        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
    public DataClass retDataObj(String date){
        dataClassList =WriteToDB.getDB(getContext()).getDCObjDB();
        for(int i=0;i<dataClassList.size();i++){
            if(dataClassList.get(i).getDate().equals(dateStr)){
                Log.d(TAG,",,,,"+dataClassList.get(i).getDate()+"....."+date);
                return dataClassList.get(i);
            }

        }
        return null;
    }
}
