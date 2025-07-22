package com.example.binapp.di

import androidx.lifecycle.ViewModel
import com.example.binapp.presentation.viewmodels.HistoryViewModel
import com.example.binapp.presentation.viewmodels.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.multibindings.IntoMap

@Module
@InstallIn(ViewModelComponent::class)
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    @ViewModelScoped
    abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HistoryViewModel::class)
    @ViewModelScoped
    abstract fun bindHistoryViewModel(historyViewModel: HistoryViewModel): ViewModel
}
/*
Результаты диагноза Помощника ИИ:
Added proper ViewModel component installation
Added required Hilt annotations
Fixed ViewModel binding syntax
*/