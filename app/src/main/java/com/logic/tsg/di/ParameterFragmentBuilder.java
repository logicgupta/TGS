package com.logic.tsg.di;

import com.logic.tsg.ui.main.ComparisionGraphActivity;
import com.logic.tsg.ui.main.ParameterGraphActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public  abstract class ParameterFragmentBuilder {

    @ContributesAndroidInjector
    abstract ComparisionGraphActivity contributeComparisionGraph();


    @ContributesAndroidInjector
    abstract ParameterGraphActivity contributeParameterGraph();

}
