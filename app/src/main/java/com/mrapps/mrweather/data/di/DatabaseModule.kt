package com.mrapps.mrweather.data.di

import org.koin.dsl.module
import org.koin.android.ext.koin.androidContext
import androidx.room.Room
import com.mrapps.mrweather.data.local.AppDatabase
import com.mrapps.mrweather.data.local.LocalDataConfig

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            LocalDataConfig.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }
    single { get<AppDatabase>().cityDao() }
    single { get<AppDatabase>().searchHistoryDao() }
}