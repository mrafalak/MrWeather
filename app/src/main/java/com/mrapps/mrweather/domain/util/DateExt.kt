package com.mrapps.mrweather.domain.util

import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.time.*

fun convertStringToOffsetDateTime(dateString: String, epoch: Int): LocalDateTime {
    return try {
        OffsetDateTime.parse(dateString, DateTimeFormatter.ISO_OFFSET_DATE_TIME)
            .toLocalDateTime()
    } catch (_: DateTimeParseException) {
        Instant.ofEpochSecond(epoch.toLong())
            .atZone(ZoneId.systemDefault())
            .toLocalDateTime()
    }
}

fun LocalDateTime.formatTimeToString(): String {
    val formatter = DateTimeFormatter.ofPattern("HH:mm")
    return this.format(formatter)
}