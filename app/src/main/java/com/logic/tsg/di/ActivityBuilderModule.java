package com.logic.tsg.di;

import com.logic.tsg.ui.MainActivity;
import com.logic.tsg.ui.auth.LoginActivity;
import com.logic.tsg.ui.SplashActivity;;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
@Module
public abstract class ActivityBuilderModule {

    @ContributesAndroidInjector
    abstract SplashActivity contributeMainActiivity();

    @ContributesAndroidInjector(modules = {})
    abstract LoginActivity contributeLoginActivity();


    @ContributesAndroidInjector(modules = ParameterFragmentBuilder.class)
    abstract MainActivity contributeActivity();

}
