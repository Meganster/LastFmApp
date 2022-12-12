package com.appsfactory.testtask.injector

import android.content.Context
import com.appsfactory.testtask.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.io.File
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    @Named("cache")
    fun provideCacheDir(@ApplicationContext context: Context): File = context.cacheDir

    @Provides
    @Singleton
    @Named("lastfm_api_key")
    fun provideLastFmApiKey(): String = BuildConfig.LASTFM_API_KEY

    @Provides
    @Singleton
    @Named("base_url")
    fun provideLastFmBaseUrl(): String = BuildConfig.LASTFM_BASE_URL
}