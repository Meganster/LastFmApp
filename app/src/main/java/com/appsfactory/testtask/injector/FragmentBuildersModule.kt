package com.appsfactory.testtask.injector

import androidx.lifecycle.ViewModel
import com.appsfactory.testtask.ui.album.details.DetailsFragment
import com.appsfactory.testtask.ui.album.details.DetailsViewModel
import com.appsfactory.testtask.ui.album.favorites.FavoriteAlbumsFragment
import com.appsfactory.testtask.ui.album.favorites.FavoriteAlbumsViewModel
import com.appsfactory.testtask.ui.album.top.TopAlbumsFragment
import com.appsfactory.testtask.ui.album.top.TopAlbumsViewModel
import com.appsfactory.testtask.ui.search.SearchFragment
import com.appsfactory.testtask.ui.search.SearchViewModel
import com.appsfactory.testtask.ui.splash.SplashFragment
import com.appsfactory.testtask.ui.splash.SplashViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun screenSplash(): SplashFragment

    @ContributesAndroidInjector
    abstract fun screenFavoriteAlbums(): FavoriteAlbumsFragment

    @ContributesAndroidInjector
    abstract fun screenSearch(): SearchFragment

    @ContributesAndroidInjector
    abstract fun screenTopAlbums(): TopAlbumsFragment

    @ContributesAndroidInjector
    abstract fun screenDetails(): DetailsFragment

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    abstract fun provideSplashViewModel(viewModel: SplashViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FavoriteAlbumsViewModel::class)
    abstract fun provideFavoriteAlbumViewModel(viewModel: FavoriteAlbumsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    abstract fun provideSearchViewModel(viewModel: SearchViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TopAlbumsViewModel::class)
    abstract fun provideTopAlbumsViewModel(viewModel: TopAlbumsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailsViewModel::class)
    abstract fun provideDetailsViewModel(viewModel: DetailsViewModel): ViewModel
}