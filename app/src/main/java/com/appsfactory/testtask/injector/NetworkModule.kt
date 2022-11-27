package com.appsfactory.testtask.injector

import com.appsfactory.testtask.data.LastFmInterceptor
import com.appsfactory.testtask.data.LastFmService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import javax.inject.Named
import javax.inject.Singleton
import com.appsfactory.testtask.BuildConfig

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        if (BuildConfig.DEBUG) {
            level = HttpLoggingInterceptor.Level.BASIC
        }
    }

    @Singleton
    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Singleton
    @Provides
    fun provideLastFmInterceptor(@Named("lastfm_api_key") apikey: String) = LastFmInterceptor(apikey)

    @Singleton
    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        lastFmInterceptor: LastFmInterceptor,
        @Named("cache") cache: File
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(lastFmInterceptor)
        .addInterceptor(loggingInterceptor)
        .cache(Cache(cache, 10 * 1024 * 1024))
        .build()

    @Singleton
    @Provides
    fun provideLastFmService(
        @Named("base_url") baseUrl: String,
        okHttpClient: OkHttpClient,
        gson: Gson
    ): LastFmService = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(okHttpClient)
        .build()
        .create(LastFmService::class.java)
}