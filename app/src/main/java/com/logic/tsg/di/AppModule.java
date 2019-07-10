package com.logic.tsg.di;


import android.app.Application;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.logic.tsg.R;
import com.logic.tsg.api.WebService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.logic.tsg.util.Constant.BASE_URL;

@Module
public class AppModule {

    @Singleton
    @Provides
    public static Retrofit providesRetrofit(){
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY));


        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }


    @NonNull
    @Provides
    @Singleton
    WebService providesWebService(Retrofit retrofit){
        return retrofit.create(WebService.class);
    }


    @Singleton
    @Provides
    static RequestOptions  providesRequestOption(){
        return RequestOptions
                .placeholderOf(R.drawable.logo)
                .error(R.drawable.logo);
    }

    @Singleton
    @Provides
    static RequestManager providesGlideInstance(Application application, RequestOptions requestOptions){
        return Glide
                .with(application)
                .setDefaultRequestOptions(requestOptions);
    }

    @Singleton
    @Provides
    static Drawable providesDrawable(Application application){
        return ContextCompat.getDrawable(application,R.drawable.logo);
    }



}
