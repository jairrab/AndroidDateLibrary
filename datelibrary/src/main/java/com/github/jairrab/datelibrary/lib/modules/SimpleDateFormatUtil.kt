package com.github.jairrab.datelibrary.lib.modules

import com.github.jairrab.datelibrary.DateFormat.DATE_ISO
import java.text.SimpleDateFormat
import java.util.*

internal class SimpleDateFormatUtil(
    private val checkIsoFormat: CheckIsoFormat
) {
    private var localizedFormatter = SimpleDateFormat(DATE_ISO, Locale.getDefault())
    private var localizedParser = SimpleDateFormat(DATE_ISO, Locale.getDefault())

    private val isoParser = SimpleDateFormat(DATE_ISO, Locale.US)
    private val nonIsoParser = SimpleDateFormat(DATE_ISO, Locale.US)
    private val isoFormatter = SimpleDateFormat(DATE_ISO, Locale.US)
    private val nonIsoFormatter = SimpleDateFormat(DATE_ISO, Locale.US)

    fun updateLocale(locale: Locale) {
        localizedParser = SimpleDateFormat(DATE_ISO, locale)
        localizedFormatter = SimpleDateFormat(DATE_ISO, locale)
    }

    @Synchronized
    fun getDateTextIso(date: Date, pattern: String): String {
        return if (checkIsoFormat.check(pattern)) {
            if (pattern == DATE_ISO) {
                isoFormatter.format(date)
            } else {
                nonIsoFormatter.applyPattern(pattern)
                nonIsoFormatter.format(date)
            }
        } else {
            localizedFormatter.applyPattern(pattern)
            localizedFormatter.format(date)
        }
    }

    fun getCalendar(date: String, pattern: String): Calendar {
        return if (checkIsoFormat.check(pattern)) {
            if (pattern == DATE_ISO) {
                val calendar = Calendar.getInstance()
                calendar.time = getDateParserIso(date)
                calendar
            } else {
                val calendar = Calendar.getInstance()
                calendar.time = getParser(date, pattern)
                calendar
            }
        } else {
            val calendar = Calendar.getInstance()
            calendar.time = getLocalizedParser(date, pattern)
            calendar
        }
    }

    fun getCalendar(date: String): Calendar {
        val calendar = Calendar.getInstance()
        calendar.time = getDateParserIso(date)
        return calendar
    }

    @Synchronized
    fun getDateParserIso(date: String): Date {
        return isoParser.parse(date) ?: Date()
    }

    fun getDateParsed(date: String, pattern: String): Date {
        return if (checkIsoFormat.check(pattern)) {
            if (pattern == DATE_ISO) {
                getDateParserIso(date)
            } else {
                getParser(date, pattern)
            }
        } else {
            getLocalizedParser(date, pattern)
        }
    }

    @Synchronized
    private fun getParser(date: String, pattern: String): Date {
        nonIsoParser.applyPattern(pattern)
        return nonIsoParser.parse(date) ?: Date()
    }

    @Synchronized
    private fun getLocalizedParser(date: String, pattern: String): Date {
        localizedParser.applyPattern(pattern)
        return localizedParser.parse(date) ?: Date()
    }
}