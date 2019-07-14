package com.logic.tsg.ui.main;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.logic.tsg.Model.DataDaily;
import com.logic.tsg.Model.DataMinutes;
import com.logic.tsg.Model.DataMonthly;
import com.logic.tsg.Model.DataWeek;
import com.logic.tsg.Model.HId;
import com.logic.tsg.Model.HardwareId;
import com.logic.tsg.Model.DataHourly;
import com.logic.tsg.R;
import com.logic.tsg.ViewModelProvidersFactory;
import com.logic.tsg.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import dagger.android.support.DaggerFragment;

public class ParameterGraphActivity extends DaggerFragment implements OnChartGestureListener
, OnChartValueSelectedListener {

    private static final String TAG = "ParameterGraphActivity";

    MainViewModel viewModel;
    List<String> hardwareIdList=new ArrayList<>();
    HId hId;
    @Inject
    ViewModelProvidersFactory factoryModule;
    int day,yr,mth;
    EditText editTextCal,editTextTime;
    ImageButton calenderButton,timeButton;
    DatePickerDialog.OnDateSetListener date1;
    Spinner spinnerType,spinnerSortBy;
    private int mHour;
    private int mMinute;
    String paramter;
    String sortType;
    List<DataMinutes> dataMinutesList=new ArrayList<>();
    List<DataDaily> dataDailyList=new ArrayList<>();
    List<DataHourly> dataHourlyList=new ArrayList<>();
    List<DataWeek> dataWeekList=new ArrayList<>();
    List<DataMonthly> dataMonthlist=new ArrayList<>();
    double x[]=new double[100];
    int y[]=new int[100];

    ImageButton imageButton;
    TextView textViewTitle;
    TextView textViewYearHourly,textViewMonthHourly,textViewYDateHourly;
    Spinner spinnerMonthDaily, spinnerYearDaily;
    Spinner spinnerMonthWeekly,spinnerYearWeekly;
    Spinner  spinnerYearMonthly;

    LineChart lineChart;
    BarChart barChart;
    HardwareId hardwareId;

    String monthDaily,yearDaily,monthWeekly,yearWeekly,yearMonthly;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.activity_parameter_graph,container,false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        lineChart=view.findViewById(R.id.lineGraph);
        barChart=view.findViewById(R.id.barGraph);
        textViewTitle=view.findViewById(R.id.text);
        viewModel= ViewModelProviders.of(ParameterGraphActivity.this, factoryModule)
                .get(MainViewModel.class);
        hId=new HId("702ff36c6c16daad5cbb65f457644f56ca9b844f");
        hardwareIdList.add("702ff36c6c16daad5cbb65f457644f56ca9b844f");


        hardwareId=new HardwareId(hardwareIdList);
        //viewModel.header(hardwareId);
        textViewTitle.setText("Minutely Parametric Graph");

        imageButton=view.findViewById(R.id.imageButton2);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_1();

            }
        });


        sortType="Line Voltage(Line1 - Line2)";
        paramter="Minutely";
        /*
                    Parameter
         */
        getParamter(hardwareId,"Line Voltage(Line2-Line3)","minutely",7,2019,8,12);

        /*
                                Minutes **********************************************
         */

        viewModel.getLiveDataMinutes().observe(this, new Observer<List<DataMinutes>>() {
            @Override
            public void onChanged(List<DataMinutes> dataHourlies) {
                Log.e(TAG, "onChanged: "+dataHourlies );
                dataMinutesList.addAll(dataHourlies);
              //  plot("cc","ss");
                plotLine();
            }
        });
        /*
                                Daily ************************************************
         */

        viewModel.getLiveDataDaily().observe(this, new Observer<List<DataDaily>>() {
            @Override
            public void onChanged(List<DataDaily> dataDailies) {

                Log.e(TAG, "onChanged: "+dataDailies );
                dataDailyList.addAll(dataDailies);
                plotLineDaily();
            }
        });

        /*
                                Hourly ***********************************************
         */

        viewModel.getLiveDataHourly().observe(this, new Observer<List<DataHourly>>() {
            @Override
            public void onChanged(List<DataHourly> dataHourlies) {

                dataHourlyList.addAll(dataHourlies);
                plotLineHour();

            }
        });

        /*
                            Weekly ****************************************************
         */

        viewModel.getLiveDataWeek().observe(this, new Observer<List<DataWeek>>() {
            @Override
            public void onChanged(List<DataWeek> dataWeeks) {

                dataWeekList.addAll(dataWeeks);
                plotWeekly();
            }
        });

        /*
                            Monthly ****************************************************
         */

        viewModel.getLiveDataMonthly().observe(this, new Observer<List<DataMonthly>>() {
            @Override
            public void onChanged(List<DataMonthly> dataMonthlies) {

                dataMonthlist.addAll(dataMonthlies);
                plotMonthly();
            }
        });

    }




    /*
                Minutes ------------------------------------------------
     */

    public void getParamter(HardwareId hardwareId,String sortType,String limit, int month,int year, int date, int mHour){
        viewModel.getParamterMinutes(hardwareId,limit,month,year,date,mHour);
    }


    /*
                Hourly -------------------------------------------------
     */

    public void  getHourly(HardwareId hardwareId , String sortType,String limit, int month, int year, int date){

        viewModel.getParameterHourly(hardwareId,limit,month,year,date);

    }


    /*
                Daily ------------------------------------------------------
     */

    public void getDaily(HardwareId hardwareId ,String sortType, String limit, int month, int year){

        viewModel.getParameterDaily(hardwareId,limit,month,year);
    }

    /*
                Weekly----------------------------------------------------
     */

    public void getWeekly(HardwareId hardwareId ,String sortType ,String limit, int month
            , int year){

        viewModel.getParameterWeekly(hardwareId,limit,month,year);
        plotWeekly();
    }

    /*
                        Monthly ------------------------------------------
     */
    public void getMonthly(HardwareId hardwareId ,String sortType ,String limit, int year){

        viewModel.getParameterMonthly(hardwareId,limit,year);
        plotMonthly();

    }



    void plotLine(){

        barChart.setVisibility(View.GONE);
        lineChart.setVisibility(View.VISIBLE);

        for( int i=0;i<dataMinutesList.size();i++) {
            DataMinutes parameterG1 = new DataMinutes();
            parameterG1=dataMinutesList.get(i);

            Log.e(TAG, "plot: " + parameterG1.getElectricityConsumption() +" ****" + parameterG1.getMinutes());

            if (sortType.equalsIgnoreCase("Line Voltage (Line 1 -Line 2)")){
                x[i]=parameterG1.getVry();
                y[i]=parameterG1.getMinutes();
            }
            else  if (sortType.equalsIgnoreCase("Line Voltage (Line 2 -Line 3)")){
                x[i]=parameterG1.getVyb();
                y[i]=parameterG1.getMinutes();
            }
            else  if (sortType.equalsIgnoreCase("Line Voltage (Line 3 -Line 1)")){
                x[i]=parameterG1.getVbr();
                y[i]=parameterG1.getMinutes();

            }
            else  if (sortType.equalsIgnoreCase("Phase Voltage (Line 1)")){
                x[i]=parameterG1.getVrn();
                y[i]=parameterG1.getMinutes();

            }
            else  if (sortType.equalsIgnoreCase("Phase Voltage (Line 2)")){
                x[i]=parameterG1.getVyn();
                y[i]=parameterG1.getMinutes();
            }
            else  if (sortType.equalsIgnoreCase("Phase Voltage (Line 3)")){
                x[i]=parameterG1.getVbn();
                y[i]=parameterG1.getMinutes();
            }
            else  if (sortType.equalsIgnoreCase("Current in Phase (Line 1)")){
                x[i]=parameterG1.getIr();
                y[i]=parameterG1.getMinutes();
            }
            else  if (sortType.equalsIgnoreCase("Current in Phase (Line 2)")){
                x[i]=parameterG1.getIy();
                y[i]=parameterG1.getMinutes();

            }
            else  if (sortType.equalsIgnoreCase("Current in Phase (Line 3)")){
                x[i]=parameterG1.getIb();
                y[i]=parameterG1.getMinutes();
            }
            else  if (sortType.equalsIgnoreCase("Electricity Consumption")){

                x[i]=parameterG1.getElectricityConsumption();
                y[i]=parameterG1.getMinutes();

            }
            else  if (sortType.equalsIgnoreCase("Current Billing)")){
                x[i]=parameterG1.getCurrentbilling();
                y[i]=parameterG1.getMinutes();
            }
            else {
                x[i]=parameterG1.getVry();
                y[i]=parameterG1.getMinutes();
            }
        }

        //****
        // Controlling X axis
        XAxis xAxis = lineChart.getXAxis();
        // Set the xAxis position to bottom. Default is top
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        YAxis leftAxis=lineChart.getAxisLeft();
        leftAxis.removeAllLimitLines();

        YAxis rightAxis=lineChart.getAxisRight();
        rightAxis.setEnabled(false);


        lineChart.getAxisRight().setEnabled(false);

        lineChart.setDragEnabled(true);
        lineChart.setScaleEnabled(true);
        ArrayList<Entry> yvalues=new ArrayList<>();
        yvalues.clear();
        for (int j=0;j<dataMinutesList.size();j++){
            yvalues.add(new Entry(y[j],Math.round(x[j])));
        }

        LineDataSet set=new LineDataSet(yvalues,"Comparision ");
        set.setFillAlpha(110);
        set.setColor(Color.RED);
        set.setLineWidth(3f);
        set.setValueTextColor(Color.GREEN);

        LineData data=new LineData(set);
        lineChart.setData(data);
        lineChart.animateX(2500);
        lineChart.notifyDataSetChanged();
        lineChart.invalidate();

    }

    public void plotLineHour(){
        int x1 []=new int[200];
        int y1 []=new int[200];
        lineChart.clear();
        lineChart.clearValues();
        for( int i=0;i<dataHourlyList.size();i++) {
            DataHourly parameterG1 = new DataHourly();
            parameterG1=dataHourlyList.get(i);

            if (sortType.equalsIgnoreCase("Line Voltage (Line 1 -Line 2)")){
                x1[i]=parameterG1.getVry();
                y1[i]=parameterG1.getHour();
            }
            else  if (sortType.equalsIgnoreCase("Line Voltage (Line 2 -Line 3)")){
                x1[i]= (int) parameterG1.getVyb();
                y1[i]=parameterG1.getHour();
            }
            else  if (sortType.equalsIgnoreCase("Line Voltage (Line 3 -Line 1)")){
                x1[i]=parameterG1.getVbr();
                y1[i]=parameterG1.getHour();

            }
            else  if (sortType.equalsIgnoreCase("Phase Voltage (Line 1)")){
                x1[i]=parameterG1.getVrn();
                y1[i]=parameterG1.getHour();

            }
            else  if (sortType.equalsIgnoreCase("Phase Voltage (Line 2)")){
                x1[i]=parameterG1.getVyn();
                y1[i]=parameterG1.getHour();
            }
            else  if (sortType.equalsIgnoreCase("Phase Voltage (Line 3)")){
                x1[i]=parameterG1.getVbn();
                y1[i]=parameterG1.getHour();
            }
            else  if (sortType.equalsIgnoreCase("Current in Phase (Line 1)")){
                x1[i]= (int) parameterG1.getIr();
                y1[i]=parameterG1.getHour();
            }
            else  if (sortType.equalsIgnoreCase("Current in Phase (Line 2)")){
                x1[i]= (int) parameterG1.getIy();
                y1[i]=parameterG1.getHour();

            }
            else  if (sortType.equalsIgnoreCase("Current in Phase (Line 3)")){
                x1[i]= (int) parameterG1.getIb();
                y1[i]=parameterG1.getHour();
            }
            else  if (sortType.equalsIgnoreCase("Electricity Consumption")){

                x1[i]= (int) parameterG1.getElectricityConsumption();
                y1[i]=parameterG1.getHour();

            }
            else  if (sortType.equalsIgnoreCase("Current Billing)")){
                x1[i]= (int) parameterG1.getCurrentbilling();
                y1[i]=parameterG1.getHour();
            }
            else {
                x1[i]=parameterG1.getVry();
                y1[i]=parameterG1.getHour();
            }

        }

        YAxis leftAxis=lineChart.getAxisLeft();
        leftAxis.removeAllLimitLines();


        lineChart.getAxisRight().setEnabled(false);

        lineChart.setDragEnabled(true);
        lineChart.setScaleEnabled(true);
        ArrayList<Entry> yvalues=new ArrayList<>();
        yvalues.clear();
        for (int j=0;j<dataMinutesList.size();j++){
            yvalues.add(new Entry(y1[j],Math.round(x1[j])));
        }

        LineDataSet set=new LineDataSet(yvalues,"Comparision ");
        set.setFillAlpha(110);
        set.setColor(Color.RED);
        set.setLineWidth(3f);
        set.setValueTextColor(Color.GREEN);

        LineData data=new LineData(set);
        lineChart.setData(data);
        lineChart.animateX(2500);
        lineChart.notifyDataSetChanged();
        lineChart.invalidate();
    }

    public void plotLineDaily(){

        barChart.setVisibility(View.GONE);
        lineChart.setVisibility(View.VISIBLE);

        int x1 []=new int[200];
        int y1 []=new int[200];
        lineChart.clear();
        lineChart.clearValues();
        for( int i=0;i<dataDailyList.size();i++) {
            DataDaily parameterG1 = new DataDaily();
            parameterG1=dataDailyList.get(i);
            if (sortType.equalsIgnoreCase("Line Voltage (Line 1 -Line 2)")){
                x1[i]=parameterG1.getVry();
                y1[i]=parameterG1.getTime();            }
            else  if (sortType.equalsIgnoreCase("Line Voltage (Line 2 -Line 3)")){
                x1[i]= (int) parameterG1.getVyb();
                y1[i]=parameterG1.getTime();            }
            else  if (sortType.equalsIgnoreCase("Line Voltage (Line 3 -Line 1)")){
                x1[i]= (int) parameterG1.getVbr();
                y1[i]=parameterG1.getTime();
            }
            else  if (sortType.equalsIgnoreCase("Phase Voltage (Line 1)")){
                x1[i]= (int) parameterG1.getVrn();
                y1[i]=parameterG1.getTime();

            }
            else  if (sortType.equalsIgnoreCase("Phase Voltage (Line 2)")){
                x1[i]= (int) parameterG1.getVyn();
                y1[i]=parameterG1.getTime();
            }
            else  if (sortType.equalsIgnoreCase("Phase Voltage (Line 3)")){
                x1[i]= (int) parameterG1.getVbn();
                y1[i]=parameterG1.getTime();
            }
            else  if (sortType.equalsIgnoreCase("Current in Phase (Line 1)")){
                x1[i]= (int) parameterG1.getIr();
                y1[i]=parameterG1.getTime();
            }
            else  if (sortType.equalsIgnoreCase("Current in Phase (Line 2)")){
                x1[i]= (int) parameterG1.getIy();
                y1[i]=parameterG1.getTime();

            }
            else  if (sortType.equalsIgnoreCase("Current in Phase (Line 3)")){
                x1[i]= (int) parameterG1.getIb();
                y1[i]=parameterG1.getTime();
            }
            else  if (sortType.equalsIgnoreCase("Electricity Consumption")){

                x1[i]= (int) parameterG1.getElectricityConsumption();
                y1[i]=parameterG1.getTime();

            }
            else  if (sortType.equalsIgnoreCase("Current Billing)")){
                x1[i]= (int) parameterG1.getCurrentbilling();
                y1[i]=parameterG1.getTime();
            }
            else {
                x1[i]=parameterG1.getVry();
                y1[i]=parameterG1.getTime();
            }

        }

        YAxis leftAxis=lineChart.getAxisLeft();

        leftAxis.removeAllLimitLines();


        lineChart.getAxisRight().setEnabled(false);

        lineChart.setDragEnabled(true);
        lineChart.setScaleEnabled(true);
        ArrayList<Entry> yvalues=new ArrayList<>();
        yvalues.clear();
        for (int j=0;j<dataMinutesList.size();j++){
            yvalues.add(new Entry(y1[j],Math.round(x1[j])));
        }

        LineDataSet set=new LineDataSet(yvalues,"Comparision ");
        set.setFillAlpha(110);
        set.setColor(Color.RED);
        set.setLineWidth(3f);
        set.setValueTextColor(Color.GREEN);

        LineData data=new LineData(set);
        lineChart.setData(data);
        lineChart.animateX(2500);
        lineChart.notifyDataSetChanged();
        lineChart.invalidate();
    }

    public void  plotWeekly(){


        int bx[]=new int[100];
        int by[]=new int[100];



        for( int i=0;i<dataWeekList.size();i++) {
            DataWeek parameterG1 = new DataWeek();
            parameterG1=dataWeekList.get(i);

            if (sortType.equalsIgnoreCase("Line Voltage (Line 1 -Line 2)")){
                bx[i]=parameterG1.getVry();
                by[i]=parameterG1.getIndex();
            }
            else  if (sortType.equalsIgnoreCase("Line Voltage (Line 2 -Line 3)")){
                bx[i]= (int) parameterG1.getVyb();
                by[i]=parameterG1.getIndex();
            }
            else  if (sortType.equalsIgnoreCase("Line Voltage (Line 3 -Line 1)")){
                bx[i]= (int) parameterG1.getVbr();
                by[i]=parameterG1.getIndex();

            }
            else  if (sortType.equalsIgnoreCase("Phase Voltage (Line 1)")){
                bx[i]= (int) parameterG1.getVrn();
                by[i]=parameterG1.getIndex();
            }
            else  if (sortType.equalsIgnoreCase("Phase Voltage (Line 2)")){
              //  bx[i]=parameterG1.getVyn();
                bx[i]= (int) parameterG1.getVrn();
                by[i]=parameterG1.getIndex();
            }
            else  if (sortType.equalsIgnoreCase("Phase Voltage (Line 3)")){
                bx[i]= (int) parameterG1.getVbn();
                by[i]=parameterG1.getIndex();
            }
            else  if (sortType.equalsIgnoreCase("Current in Phase (Line 1)")){
                bx[i]= (int) parameterG1.getIr();
                by[i]=parameterG1.getIndex();
            }
            else  if (sortType.equalsIgnoreCase("Current in Phase (Line 2)")){
                bx[i]= (int) parameterG1.getIy();
                by[i]=parameterG1.getIndex();
            }
            else  if (sortType.equalsIgnoreCase("Current in Phase (Line 3)")){
                bx[i]= (int) parameterG1.getIb();
                by[i]=parameterG1.getIndex();
            }
            else  if (sortType.equalsIgnoreCase("Electricity Consumption")){

                bx[i]= (int) parameterG1.getElectricityConsumption();
                by[i]=parameterG1.getIndex();

            }
            else  if (sortType.equalsIgnoreCase("Current Billing)")){
                bx[i]= (int) parameterG1.getCurrentbilling();
                by[i]=parameterG1.getIndex();
            }
            else {
                bx[i]=parameterG1.getVry();
                by[i]=parameterG1.getIndex();
            }
        }

        lineChart.setVisibility(View.GONE);
        barChart.setVisibility(View.VISIBLE);
        barChart.setDrawBarShadow(false);
        barChart.setDrawValueAboveBar(true);
       // barChart.setMaxVisibleValueCount();

        barChart.setPinchZoom(false);
        barChart.setDrawGridBackground(true);

        ArrayList<BarEntry> entries=new ArrayList<>();
        entries.clear();

        for (int j=0;j<dataWeekList.size();j++)
        {
            entries.add(new BarEntry(bx[j],by[j]));
            Log.e(TAG, "plotWeekly: "+bx[j]);
        }
        BarDataSet barDataSet=new BarDataSet(entries,"Weekly");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        BarData data=new BarData(barDataSet);
        data.setBarWidth(0.9f);
        barChart.animateX(300);
        barChart.setData(data);
        barChart.notifyDataSetChanged();
        barChart.invalidate();


    }

    public void plotMonthly(){
        int bx[]=new int[100];
        int by[]=new int[100];

        for( int i=0;i<dataMonthlist.size();i++) {
            DataMonthly parameterG1 = new DataMonthly();
            parameterG1=dataMonthlist.get(i);


            if (sortType.equalsIgnoreCase("Line Voltage (Line 1 -Line 2)")){
                bx[i]=parameterG1.getVry();
                by[i]=parameterG1.getTime();
            }
            else  if (sortType.equalsIgnoreCase("Line Voltage (Line 2 -Line 3)")){
                bx[i]= (int) parameterG1.getVyb();
                by[i]=parameterG1.getTime();
            }
            else  if (sortType.equalsIgnoreCase("Line Voltage (Line 3 -Line 1)")){
                bx[i]= (int) parameterG1.getVbr();
                by[i]=parameterG1.getTime();

            }
            else  if (sortType.equalsIgnoreCase("Phase Voltage (Line 1)")){
                bx[i]= (int) parameterG1.getVrn();
                by[i]=parameterG1.getTime();
            }
            else  if (sortType.equalsIgnoreCase("Phase Voltage (Line 2)")){
                //  bx[i]=parameterG1.getVyn();
                bx[i]= (int) parameterG1.getVrn();
                by[i]=parameterG1.getTime();
            }
            else  if (sortType.equalsIgnoreCase("Phase Voltage (Line 3)")){
                bx[i]= (int) parameterG1.getVbn();
                by[i]=parameterG1.getTime();
            }
            else  if (sortType.equalsIgnoreCase("Current in Phase (Line 1)")){
                bx[i]= (int) parameterG1.getIr();
                by[i]=parameterG1.getTime();
            }
            else  if (sortType.equalsIgnoreCase("Current in Phase (Line 2)")){
                bx[i]= (int) parameterG1.getIy();
                by[i]=parameterG1.getTime();
            }
            else  if (sortType.equalsIgnoreCase("Current in Phase (Line 3)")){
                bx[i]= (int) parameterG1.getIb();
                by[i]=parameterG1.getTime();
            }
            else  if (sortType.equalsIgnoreCase("Electricity Consumption")){

                bx[i]= (int) parameterG1.getElectricityConsumption();
                by[i]=parameterG1.getTime();

            }
            else  if (sortType.equalsIgnoreCase("Current Billing)")){
                bx[i]= (int) parameterG1.getCurrentbilling();
                by[i]=parameterG1.getTime();
            }
            else {
                bx[i]=parameterG1.getVry();
                by[i]=parameterG1.getTime();
            }
        }


        lineChart.setVisibility(View.GONE);
        barChart.setVisibility(View.VISIBLE);
        barChart.setDrawBarShadow(false);
        barChart.setDrawValueAboveBar(true);
        // barChart.setMaxVisibleValueCount();

        barChart.setPinchZoom(false);
        barChart.setDrawGridBackground(true);

        ArrayList<BarEntry> entries=new ArrayList<>();
        entries.clear();

        for (int j=0;j<dataMonthlist.size();j++)
        {
            entries.add(new BarEntry(by[j],bx[j]));
            Log.e(TAG, "plotMonthly: "+bx[j]);
        }


        String mont [] =new String[]{
                "January",
                "Febuary",
                "March",
                "April",
                "May"
                ,"June"
                ,"July"
                ,"August"
                ,"September"
                ,"October"
                ,"November"
                ,"December"
        };
        XAxis xAxis=barChart.getXAxis();
        xAxis.setValueFormatter(new MyMonthXAxisValueFormatter(mont));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        BarDataSet barDataSet=new BarDataSet(entries,"Monthly");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        BarData data=new BarData(barDataSet);
        data.setBarWidth(0.6f);
        barChart.animateX(2500);
        barChart.setData(data);
        barChart.notifyDataSetChanged();
        barChart.invalidate();

    }

    public class MyMonthXAxisValueFormatter extends ValueFormatter implements IAxisValueFormatter {

        private String [] mvalues;
        public MyMonthXAxisValueFormatter(String [] mvalues){
            this.mvalues=mvalues;
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            return mvalues[(int) value];
        }
    }



    public void dialog_1()
    {

        final LinearLayout layoutMinuetly,layoutHourly,layoutDaily,layoutWeekly,layoutMonthly;

        Button button;
        final Dialog dialog=new Dialog(getActivity());
        dialog.setContentView(R.layout.parameter_dialog);

        button=dialog.findViewById(R.id.submit);
        spinnerType=dialog.findViewById(R.id.type);
        spinnerSortBy=dialog.findViewById(R.id.sortBy);
        calenderButton=dialog.findViewById(R.id.date_imagebutton);
        timeButton=dialog.findViewById(R.id.time_imagebutton);
        editTextTime=dialog.findViewById(R.id.date_arrival_EditText);
        editTextCal=dialog.findViewById(R.id.date_departure_EditText);
        layoutMinuetly=dialog.findViewById(R.id.layoutMinutely);
        layoutHourly=dialog.findViewById(R.id.layout_Hourly);
        layoutDaily=dialog.findViewById(R.id.layoutDaily);
        layoutWeekly=dialog.findViewById(R.id.weeklyLayout);
        layoutMonthly=dialog.findViewById(R.id.layoutMonthly);
        textViewYearHourly=dialog.findViewById(R.id.textViewYearHourly);
        textViewMonthHourly=dialog.findViewById(R.id.textViewMonthHourly);
        textViewYDateHourly=dialog.findViewById(R.id.textViewDateHourly);
        spinnerMonthDaily=dialog.findViewById(R.id.toMonthDaily);
        spinnerYearDaily=dialog.findViewById(R.id.toYearDaily);
        spinnerMonthWeekly=dialog.findViewById(R.id.toMonthWeekly);
        spinnerYearWeekly=dialog.findViewById(R.id.toYearWeekly);
        spinnerYearMonthly=dialog.findViewById(R.id.toYearMonthly);



        spinnerMonthDaily.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                monthDaily=parent.getSelectedItem().toString();
                if (monthDaily.equalsIgnoreCase("January")){
                    monthDaily="1";

                }
                else if (monthDaily.equalsIgnoreCase("Febuary")){
                    monthDaily="2";
                }
                else if (monthDaily.equalsIgnoreCase("March")){
                    monthDaily="3";
                }
                else if (monthDaily.equalsIgnoreCase("April")){

                    monthDaily="4";
                }
                else if (monthDaily.equalsIgnoreCase("May")){
                    monthDaily="5";
                }
                else if (monthDaily.equalsIgnoreCase("June")){

                    monthDaily="6";
                }
                else if (monthDaily.equalsIgnoreCase("July")){

                    monthDaily="7";
                }
                else if (monthDaily.equalsIgnoreCase("August")){
                    monthDaily="8";
                }
                else if (monthDaily.equalsIgnoreCase("September")){
                    monthDaily="9";
                }
                else if (monthDaily.equalsIgnoreCase("October")){
                    monthDaily="10";
                }
                else if (monthDaily.equalsIgnoreCase("November")){
                    monthDaily="11";
                }
                else if (monthDaily.equalsIgnoreCase("December")){
                    monthDaily="12";
                }

            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerYearDaily.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                yearDaily=parent.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerMonthWeekly.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                monthWeekly=parent.getSelectedItem().toString();

                if (monthWeekly.equalsIgnoreCase("January")){
                    monthWeekly="1";

                }
                else if (monthWeekly.equalsIgnoreCase("Febuary")){
                    monthWeekly="2";
                }
                else if (monthWeekly.equalsIgnoreCase("March")){
                    monthWeekly="3";
                }
                else if (monthWeekly.equalsIgnoreCase("April")){

                    monthWeekly="4";
                }
                else if (monthWeekly.equalsIgnoreCase("May")){
                    monthWeekly="5";
                }
                else if (monthWeekly.equalsIgnoreCase("June")){

                    monthWeekly="6";
                }
                else if (monthWeekly.equalsIgnoreCase("July")){

                    monthWeekly="7";
                }
                else if (monthWeekly.equalsIgnoreCase("August")){
                    monthWeekly="8";
                }
                else if (monthWeekly.equalsIgnoreCase("September")){
                    monthWeekly="9";
                }
                else if (monthWeekly.equalsIgnoreCase("October")){
                    monthWeekly="10";
                }
                else if (monthWeekly.equalsIgnoreCase("November")){
                    monthWeekly="11";
                }
                else if (monthWeekly.equalsIgnoreCase("December")){
                    monthWeekly="12";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        spinnerYearWeekly.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                yearWeekly=parent.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerYearMonthly.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                yearMonthly=parent.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Calendar calendar = Calendar.getInstance();
        yr = calendar.get(Calendar.YEAR);
        mth = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        mHour = calendar.get(Calendar.HOUR_OF_DAY);
        mMinute = calendar.get(Calendar.MINUTE);


        textViewYearHourly.setText(""+yr);
        textViewYDateHourly.setText(""+day);
        textViewMonthHourly.setText(""+calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (paramter!=null && sortType!=null){

                    if (paramter.equalsIgnoreCase("minutely")){
                        getParamter(hardwareId,sortType,"minutely",mth,yr,day,mHour);
                        Toast.makeText(getActivity(), "Loading ...", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                    else if (paramter.equalsIgnoreCase("hourly")){
                      //  getHourly(hardwareId,sortType,"Hourly",);
                        Toast.makeText(getActivity(), "Loading ...", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                    else if (paramter.equalsIgnoreCase("Daily")){

                        getDaily(hardwareId,sortType,"daily",Integer.parseInt(monthDaily),Integer.parseInt(yearDaily));
                        Toast.makeText(getActivity(), "Loading ...", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                    else if (paramter.equalsIgnoreCase("Weekly")){

                        getWeekly(hardwareId,sortType,"weekly",Integer.parseInt(monthWeekly),Integer.parseInt(yearWeekly));
                        Toast.makeText(getActivity(), "Loading ...", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                    else if (paramter.equalsIgnoreCase("Monthly")){
                        getMonthly(hardwareId,sortType,"monthly",Integer.parseInt(yearMonthly));
                        Toast.makeText(getActivity(), "Loading ...", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }
                else {
                    Toast.makeText(getActivity(), "Please Select Parameter !", Toast.LENGTH_SHORT).show();
                }

            }
        });

        /*
                    type
         */

        spinnerType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                paramter=parent.getSelectedItem().toString();
                if (paramter.equalsIgnoreCase("Minutely")){
                    layoutMinuetly.setVisibility(View.VISIBLE);
                    layoutDaily.setVisibility(View.GONE);
                    layoutMonthly.setVisibility(View.GONE);
                    layoutWeekly.setVisibility(View.GONE);
                    layoutHourly.setVisibility(View.GONE);

                }
                else if (paramter.equalsIgnoreCase("Hourly")){

                    layoutHourly.setVisibility(View.VISIBLE);
                    layoutDaily.setVisibility(View.GONE);
                    layoutMonthly.setVisibility(View.GONE);
                    layoutWeekly.setVisibility(View.GONE);
                    layoutMinuetly.setVisibility(View.GONE);

                }
                else if (paramter.equalsIgnoreCase("Daily")){
                    layoutDaily.setVisibility(View.VISIBLE);
                    layoutMinuetly.setVisibility(View.GONE);
                    layoutMonthly.setVisibility(View.GONE);
                    layoutWeekly.setVisibility(View.GONE);
                    layoutHourly.setVisibility(View.GONE);
                }
                else if (paramter.equalsIgnoreCase("Weekly")){
                    layoutWeekly.setVisibility(View.VISIBLE);
                    layoutDaily.setVisibility(View.GONE);
                    layoutMonthly.setVisibility(View.GONE);
                    layoutMinuetly.setVisibility(View.GONE);
                    layoutHourly.setVisibility(View.GONE);

                }
                else if (paramter.equalsIgnoreCase("Monthly")){
                    layoutMonthly.setVisibility(View.VISIBLE);
                    layoutDaily.setVisibility(View.GONE);
                    layoutMinuetly.setVisibility(View.GONE);
                    layoutWeekly.setVisibility(View.GONE);
                    layoutHourly.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerSortBy.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                sortType=parent.getSelectedItem().toString();
                textViewTitle.setText(""+sortType+" Parametric Graph");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        calenderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getActivity(), date1, yr, mth, day).show();

            }
        });

        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);


                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                                editTextTime.setText(hourOfDay + ":" + minute);
                                mHour=hourOfDay;
                                if (mHour==0){
                                    mHour=12;
                                }
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();
            }
        });


        date1=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                editTextCal.setText("  "+dayOfMonth + " /" + (month+1)+"/"+year);
                day=dayOfMonth;
                yr=year;
                mth=month+1;
            }
        };

        dialog.show();

    }


    @Override
    public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {

    }

    @Override
    public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {

    }

    @Override
    public void onChartLongPressed(MotionEvent me) {

    }

    @Override
    public void onChartDoubleTapped(MotionEvent me) {

    }

    @Override
    public void onChartSingleTapped(MotionEvent me) {

    }

    @Override
    public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {

    }

    @Override
    public void onChartScale(MotionEvent me, float scaleX, float scaleY) {

    }

    @Override
    public void onChartTranslate(MotionEvent me, float dX, float dY) {

    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }
}








































    /*
    public void plot(String Xtitle, String Ytitle){

        for( int i=0;i<dataMinutesList.size();i++) {
            DataMinutes parameterG1 = new DataMinutes();
            parameterG1=dataMinutesList.get(i);

            Log.e(TAG, "plot: " + parameterG1.getElectricityConsumption() +" ****" + parameterG1.getMinutes());
            graphView = getActivity().findViewById(R.id.graph);

            x[i]=parameterG1.getElectricityConsumption();
            y[i]=parameterG1.getMinutes();
        }
        DataPoint[] dataPoints = new DataPoint[dataMinutesList.size()]; // declare an array of DataPoint objects with the same size as your list
        for (int i = 0; i < dataMinutesList.size(); i++) {
            // add new DataPoint object to the array for each of your list entries
            dataPoints[i] = new DataPoint(y[i],x[i]); // not sure but I think the second argument should be of type double
        }
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(dataPoints); // This one should be obvious right? :)


        series.setDrawDataPoints(true);
        series.setDataPointsRadius(6);

        graphView.addSeries(series);
        graphView.getViewport().setScalable(true);
        graphView.getViewport().setScrollable(true);
        graphView.getGridLabelRenderer().setHorizontalAxisTitle("X Label");
        graphView.getGridLabelRenderer().setVerticalAxisTitle("Y Label");
    }
    */