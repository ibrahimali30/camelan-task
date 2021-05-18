package com.ibrahim.camelan_task.foursquare.presentation.di

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.ibrahim.camelan_task.base.AppPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class ShakespeareansModule {

    @Provides
    fun getPreferences(context: Application): SharedPreferences {
        return context.getSharedPreferences("Preferences", MODE_PRIVATE);
    }

    @Provides
    fun provideAppPreferences(sharedPreferences: SharedPreferences): AppPreferences {
        return AppPreferences(sharedPreferences)
    }

}