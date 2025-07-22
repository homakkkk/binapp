package com.example.binapp.di

import com.example.binapp.data.remote.BinListService
import com.example.binapp.data.repository.DefaultCardRepository
import com.example.binapp.data.repository.ICardRepository
import com.example.binapp.data.local.dao.CardDao
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideBinListService(retrofit: Retrofit): BinListService =
        retrofit.create(BinListService::class.java)

    @Provides
    @Singleton
    fun provideCardRepository(
        binListService: BinListService,
        cardDao: CardDao
    ): ICardRepository =
        DefaultCardRepository(binListService, cardDao)

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://lookup.binlist.net/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()
}
/*
Результаты диагноза Помощника ИИ:
Added all required Retrofit and OkHttp imports
Fixed service creation syntax
Added proper time unit handling for OkHttp
Added Gson converter factory
*/