package com.example.todocompose.presentation.di

import android.app.Application
import androidx.room.Room
import com.example.todocompose.data.DATABASE_NAME
import com.example.todocompose.data.source.AppDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideAppDatabase(application: Application): AppDataBase {
        return Room.databaseBuilder(
            application,
            AppDataBase::class.java,
            DATABASE_NAME
        ).build()
    }

}