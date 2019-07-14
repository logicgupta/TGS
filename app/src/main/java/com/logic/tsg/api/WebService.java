package com.logic.tsg.api;
import com.logic.tsg.Model.DataDaily;
import com.logic.tsg.Model.DataMinutes;
import com.logic.tsg.Model.DataMonthly;
import com.logic.tsg.Model.DataWeek;
import com.logic.tsg.Model.HardwareId;
import com.logic.tsg.Model.Header;
import com.logic.tsg.Model.DataHourly;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface WebService {

    //@GET("header/")
    @HTTP(method = "POST", path = "header/", hasBody = true)
    Observable<Header> getHeader(@Body HardwareId hIds);

    // /graph?data_limit=hourly&month=5&year=2019&date=16&hour=20


    @POST("graph/")
    Observable<List<DataMinutes>> getParameterMinutely(
            @Body HardwareId hIds,
            @Query("data_limit") String limit
            ,@Query("month") int month
            ,@Query("year") int year
            ,@Query("date") int date,
            @Query("hour") int hour
    );


    @POST("graph/")
    Observable<List<DataHourly>> getParameterHourly(
            @Body HardwareId hIds,
            @Query("data_limit") String limit
            ,@Query("month") int month
            ,@Query("year") int year
            ,@Query("date") int date
    );

    @POST("graph/")
    Observable<List<DataDaily>> getParameterDaily(
            @Body HardwareId hIds,
            @Query("data_limit") String limit
            ,@Query("month") int month
            ,@Query("year") int year
    );

    @POST("graph/")
    Observable<List<DataDaily>> getParameterDaily2(
            @Body HardwareId hIds,
            @Query("data_limit") String limit
            ,@Query("month") int month
            ,@Query("year") int year
    );

    @POST("graph/")
    Observable<List<DataWeek>> getParameterWeekly(
            @Body HardwareId hIds,
            @Query("data_limit") String limit
            ,@Query("month") int month
            ,@Query("year") int year
    );

    @POST("graph/")
    Observable<List<DataMonthly>> getParameterMonthly(
            @Body HardwareId hIds,
            @Query("data_limit") String limit
            ,@Query("year") int year

    );
    @POST("graph/")
    Observable<List<DataWeek>> getParameterWeekly2(
            @Body HardwareId hIds,
            @Query("data_limit") String limit
            ,@Query("month") int month
            ,@Query("year") int year
    );


}
