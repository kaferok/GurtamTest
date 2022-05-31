package com.bijov1apps.domain.utils

import java.text.SimpleDateFormat
import java.util.*

object DateTimeUtils {

    private val viewDateFormed =
        SimpleDateFormat("dd.MM.yyyy", Locale.US).apply {
            timeZone = TimeZone.getTimeZone("UTC")
        }

    fun formatPublishedDate(date: String?): String {
        if (date != null) {
            val currentFormat =
                SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US).apply {
                    timeZone = TimeZone.getTimeZone("UTC")
                }
            return viewDateFormed.format(currentFormat.parse(date))
        }
        return ""
    }
}