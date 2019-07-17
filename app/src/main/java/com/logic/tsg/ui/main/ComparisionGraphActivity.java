package com.logic.tsg.ui.main;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.logic.tsg.Model.DataDaily;
import com.logic.tsg.Model.DataMinutes;
import com.logic.tsg.Model.DataWeek;
import com.logic.tsg.Model.HId;
import com.logic.tsg.Model.HardwareId;
import com.logic.tsg.R;
import com.logic.tsg.ViewModelProvidersFactory;
import com.logic.tsg.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class ComparisionGraphActivity extends DaggerFragment {

    private static final String TAG = "ComparisionGraphActivit";

    Spinner spinnerType,spinnerFromDate,spinnerFromYear,
    spinnerToDate,spinnerToYear;

    String type,fromDate,fromYear,toDate,toYear;

    ImageButton imageButton;
    LineChart lineChart;
    MainViewModel viewModel;
    HardwareId hardwareId;
    List<String> hardwareIdList=new ArrayList<>();
    HId hId;
    @Inject
    ViewModelProvidersFactory factory;
    List<DataDaily> dataDailyList=new ArrayList<>();
    List<DataDaily> dataDailyList2=new ArrayList<>();
    LineDataSet set1;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return  inflater.inflate(R.layout.activity_comparision_graph,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        hId=new HId("702ff36c6c16daad5cbb65f457644f56ca9b844f");
        hardwareIdList.add("702ff36c6c16daad5cbb65f457644f56ca9b844f");


        hardwareId=new HardwareId(hardwareIdList);
        //viewModel.header(hardwareId);


        viewModel= ViewModelProviders.of(getActivity(),factory).get(MainViewModel.class);

        lineChart=view.findViewById(R.id.graph);
        YAxis leftAxis=lineChart.getAxisLeft();
        leftAxis.removeAllLimitLines();
        lineChart.getAxisRight().setEnabled(false);

        lineChart.setDragEnabled(true);
        lineChart.animateX(1000);
        lineChart.setScaleEnabled(true);
        lineChart.setBorderColor(Color.BLACK);
        lineChart.setBorderWidth(5);
       // lineChart.clear();

       imageButton=view.findViewById(R.id.imageButton);
       imageButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               init();
           }
       });


            viewModel.getLiveDataDaily().observe(getActivity(), new Observer<List<DataDaily>>() {
                @Override
                public void onChanged(List<DataDaily> dataDailies) {
                    dataDailyList.clear();
                    Log.e(TAG, "onChanged 1: "+dataDailies);
                    dataDailyList.addAll(dataDailies);
                    plotLine();
                }
            });

            viewModel.getLiveDataDaily2().observe(getActivity(), new Observer<List<DataDaily>>() {
                @Override
                public void onChanged(List<DataDaily> dataDailies) {
                    dataDailyList2.clear();
                    dataDailyList2.addAll(dataDailies);
                    Log.e(TAG, "onChanged: 2 "+ dataDailyList2);
                    plotLine2();
                }
            });

           }


    void plotLine(){

        int x[]=new int [100];
        int y[]=new int[100];
        ArrayList<String> xases=new ArrayList<>();

        for( int i=0;i<dataDailyList.size();i++) {
            DataDaily parameterG1 = new DataDaily();
            parameterG1=dataDailyList.get(i);

            if (type.equalsIgnoreCase("Line Voltage (Line 1 -Line 2)")){
                x[i]=(int)parameterG1.getVry();
                y[i]=i;
            }
            else  if (type.equalsIgnoreCase("Line Voltage (Line 2 -Line 3)")){
                x[i]= (int) parameterG1.getVyb();
                y[i]=i;
            }
            else  if (type.equalsIgnoreCase("Line Voltage (Line 3 -Line 1)")){
                x[i]= (int) parameterG1.getVbr();
                y[i]=i;

            }
            else  if (type.equalsIgnoreCase("Phase Voltage (Line 1)")){
                x[i]= (int) parameterG1.getVrn();
                y[i]=i;

            }
            else  if (type.equalsIgnoreCase("Phase Voltage (Line 2)")){
                x[i]= (int) parameterG1.getVyn();
                y[i]=i;
            }
            else  if (type.equalsIgnoreCase("Phase Voltage (Line 3)")){
                x[i]= (int) parameterG1.getVbn();
                y[i]=i;
            }
            else  if (type.equalsIgnoreCase("Current in Phase (Line 1)")){
                x[i]= (int) parameterG1.getIr();
                y[i]=i;
            }
            else  if (type.equalsIgnoreCase("Current in Phase (Line 2)")){
                x[i]= (int) parameterG1.getIy();
                y[i]=i;

            }
            else  if (type.equalsIgnoreCase("Current in Phase (Line 3)")){
                x[i]= (int) parameterG1.getIb();
                y[i]=i;
            }
            else  if (type.equalsIgnoreCase("Electricity Consumption")){

                x[i]= (int) parameterG1.getElectricityConsumption();
                y[i]=i;

            }
            else  if (type.equalsIgnoreCase("Current Billing)")){
                x[i]= (int) parameterG1.getCurrentbilling();
                y[i]=i;
            }
        }

        YAxis leftAxis=lineChart.getAxisLeft();
        leftAxis.removeAllLimitLines();
        lineChart.getAxisRight().setEnabled(false);

        lineChart.setDragEnabled(true);
        lineChart.setScaleEnabled(true);
        ArrayList<Entry> yvalues=new ArrayList<>();
        for (int j=0;j<dataDailyList.size();j++){
            Log.e(TAG, "plotLine: "+y[j]);
            yvalues.add(new Entry(y[j],Math.round(x[j])));
        }

        for(int i=0;i<dataDailyList.size();i++){
            Log.e(TAG, "plotLine: "+y[i]);
        }

        set1=new LineDataSet(yvalues,"Month 1");
        set1.setFillAlpha(110);
        set1.setColor(Color.RED);
        set1.setLineWidth(3f);
        set1.setValueTextColor(Color.GREEN);
       /* LineData data=new LineData(set1);
        lineChart.animateX(2000);
        lineChart.setData(data);*/
        plotLine2();

    }


    void plotLine2(){

        int x1[]=new int[100];
        int y1[]=new int[100];

        for( int i=0;i<dataDailyList.size();i++) {
            DataDaily parameterG1 = new DataDaily();
            parameterG1=dataDailyList.get(i);


            if (type.equalsIgnoreCase("Line Voltage (Line 1 -Line 2)")){
                x1[i]=parameterG1.getVry();
                y1[i]=i;
            }
            else  if (type.equalsIgnoreCase("Line Voltage (Line 2 -Line 3)")){
                x1[i]= (int) parameterG1.getVyb();
                y1[i]=i;
            }
            else  if (type.equalsIgnoreCase("Line Voltage (Line 3 -Line 1)")){
                x1[i]= (int) parameterG1.getVbr();
                y1[i]=i;

            }
            else  if (type.equalsIgnoreCase("Phase Voltage (Line 1)")){
                x1[i]= (int) parameterG1.getVrn();
                y1[i]=i;

            }
            else  if (type.equalsIgnoreCase("Phase Voltage (Line 2)")){
                x1[i]= (int) parameterG1.getVyn();
                y1[i]=i;
            }
            else  if (type.equalsIgnoreCase("Phase Voltage (Line 3)")){
                x1[i]= (int) parameterG1.getVbn();
                y1[i]=i;
            }
            else  if (type.equalsIgnoreCase("Current in Phase (Line 1)")){
                x1[i]= (int) parameterG1.getIr();
                y1[i]=i;
            }
            else  if (type.equalsIgnoreCase("Current in Phase (Line 2)")){
                x1[i]= (int) parameterG1.getIy();
                y1[i]=i;

            }
            else  if (type.equalsIgnoreCase("Current in Phase (Line 3)")){
                x1[i]= (int) parameterG1.getIb();
                y1[i]=i;
            }
            else  if (type.equalsIgnoreCase("Electricity Consumption")){

                x1[i]= (int) parameterG1.getElectricityConsumption();
                y1[i]=i;

            }
            else  if (type.equalsIgnoreCase("Current Billing)")){
                x1[i]= (int) parameterG1.getCurrentbilling();
                y1[i]=i;
            }

        }

        YAxis leftAxis=lineChart.getAxisLeft();
        leftAxis.removeAllLimitLines();


        lineChart.getAxisRight().setEnabled(false);

        lineChart.setDragEnabled(true);
        lineChart.setScaleEnabled(true);
        ArrayList<Entry> yvalues2=new ArrayList<>();
        for (int j=0;j<dataDailyList2.size();j++){
            yvalues2.add(new Entry(y1[j],x1[j]));
        }
        LineDataSet set=new LineDataSet(yvalues2,"Month2");
        set.setFillAlpha(110);
        set.setColor(Color.BLUE);
        set.setLineWidth(3f);
        set.setValueTextColor(Color.GREEN);

       List<ILineDataSet> dataSets = new ArrayList<>(); // for adding multiple plots

        dataSets.add(set);
        dataSets.add(set1);


       for (int i=0;i<dataDailyList2.size();i++){
           Log.e(TAG, "plotLine2: "+y1[i]);
       }



        LineData data=new LineData(dataSets);
       LineData lineData=new LineData(set1);
        lineChart.animateX(2000);
       // lineChart.setData(lineData);
        lineChart.setData(data);
        lineChart.invalidate();
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

                if(type!=null && fromYear!=null && fromDate!=null && toDate!=null  && toYear!=null){

                    viewModel.getParameterDaily(hardwareId,"daily",Integer.parseInt(fromDate),Integer.parseInt(fromYear));
                    viewModel.getParameterDaily2(hardwareId,"daily",Integer.parseInt(toDate),Integer.parseInt(toYear));
                }

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
                if (fromDate.equalsIgnoreCase("January")){
                    fromDate="1";

                }
                else if (fromDate.equalsIgnoreCase("Febuary")){
                    fromDate="2";
                }
                else if (fromDate.equalsIgnoreCase("March")){
                    fromDate="3";
                }
                else if (fromDate.equalsIgnoreCase("April")){

                    fromDate="4";
                }
                else if (fromDate.equalsIgnoreCase("May")){
                    fromDate="5";
                }
                else if (fromDate.equalsIgnoreCase("June")){

                    fromDate="6";
                }
                else if (fromDate.equalsIgnoreCase("July")){

                    fromDate="7";
                }
                else if (fromDate.equalsIgnoreCase("August")){
                    fromDate="8";
                }
                else if (fromDate.equalsIgnoreCase("September")){
                    fromDate="9";
                }
                else if (fromDate.equalsIgnoreCase("October")){
                    fromDate="10";
                }
                else if (fromDate.equalsIgnoreCase("November")){
                    fromDate="11";
                }
                else if (fromDate.equalsIgnoreCase("December")){
                    fromDate="12";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                fromDate="7";
            }
        });


        spinnerToDate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                toDate=parent.getSelectedItem().toString();

                if (toDate.equalsIgnoreCase("January")){
                    toDate="1";

                }
                else if (toDate.equalsIgnoreCase("Febuary")){
                    toDate="2";
                }
                else if (toDate.equalsIgnoreCase("March")){
                    toDate="3";
                }
                else if (toDate.equalsIgnoreCase("April")){

                    toDate="4";
                }
                else if (toDate.equalsIgnoreCase("May")){
                    toDate="5";
                }
                else if (toDate.equalsIgnoreCase("June")){

                    toDate="6";
                }
                else if (toDate.equalsIgnoreCase("July")){

                    toDate="7";
                }
                else if (toDate.equalsIgnoreCase("August")){
                    toDate="8";
                }
                else if (toDate.equalsIgnoreCase("September")){
                    toDate="9";
                }
                else if (toDate.equalsIgnoreCase("October")){
                    toDate="10";
                }
                else if (toDate.equalsIgnoreCase("November")){
                    toDate="11";
                }
                else if (toDate.equalsIgnoreCase("December")){
                    toDate="12";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                toDate="7";
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
