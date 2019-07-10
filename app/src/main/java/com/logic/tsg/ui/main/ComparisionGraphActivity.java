package com.logic.tsg.ui.main;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.logic.tsg.R;

import java.util.ArrayList;

import dagger.android.support.DaggerFragment;

public class ComparisionGraphActivity extends DaggerFragment {

    Spinner spinnerType,spinnerFromDate,spinnerFromYear,
    spinnerToDate,spinnerToYear;

    String type,fromDate,fromYear,toDate,toYear;

    ImageButton imageButton;
    LineChart lineChart;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return  inflater.inflate(R.layout.activity_comparision_graph,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        lineChart=view.findViewById(R.id.graph);
        lineChart.setDragEnabled(true);
        lineChart.setScaleEnabled(true);
        ArrayList<Entry> yvalues=new ArrayList<>();

        yvalues.add(new Entry(0,5));
        yvalues.add(new Entry(1,4));
        yvalues.add(new Entry(8,5));
        yvalues.add(new Entry(3,5));
        yvalues.add(new Entry(4,9));

        LineDataSet set=new LineDataSet(yvalues,"Comparision ");
        set.setFillAlpha(110);

        LineData data=new LineData(set);
        lineChart.setData(data);





       imageButton=view.findViewById(R.id.imageButton);
       imageButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               init();
           }
       });
    }


    public void init(){

        Dialog dialog=new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog_comparision);

        Button button;
        spinnerType=dialog.findViewById(R.id.type);
        spinnerFromDate=dialog.findViewById(R.id.fromMonth);
        spinnerFromYear=dialog.findViewById(R.id.fromYear);
        spinnerToDate=dialog.findViewById(R.id.toMonth);
        spinnerToYear=dialog.findViewById(R.id.toYear);
        button=dialog.findViewById(R.id.submit2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        spinnerType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                type=parent.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerFromYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                fromYear=parent.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerFromDate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                fromDate=parent.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        spinnerToDate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                toDate=parent.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerToYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                toYear=parent.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        dialog.show();

    }

}
