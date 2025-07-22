package com.example.binapp.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().setLenient().create()

    @Provides
    @Singleton
    fun provideGsonConverterFactory(gson: Gson): Converter.Factory = 
        GsonConverterFactory.create(gson)

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply { 
            level = HttpLoggingInterceptor.Level.BODY 
        }

    @Provides
    @Singleton
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(
        client: OkHttpClient, 
        converterFactory: Converter.Factory
    ): Retrofit =
        Retrofit.Builder()
            .client(client)
            .baseUrl("https://lookup.binlist.net/")
            .addConverterFactory(converterFactory)
            .build()

    @Provides
    @Singleton
    fun provideBinListService(retrofit: Retrofit): BinListService =
        retrofit.create(BinListService::class.java)
}
/*
Результаты диагноза Помощника ИИ:
Added all required network-related imports
Fixed logging interceptor configuration
Added proper Retrofit builder configuration
Fixed service creation syntax
*/
