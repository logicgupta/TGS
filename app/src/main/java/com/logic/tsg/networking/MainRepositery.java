package com.logic.tsg.networking;

import com.google.gson.JsonArray;
import com.logic.tsg.Model.DataDaily;
import com.logic.tsg.Model.DataMinutes;
import com.logic.tsg.Model.DataMonthly;
import com.logic.tsg.Model.DataWeek;
import com.logic.tsg.Model.HardwareId;
import com.logic.tsg.Model.Header;
import com.logic.tsg.Model.DataHourly;
import com.logic.tsg.api.WebService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainRepositery {


    private final WebService webService;

    @Inject
    public MainRepositery(WebService webService) {
        this.webService = webService;
    }

    public Observable<Header> getHeader(HardwareId hIds){
        return webService.getHeader(hIds)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /*
                Minutes **-*-*-*-*-*-*
     */

    public Observable<List<DataMinutes>> getMinuetly(HardwareId hardwareId
    , String limit, int month, int year, int date, int hour){

        return webService.getParameterMinutely(hardwareId
                ,limit,month,year,date,hour)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }

      /*
                Daily
     */


    public Observable<List<DataDaily>> getParameterDaily(HardwareId hardwareId
            , String limit, int month, int year){

        return webService.getParameterDaily(hardwareId
                ,limit,month,year)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }

    public Observable<List<DataDaily>> getParameterDaily2(HardwareId hardwareId
            , String limit, int month, int year){

        return webService.getParameterDaily2(hardwareId
                ,limit,month,year)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }




    /*
                Hourly **-*-*-*-*-*-*
     */

    public Observable<List<DataHourly>> getParameterHourly(HardwareId hardwareId
            , String limit, int month, int year, int date){

        return webService.getParameterHourly(hardwareId
                ,limit,month,year,date)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }


    /*
                Weekly **-*-*-*-*-*-*
     */

    public Observable<JsonArray> getParameterWeekly(HardwareId hardwareId
            , String limit, int month, int year){

        return webService.getParameterWeekly(hardwareId
                ,limit,month,year)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }



    /*
                Monthly **-*-*-*-*-*-*
     */

    public Observable<List<DataMonthly>> getParameterMonthy(HardwareId hardwareId
            , String limit, int year){

        return webService.getParameterMonthly(hardwareId
                ,limit,year)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }

}
