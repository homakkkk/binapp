package com.example.binapp.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import androidx.room.Room
import com.example.binapp.data.local.BinDatabase
import com.example.binapp.data.local.dao.CardDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApplication(app: Application): Context = app.applicationContext

    @Provides
    @Singleton
    fun provideSharedPreferences(context: Context): SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(context)

    @Provides
    @Singleton
    fun provideDatabase(context: Context): BinDatabase =
        Room.databaseBuilder(context, BinDatabase::class.java, "bins.db").build()

    @Provides
    @Singleton
    fun provideCardDao(database: BinDatabase): CardDao = database.cardDao()
}
/*
Результаты диагноза Помощника ИИ:
Added all required Android and Dagger imports
Fixed Room database builder syntax
Added proper Hilt component installation
*/