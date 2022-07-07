package com.example.moneymanager;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class DataInputActivity extends AppCompatActivity {
    public static Intent newIntent(Context context){
        Intent i=new Intent(context,DataInputActivity.class);
        return i;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.data_input_activity);
        FragmentManager fm=getSupportFragmentManager();
        Fragment fragment=DataInputFragment.newInstance();
        if(savedInstanceState==null){
            fm.beginTransaction().add(R.id.data_input_fragment_container,fragment,null)
                    .addToBackStack(null).commit();
        }
    }

}
