package com.mrapps.mrweather.data.local.mappers

import com.mrapps.mrweather.data.local.entity.city.AdministrativeAreaData
import com.mrapps.mrweather.data.local.entity.city.CityEntity
import com.mrapps.mrweather.data.local.entity.city.CountryData
import com.mrapps.mrweather.data.local.entity.city.RegionData
import com.mrapps.mrweather.data.local.entity.search_history.SearchHistoryEntity
import com.mrapps.mrweather.data.local.transaction.SearchHistoryWithCityData
import com.mrapps.mrweather.domain.model.SearchHistory
import com.mrapps.mrweather.domain.model.SearchHistoryWithCity
import com.mrapps.mrweather.domain.model.location.AdministrativeArea
import com.mrapps.mrweather.domain.model.location.City
import com.mrapps.mrweather.domain.model.location.Country
import com.mrapps.mrweather.domain.model.location.Region


fun CityEntity.toCity(): City {
    return City(
        localizedName = localizedName,
        englishName = englishName,
        administrativeArea = administrativeAreaData.toAdministrativeArea(),
        country = country.toCountry(),
        region = region.toRegion(),
        id = id
    )
}

fun AdministrativeAreaData.toAdministrativeArea(): AdministrativeArea {
    return AdministrativeArea(
        id = id,
        localizedName = localizedName,
        englishName = englishName,
        localizedType = localizedType,
        englishType = englishType
    )
}

fun CountryData.toCountry(): Country {
    return Country(
        id = id,
        localizedName = localizedName,
        englishName = englishName
    )
}

fun RegionData.toRegion(): Region {
    return Region(
        id = id,
        localizedName = localizedName,
        englishName = englishName
    )
}

fun City.toCityEntity(): CityEntity {
    return CityEntity(
        localizedName = localizedName,
        englishName = englishName,
        administrativeAreaData = administrativeArea.toAdministrativeAreaData(),
        country = country.toCountryData(),
        region = region.toRegionData(),
        id = id
    )
}

fun AdministrativeArea.toAdministrativeAreaData(): AdministrativeAreaData {
    return AdministrativeAreaData(
        id = id,
        localizedName = localizedName,
        englishName = englishName,
        localizedType = localizedType,
        englishType = englishType,
    )
}

fun Country.toCountryData(): CountryData {
    return CountryData(
        id = id,
        localizedName = localizedName,
        englishName = englishName
    )
}

fun Region.toRegionData(): RegionData {
    return RegionData(
        id = id,
        localizedName = localizedName,
        englishName = englishName
    )
}

fun SearchHistoryWithCityData.toSearchHistoryWithCity(): SearchHistoryWithCity {
    return SearchHistoryWithCity(
        searchHistory = searchHistory.toSearchHistory(),
        city = city.toCity()
    )
}

fun SearchHistoryEntity.toSearchHistory(): SearchHistory {
    return SearchHistory(
        cityId = cityId,
        searchTime = searchTime
    )
}