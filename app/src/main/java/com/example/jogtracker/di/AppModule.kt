package com.example.jogtracker.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.example.jogtracker.db.RunningDataBase
import com.example.jogtracker.other.Constants.RUNNING_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRunningDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        RunningDataBase::class.java,
        RUNNING_DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun provideRunDao(db: RunningDataBase) = db.getRunDao()

    @Singleton
    @Provides
    fun provideSharedPreferences(@ApplicationContext app:Context) =
        app.getSharedPreferences("sharedPref", Context.MODE_PRIVATE)


    @Singleton
    @Provides
    fun provideName(sharedPref: SharedPreferences) = sharedPref.getString("KEY_NAME","") ?: ""

    @Singleton
    @Provides
    fun provideWeight(sharedPref: SharedPreferences) = sharedPref.getFloat("KEY_WEIGHT",80f) ?: ""

    @Singleton
    @Provides
    fun provideFirstTimeToggle(sharedPref: SharedPreferences) =
        sharedPref.getBoolean(
            "KEY_FIRST_TIME_TOGGLE",true)


}