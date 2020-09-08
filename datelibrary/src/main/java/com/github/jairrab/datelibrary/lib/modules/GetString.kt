package com.github.jairrab.datelibrary.lib.modules

import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import com.github.jairrab.datelibrary.DateFormat.DATE_ISO
import com.github.jairrab.datelibrary.DateFormat.ENABLE_DATE_TIME_FORMATTER
import com.github.jairrab.datelibrary.DateFrequency
import com.github.jairrab.datelibrary.Period.START_OF_PERIOD
import com.github.jairrab.datelibrary.lib.DateLibrary
import java.util.*

internal class GetString(
    private val dateFormatterUtil: DateFormatterUtil,
    private val simpleDateFormatUtil: SimpleDateFormatUtil,
) {
    fun getDateText(
        dateLibrary: DateLibrary,
        date: Date,
        pattern: String
    ): String = dateLibrary.run {
        return getDateString(date, pattern)
    }

    fun getDateText(
        dateLibrary: DateLibrary,
        hour: Int,
        minute: Int,
        seconds: Int,
        pattern: String
    ): String = dateLibrary.run {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, minute)
        calendar.set(Calendar.SECOND, seconds)
        calendar.set(Calendar.MILLISECOND, 0)
        return getDateString(calendar.time, pattern)
    }

    fun getDateText(
        dateLibrary: DateLibrary,
        date: String,
        hour: Int,
        minute: Int,
        seconds: Int
    ): String = dateLibrary.run {
        val calendar = getCalendar(date)
        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, minute)
        calendar.set(Calendar.SECOND, seconds)
        calendar.set(Calendar.MILLISECOND, 0)
        return getDateString(calendar.time, DATE_ISO)
    }

    fun getDateText(
        dateLibrary: DateLibrary,
        date: String,
        dateFrequency: DateFrequency
    ): String {
        return when (dateFrequency) {
            DateFrequency.DAILY,
            DateFrequency.DAILY_DAY_ONLY,
            DateFrequency.ACCOUNT_CARD_DAILY -> date
            DateFrequency.WEEKLY,
            DateFrequency.ACCOUNT_CARD_WEEKLY -> dateLibrary
                .getDateTextIsoOffsetWeek(START_OF_PERIOD, dateLibrary.getCalendar(date))
            DateFrequency.BI_WEEKLY -> dateLibrary
                .getDateTextIsoOffsetWeek(START_OF_PERIOD, dateLibrary.getCalendar(date))
            DateFrequency.MONTHLY -> dateLibrary
                .getDateTextIsoOffsetMonth(dateLibrary.getCalendar(date), START_OF_PERIOD)
            DateFrequency.QUARTERLY -> dateLibrary
                .getDateTextIsoOffsetQuarter(START_OF_PERIOD, dateLibrary.getCalendar(date))
            DateFrequency.ANNUALLY -> dateLibrary
                .getDateTextIsoOffsetYear(START_OF_PERIOD, dateLibrary.getCalendar(date))
        }
    }

    fun getDateTextIsoOffsetCurrentTime(
        dateLibrary: DateLibrary,
        date: String
    ): String = dateLibrary.run {
        val c = Calendar.getInstance()
        val calendar = getCalendar(date)
        calendar.set(Calendar.HOUR_OF_DAY, c.get(Calendar.HOUR))
        calendar.set(Calendar.MINUTE, c.get(Calendar.MINUTE))
        calendar.set(Calendar.SECOND, c.get(Calendar.SECOND))
        calendar.set(Calendar.MILLISECOND, 0)
        return getDateString(calendar.time, DATE_ISO)
    }

    private fun getDateString(date: Date, pattern: String): String {
        return if (ENABLE_DATE_TIME_FORMATTER && VERSION.SDK_INT >= VERSION_CODES.O) {
            dateFormatterUtil.getDateTextIso(date, pattern)
        } else {
            simpleDateFormatUtil.getDateTextIso(date, pattern)
        }
    }
}