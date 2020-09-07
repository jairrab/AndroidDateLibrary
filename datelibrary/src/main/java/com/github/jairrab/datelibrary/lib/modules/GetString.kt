package com.github.jairrab.datelibrary.lib.modules

import com.github.jairrab.datelibrary.DateFormat
import com.github.jairrab.datelibrary.DateFormat.DATE_ISO
import com.github.jairrab.datelibrary.DateFrequency
import com.github.jairrab.datelibrary.PeriodSelection.START_OF_PERIOD
import com.github.jairrab.datelibrary.lib.DateLibrary
import java.text.SimpleDateFormat
import java.util.*

internal class GetString(private val isoFormat: CheckIsoFormat) {
    var simpleDateFormatLocalized = SimpleDateFormat(DATE_ISO, Locale.getDefault())
    private val simpleDateFormat = SimpleDateFormat(DATE_ISO, Locale.US)

    fun getToday(dateLibrary: DateLibrary): String = dateLibrary.run {
        return fromDate(dateLibrary, Date(), DATE_ISO)
    }

    fun getTodayTrimmed(dateLibrary: DateLibrary): String = dateLibrary.run {
        return fromDate(dateLibrary, Date(), DateFormat.DATE_ISO_TRIMMED)
    }

    fun fromDate(dateLibrary: DateLibrary, date: Date): String = dateLibrary.run {
        return getStringFromDate(date, DATE_ISO)
    }

    fun fromDate(dateLibrary: DateLibrary, date: Date, pattern: String): String = dateLibrary.run {
        return getStringFromDate(date, pattern)
    }

    fun fromDateTrimmed(dateLibrary: DateLibrary, date: Date): String = dateLibrary.run {
        return getStringFromDate(date, DateFormat.DATE_ISO_TRIMMED)
    }

    fun fromLong(dateLibrary: DateLibrary, timeInMills: Long): String = dateLibrary.run {
        return getStringFromDate(Date(timeInMills), DATE_ISO)
    }

    fun today(dateLibrary: DateLibrary, pattern: String): String {
        return fromDate(dateLibrary, Date(), pattern)
    }

    fun transform(
        dateLibrary: DateLibrary,
        date: String,
        pattern: String
    ): String = dateLibrary.run {
        return getStringFromDate(dateLibrary.getDate(date), pattern)
    }

    fun trim(dateLibrary: DateLibrary, date: String): String = dateLibrary.run {
        return getStringFromDate(dateLibrary.getDate(date), DateFormat.DATE_ISO_TRIMMED)
    }

    fun fromTime(
        dateLibrary: DateLibrary,
        hour: Int,
        minute: Int,
        seconds: Int,
        pattern: String
    ): String = dateLibrary.run {
        val calendar1 = Calendar.getInstance()
        calendar1.set(Calendar.HOUR_OF_DAY, hour)
        calendar1.set(Calendar.MINUTE, minute)
        calendar1.set(Calendar.SECOND, seconds)
        calendar1.set(Calendar.MILLISECOND, 0)
        return getStringFromDate(calendar1.time, pattern)
    }

    fun fromTime(
        dateLibrary: DateLibrary,
        date: String,
        hour: Int,
        minute: Int,
        seconds: Int
    ): String = dateLibrary.run {
        val calendar = getCalendarFromString(date, DATE_ISO)
        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, minute)
        calendar.set(Calendar.SECOND, seconds)
        calendar.set(Calendar.MILLISECOND, 0)
        return getStringFromDate(calendar.time, DATE_ISO)
    }

    fun getDayFromFrequency(dateLibrary: DateLibrary, date: String?, dateFrequency: DateFrequency): String {
        if (date == null) return getToday(dateLibrary)
        return when (dateFrequency) {
            DateFrequency.DAILY,
            DateFrequency.DAILY_DAY_ONLY,
            DateFrequency.ACCOUNT_CARD_DAILY -> date
            DateFrequency.WEEKLY,
            DateFrequency.ACCOUNT_CARD_WEEKLY -> dateLibrary.getDateTextIsoAdjustedWeek(
                START_OF_PERIOD,
                dateLibrary.getCalendar(date)
            )
            DateFrequency.BI_WEEKLY -> dateLibrary.getDateTextIsoAdjustedWeek(
                START_OF_PERIOD,
                dateLibrary.getCalendar(date)
            )
            DateFrequency.MONTHLY -> dateLibrary.getDateTextIsoAdjustedMonth(
                dateLibrary.getCalendar(
                    date
                ), START_OF_PERIOD
            )
            DateFrequency.QUARTERLY -> dateLibrary.getDateTextIsoAdjustedQuarter(
                START_OF_PERIOD,
                dateLibrary.getCalendar(date)
            )
            DateFrequency.ANNUALLY -> dateLibrary.getDateTextIsoAdjustedYear(
                START_OF_PERIOD,
                dateLibrary.getCalendar(date)
            )
            else -> date
        }
    }

    fun updateToCurrentTime(dateLibrary: DateLibrary, date: String): String = dateLibrary.run {
        val c = Calendar.getInstance()
        val calendar = getCalendarFromString(date, DATE_ISO)
        calendar.set(Calendar.HOUR_OF_DAY, c.get(Calendar.HOUR))
        calendar.set(Calendar.MINUTE, c.get(Calendar.MINUTE))
        calendar.set(Calendar.SECOND, c.get(Calendar.SECOND))
        calendar.set(Calendar.MILLISECOND, 0)
        return getStringFromDate(calendar.time, DATE_ISO)
    }

    @Synchronized
    private fun getStringFromDate(date: Date, pattern: String): String {
        return try {
            if (isoFormat.check(pattern)) {
                simpleDateFormat.applyPattern(pattern)
                simpleDateFormat.format(date)
            } else {
                simpleDateFormatLocalized.applyPattern(pattern)
                simpleDateFormatLocalized.format(date)
            }
        } catch (e: IllegalArgumentException) {
            ""
        }
    }

    @Synchronized
    private fun getCalendarFromString(date: String, pattern: String): Calendar {
        return try {
            val calendar = Calendar.getInstance()
            if (isoFormat.check(pattern)) {
                simpleDateFormat.applyPattern(pattern)
                calendar.time = simpleDateFormat.parse(date)
                calendar
            } else {
                simpleDateFormatLocalized.applyPattern(pattern)
                calendar.time = simpleDateFormatLocalized.parse(date)
                calendar
            }
        } catch (e: Exception) {
            Calendar.getInstance()
        }
    }
}