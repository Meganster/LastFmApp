package com.appsfactory.testtask.injector

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.MapKey
import dagger.Module
import dagger.Provides
import javax.inject.Provider
import kotlin.reflect.KClass

@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)

@Module
class ViewModelFactoryModule {

    @Provides
    fun provideViewModelFactory(map: MutableMap<Class<out ViewModel>, Provider<ViewModel>>): ViewModelProvider.Factory =
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return map[modelClass]?.get() as T
            }
        }
}