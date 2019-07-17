package com.logic.tsg.viewmodel;

import android.util.Log;

import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.logic.tsg.Model.DataDaily;
import com.logic.tsg.Model.DataMinutes;
import com.logic.tsg.Model.DataMonthly;
import com.logic.tsg.Model.DataWeek;
import com.logic.tsg.Model.HardwareId;
import com.logic.tsg.Model.Header;
import com.logic.tsg.Model.DataHourly;
import com.logic.tsg.api.WebService;
import com.logic.tsg.networking.MainRepositery;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class MainViewModel extends ViewModel {

    private static final String TAG = "MainViewModel";

    private final WebService webService;
    private final MainRepositery repositery;
    List<DataWeek> dataWeekList=new ArrayList<>();

    MediatorLiveData<Header> liveData=new MediatorLiveData<>();
    CompositeDisposable disposable=new CompositeDisposable();
    MutableLiveData<List<DataMinutes>> liveDataMinutes =new MutableLiveData<>();
    MutableLiveData<List<DataHourly>> liveDataHourly =new MutableLiveData<>();
    MutableLiveData<List<DataDaily>> liveDataDaily =new MutableLiveData<>();
    MutableLiveData<List<DataDaily>> liveDataDaily2 =new MutableLiveData<>();
    MutableLiveData<List<DataWeek>> liveDataWeek =new MutableLiveData<>();
    MutableLiveData<List<DataWeek>> liveDataWeek2 =new MutableLiveData<>();
    MutableLiveData<List<DataMonthly>> liveDataMonthly =new MutableLiveData<>();
    MutableLiveData<List<DataDaily>> newLiveData=new MutableLiveData<>();
    MutableLiveData<List<DataDaily>> mutableLiveData=new MutableLiveData<>();
    MutableLiveData<Boolean> loading =new MutableLiveData<>();

    DataWeek dataWeek=new DataWeek();



    @Inject
    public MainViewModel(WebService webService, MainRepositery repositery) {
        this.webService = webService;
        this.repositery = repositery;
    }


    public void header(HardwareId hIdList){

        repositery.getHeader(hIdList).subscribe(new Observer<Header>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Header header) {

                Log.e(TAG, "onNext: "+header );
                liveData.postValue(header);
            }

            @Override
            public void onError(Throwable e) {

                Log.e(TAG, "onError: "+e );
            }

            @Override
            public void onComplete() {


            }
        });
    }


    public void getParamterMinutes(HardwareId hardwareId , String limit, int month
    , int year, int date, int hour){

        repositery.getMinuetly(hardwareId,limit,month,year,date,hour).subscribe(
                new Observer<List<DataMinutes>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<DataMinutes> dataHourlies) {

                        Log.e(TAG, "onNext: "+ dataHourlies);

                        liveDataMinutes.postValue(dataHourlies);
                        loading.setValue(true);
                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.e(TAG, "onError: "+e );
                        loading.setValue(false);
                    }

                    @Override
                    public void onComplete() {

                    }
                }
        );

    }

    public MutableLiveData<List<DataMinutes>> getLiveDataMinutes(){
        return liveDataMinutes;
    }

    public MutableLiveData<Boolean> getLoading(){
        return loading;
    }

    /*
            Daily *------------------------------------------------------------------
     */

    public void getParameterDaily(HardwareId hardwareId , String limit, int month
            , int year){
        liveDataDaily=new MutableLiveData<>();
        repositery.getParameterDaily(hardwareId,limit,month,year)
                .subscribe(new Observer<List<DataDaily>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<DataDaily> dataDailies) {

                        Log.e(TAG, "onNext: "+dataDailies );
                        newLiveData.postValue(dataDailies);
                        loading.setValue(true);
                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.e(TAG, "onError: "+e );
                        loading.setValue(false);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public MutableLiveData<List<DataDaily>> getLiveDataDaily(){
        return newLiveData;
    }




    public void getParameterDaily2(HardwareId hardwareId , String limit, int month
            , int year){
        repositery.getParameterDaily2(hardwareId,limit,month,year)
                .subscribe(new Observer<List<DataDaily>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<DataDaily> dataDailies) {

                        Log.e(TAG, "onNext: "+dataDailies );
                        mutableLiveData.postValue(dataDailies);
                        loading.setValue(true);
                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.e(TAG, "onError: "+e );
                        loading.setValue(false);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public MutableLiveData<List<DataDaily>> getLiveDataDaily2(){
        return mutableLiveData;
    }



    /*
                Hourly *-----------------------------------------------------------------
     */

    public void getParameterHourly(HardwareId hardwareId , String limit, int month
            , int year, int date){

        repositery.getParameterHourly(hardwareId,limit,month,year,date)
                .subscribe(new Observer<List<DataHourly>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<DataHourly> dataHourlies) {

                        Log.e(TAG, "onNext: "+dataHourlies );
                        liveDataHourly.setValue(dataHourlies);
                        loading.setValue(true);
                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.e(TAG, "onError: "+e );
                        loading.setValue(true);

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    public MutableLiveData<List<DataHourly>> getLiveDataHourly(){
        return liveDataHourly;
    }

    /*
                Weekly *------------------------------------------------------------------
     */

    public void getParameterWeekly(HardwareId hardwareId , String limit, int month
            , int year){

        repositery.getParameterWeekly(hardwareId,limit,month,year)
                .subscribe(new Observer<JsonArray>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(JsonArray jsonElements) {

                        for (int i=0 ;i<jsonElements.size();i++){
                            Log.e(TAG, "Elem"+jsonElements.get(i) );

                            JsonArray jsonArray=jsonElements.get(i).getAsJsonArray();

                            for (int j=0;j<jsonArray.size();j++){
                                JsonObject jsonObject=jsonArray.get(j).getAsJsonObject();

                                Gson gson = new Gson();
                                Log.e(TAG, "onNext: "+jsonObject.get("index") );
                                dataWeek=new DataWeek();
                                    dataWeek.setIndex(jsonObject.get("index").toString());
                                    dataWeek.setCreatedAt(jsonObject.get("created_at").toString());
                                    dataWeek.setVbn(jsonObject.get("vbn").getAsInt());
                                    dataWeek.setVbr(jsonObject.get("vbr").getAsInt());
                                    dataWeek.setVrn(jsonObject.get("vrn").getAsInt());
                                    dataWeek.setVyn(jsonObject.get("vyn").getAsInt());
                                    dataWeek.setVry(jsonObject.get("vry").getAsInt());
                                    dataWeek.setVyb(jsonObject.get("vyb").getAsInt());
                                    dataWeek.setIb(jsonObject.get("ib").getAsInt());
                                    dataWeek.setIr(jsonObject.get("ir").getAsInt());
                                    dataWeek.setIy(jsonObject.get("iy").getAsInt());
                                    dataWeek.setElectricityConsumption(jsonObject.get("electricity_consumption").getAsInt());
                                    dataWeek.setCurrentbilling(jsonObject.get("currentbilling").getAsInt());
                                    dataWeekList.add(dataWeek);
                                Log.e(TAG, "onNext: E "+dataWeek.getElectricityConsumption() );
                            }
                          //  BasketId basketId1 = gson.fromJson(jsonObject, BasketId.class);
                           // basketIdList.add(basketId1);
                        }
                        Log.e(TAG, "onNext: "+dataWeekList.get(0) );
                        liveDataWeek.postValue(dataWeekList);

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: "+e);

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    public MutableLiveData<List<DataWeek>> getLiveDataWeek(){

        return liveDataWeek;
    }



    /*
                Monthy *-------------------------------------------------------------
     */

    public void getParameterMonthly(HardwareId hardwareId , String limit
            , int year){

        repositery.getParameterMonthy(hardwareId,limit,year)
                .subscribe(new Observer<List<DataMonthly>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<DataMonthly> dataMonthlies) {

                        Log.e(TAG, "onNext: "+dataMonthlies );
                        liveDataMonthly.setValue(dataMonthlies);
                        loading.setValue(true);
                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.e(TAG, "onError: "+e);
                        loading.setValue(true);

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    public MutableLiveData<List<DataMonthly>> getLiveDataMonthly(){

        return liveDataMonthly;
    }

    public MutableLiveData<Header> getHeaderLivedata(){
        return liveData;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
