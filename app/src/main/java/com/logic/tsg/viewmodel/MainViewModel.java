package com.logic.tsg.viewmodel;

import android.util.Log;

import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.logic.tsg.Model.DataDaily;
import com.logic.tsg.Model.DataMinutes;
import com.logic.tsg.Model.DataMonthly;
import com.logic.tsg.Model.DataWeek;
import com.logic.tsg.Model.HardwareId;
import com.logic.tsg.Model.Header;
import com.logic.tsg.Model.DataHourly;
import com.logic.tsg.api.WebService;
import com.logic.tsg.networking.MainRepositery;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class MainViewModel extends ViewModel {

    private static final String TAG = "MainViewModel";

    private final WebService webService;
    private final MainRepositery repositery;

    MediatorLiveData<Header> liveData=new MediatorLiveData<>();
    CompositeDisposable disposable=new CompositeDisposable();
    MutableLiveData<List<DataMinutes>> liveDataMinutes =new MutableLiveData<>();
    MutableLiveData<List<DataHourly>> liveDataHourly =new MutableLiveData<>();
    MutableLiveData<List<DataDaily>> liveDataDaily =new MutableLiveData<>();
    MutableLiveData<List<DataWeek>> liveDataWeek =new MutableLiveData<>();
    MutableLiveData<List<DataMonthly>> liveDataMonthly =new MutableLiveData<>();
    MutableLiveData<Boolean> loading =new MutableLiveData<>();


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
        repositery.getParameterDaily(hardwareId,limit,month,year)
                .subscribe(new Observer<List<DataDaily>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<DataDaily> dataDailies) {

                        Log.e(TAG, "onNext: "+dataDailies );
                        liveDataDaily.postValue(dataDailies);
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
        return liveDataDaily;
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
                .subscribe(new Observer<List<DataWeek>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<DataWeek> dataWeeks) {

                        Log.e(TAG, "onNext: "+dataWeeks );
                        liveDataWeek.setValue(dataWeeks);
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
