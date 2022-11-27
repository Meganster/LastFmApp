package com.appsfactory.testtask.injector

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.appsfactory.testtask.App
import com.appsfactory.testtask.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.Reusable
import java.io.File
import javax.inject.Named

@Module
class AppModule {

    @Provides
    fun provideContext(application: App): Context = application.applicationContext

    @Provides
    @Reusable
    @Named("cache")
    fun provideCacheDir(application: App): File = application.cacheDir

    @Provides
    @Named("lastfm_api_key")
    fun provideLastFmApiKey(): String = BuildConfig.LASTFM_API_KEY

    @Provides
    @Named("base_url")
    fun provideLastFmBaseUrl(): String = BuildConfig.LASTFM_BASE_URL

    @Provides
    @Reusable
    fun provideAppPreferences(application: App): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(application)
    }
}