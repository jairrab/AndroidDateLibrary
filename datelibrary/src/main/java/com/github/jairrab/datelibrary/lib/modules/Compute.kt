package com.github.jairrab.datelibrary.lib.modules

import com.github.jairrab.datelibrary.lib.DateLibrary
import java.util.*

internal class Compute {

    fun getDaysBetweenWhole(dateLibrary: DateLibrary, from: String, to: String): Int {
        val fromMills = dateLibrary.getCalendar(dateLibrary.getDateTextIsoTrimmed(from)).timeInMillis
        val toMills = dateLibrary.getCalendar(dateLibrary.getDateTextIsoTrimmed(to)).timeInMillis
        val difference = toMills - fromMills
        return (difference / (24.0 * 60.0 * 60.0 * 1000.0)).toInt()
    }

    fun getDaysBetweenExact(
        dateLibrary: DateLibrary,
        from: String,
        to: String
    ): Double = dateLibrary.run {
        return getDaysCount(getTimeDifference(dateLibrary, from, to))
    }

    fun getDaysBetweenExact(from: Date, to: Date): Double {
        return getDaysCount(to.time - from.time)
    }

    fun isFirstBeforeEndDate(dateLibrary: DateLibrary, firsDay: String, endDate: String): Boolean {
        return dateLibrary.getTime(firsDay) < dateLibrary.getTime(endDate)
    }

    fun isFirstOnOrBeforeEndDate(
        dateLibrary: DateLibrary,
        firsDay: String,
        endDate: String
    ): Boolean {
        return dateLibrary.getTime(firsDay) <= dateLibrary.getTime(endDate)
    }

    private fun getDaysCount(timeInMills: Long): Double {
        return timeInMills / (24.0 * 60.0 * 60.0 * 1000.0)
    }

    private fun getTimeDifference(dateLibrary: DateLibrary, from: String, to: String): Long {
        val dateFromDate = dateLibrary.getDate(from)
        val dateToDate = dateLibrary.getDate(to)
        val c1 = Calendar.getInstance()
        val c2 = Calendar.getInstance()
        c1.time = dateFromDate
        c2.time = dateToDate
        return c2.timeInMillis - c1.timeInMillis
    }
}