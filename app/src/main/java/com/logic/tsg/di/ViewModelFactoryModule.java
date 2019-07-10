package com.logic.tsg.di;


import androidx.lifecycle.ViewModelProvider;

import com.logic.tsg.ViewModelProvidersFactory;

import dagger.Binds;
import dagger.Module;

@Module(includes = {ViewModelModule.class})
public abstract class ViewModelFactoryModule {

    @Binds
    public abstract ViewModelProvider.Factory bindViewModelFactory (ViewModelProvidersFactory factory);

}
