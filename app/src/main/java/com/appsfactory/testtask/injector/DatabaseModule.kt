package com.appsfactory.testtask.injector

import android.content.Context
import android.os.Debug
import androidx.room.Room
import com.appsfactory.testtask.db.TestTaskDb
import com.appsfactory.testtask.db.dao.ArtistDao
import com.appsfactory.testtask.db.dao.DetailsAlbumDao
import com.appsfactory.testtask.db.dao.DetailsAlbumWithTracksDao
import com.appsfactory.testtask.db.dao.TrackDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(context: Context): TestTaskDb {
        val builder = Room
            .databaseBuilder(context, TestTaskDb::class.java, "testtask.db")
            .fallbackToDestructiveMigration()

        if (Debug.isDebuggerConnected()) {
            builder.allowMainThreadQueries()
        }
        return builder.build()
    }

    @Provides
    fun provideArtistDao(db: TestTaskDb): ArtistDao = db.artistDao()

    @Provides
    fun provideAlbumInfoDao(db: TestTaskDb): DetailsAlbumDao = db.detailsAlbumDao()

    @Provides
    fun provideTrackDao(db: TestTaskDb): TrackDao = db.trackDao()

    @Provides
    fun provideAlbumInfoWithTracksDao(db: TestTaskDb): DetailsAlbumWithTracksDao = db.detailsAlbumWithTracksDao()
}