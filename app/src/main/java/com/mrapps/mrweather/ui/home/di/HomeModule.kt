package com.mrapps.mrweather.ui.home.di

import com.mrapps.mrweather.ui.home.search.SearchViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    viewModel { SearchViewModel(get()) }
}