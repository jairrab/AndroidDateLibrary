package com.github.jairrab.datelibrary.lib.modules

import com.github.jairrab.datelibrary.DateFormat
import com.github.jairrab.datelibrary.lib.DateLibrary
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

internal class GetCalendar(private val isoFormat: CheckIsoFormat) {
    var simpleDateFormatLocalized = SimpleDateFormat(DateFormat.DATE_ISO, Locale.getDefault())
    private val simpleDateFormat = SimpleDateFormat(DateFormat.DATE_ISO, Locale.US)

    @Synchronized
    fun getCalendar(dateLibrary: DateLibrary, date: String): Calendar = dateLibrary.run {
        val calendar = Calendar.getInstance()
        return try {
            simpleDateFormat.applyPattern(DateFormat.DATE_ISO)
            calendar.time = simpleDateFormat.parse(date) ?: return calendar
            calendar
        } catch (e: ParseException) {
            calendar
        }
    }

    @Synchronized
    fun getCalendar(date: Date): Calendar {
        val calendar = Calendar.getInstance()
        calendar.time = date
        return calendar
    }

    fun getCalendar(
        dateLibrary: DateLibrary,
        date: String,
        pattern: String
    ): Calendar = dateLibrary.run {
        val calendar = Calendar.getInstance()
        return try {
            if (isoFormat.check(pattern)) {
                simpleDateFormat.applyPattern(pattern)
                calendar.time = simpleDateFormat.parse(date)
                calendar
            } else {
                simpleDateFormatLocalized.applyPattern(pattern)
                calendar.time = simpleDateFormatLocalized.parse(date)
                calendar
            }
        } catch (e: ParseException) {
            e.printStackTrace()
            calendar
        }
    }
}