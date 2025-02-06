package com.mrapps.mrweather.data.di

import com.mrapps.mrweather.data.repository.CityRepositoryImpl
import com.mrapps.mrweather.domain.CityRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<CityRepository> { CityRepositoryImpl(get()) }
}