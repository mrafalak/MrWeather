package com.mrapps.mrweather.data.di

import com.mrapps.mrweather.BuildConfig
import com.mrapps.mrweather.Config
import com.mrapps.mrweather.data.remote.AuthInterceptor
import com.mrapps.mrweather.data.remote.WeatherApi
import com.mrapps.mrweather.data.remote.fake.FakeWeatherApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import org.koin.dsl.module

val networkModule = module {
    single {
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }
    single { AuthInterceptor(BuildConfig.API_KEY) }
    single {
        OkHttpClient.Builder()
            .addInterceptor(get<AuthInterceptor>())
            .addInterceptor(get<HttpLoggingInterceptor>())
            .build()
    }
    single {
        if(BuildConfig.USE_FAKE_API) {
            FakeWeatherApi()
        } else {
            Retrofit.Builder()
                .baseUrl(Config.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(get())
                .build()
                .create(WeatherApi::class.java)
        }
    }
}