package com.ibrahim.camelan_task.foursquare.presentation.di

import android.app.Application
import androidx.room.Room
import com.ibrahim.camelan_task.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    @Provides
    fun provideLocalDatabase(context: Application): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "AppDatabase")
            .fallbackToDestructiveMigration()
            .build()
    }


}