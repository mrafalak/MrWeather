package com.mrapps.mrweather.domain.util

import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

fun convertStringToLocalDateTime(
    dateString: String,
    formatter: DateTimeFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME
): LocalDateTime {
    val offsetDateTime = OffsetDateTime.parse(dateString, formatter)
    return offsetDateTime.withOffsetSameInstant(ZoneOffset.UTC).toLocalDateTime()
}