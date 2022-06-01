package com.bijov1apps.domain.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

private const val SHORT_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'"
private const val FULL_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
private const val VIEW_DATE_FORMAT = "dd.MM.yyyy"

object DateTimeUtils {

    private val viewDateFormed =
        SimpleDateFormat(VIEW_DATE_FORMAT, Locale.US).apply {
            timeZone = TimeZone.getTimeZone("UTC")
        }

    fun formatPublishedDate(date: String?): String {
        if (date.isNullOrEmpty()) {
            return date.orEmpty()
        }

        val currentFormat = when {
            isValidShortDate(date) -> SHORT_DATE_FORMAT
            isValidFullDate(date) -> FULL_DATE_FORMAT
            else -> FULL_DATE_FORMAT
        }

        val currentDateFormat =
            SimpleDateFormat(currentFormat, Locale.US).apply {
                timeZone = TimeZone.getTimeZone("UTC")
            }
        return viewDateFormed.format(currentDateFormat.parse(date))

    }

    private fun isValidShortDate(date: String?): Boolean {
        try {
            SimpleDateFormat(SHORT_DATE_FORMAT, Locale.US).parse(date)
        } catch (e: ParseException) {
            return false
        }
        return true
    }

    private fun isValidFullDate(date: String?): Boolean {
        try {
            SimpleDateFormat(FULL_DATE_FORMAT, Locale.US).parse(date)
        } catch (e: ParseException) {
            return false
        }
        return true
    }
}