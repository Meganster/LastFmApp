package com.appsfactory.testtask.injector

import android.content.Context
import androidx.viewbinding.BuildConfig
import com.appsfactory.testtask.data.LastFmInterceptor
import com.appsfactory.testtask.data.LastFmServiceApi
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import javax.inject.Named

@Module
class NetworkModule {

    @Reusable
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        if (BuildConfig.DEBUG) {
            level = HttpLoggingInterceptor.Level.BASIC
        }
    }

    @Reusable
    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Reusable
    @Provides
    fun provideLastFmInterceptor(@Named("lastfm_api_key") apikey: String): LastFmInterceptor = LastFmInterceptor(apikey)

    @Reusable
    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        lastFmInterceptor: LastFmInterceptor,
        context: Context,
        @Named("cache") cache: File
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(lastFmInterceptor)
        .addInterceptor(loggingInterceptor)
        .addInterceptor(ChuckerInterceptor(context))
        .cache(Cache(cache, 10 * 1024 * 1024))
        .build()

    @Reusable
    @Provides
    fun provideLastFmServiceApi(
        @Named("base_url") baseUrl: String,
        okHttpClient: OkHttpClient,
        gson: Gson
    ): LastFmServiceApi = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(okHttpClient)
        .build()
        .create(LastFmServiceApi::class.java)
}