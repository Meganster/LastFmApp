package com.appsfactory.testtask.injector

import com.appsfactory.testtask.App
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        DatabaseModule::class,
        ViewModelFactoryModule::class,
        NetworkModule::class,
        AppModule::class,
        MainActivityModule::class
    ]
)
interface AppComponent : AndroidInjector<App> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<App>()
}