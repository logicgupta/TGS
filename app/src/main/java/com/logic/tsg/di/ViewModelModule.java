package com.logic.tsg.di;

import androidx.lifecycle.ViewModel;

import com.logic.tsg.networking.MainRepositery;
import com.logic.tsg.viewmodel.LoginViewModel;
import com.logic.tsg.viewmodel.MainViewModel;

import dagger.Binds;
import dagger.MapKey;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel.class)
    abstract ViewModel bindLoginViewModel(LoginViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel.class)
    abstract ViewModel bindMainViewModel(MainViewModel viewModel);
}
