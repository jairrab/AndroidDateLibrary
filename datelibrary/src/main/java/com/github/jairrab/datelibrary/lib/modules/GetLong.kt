package com.github.jairrab.datelibrary.lib.modules

import com.github.jairrab.datelibrary.lib.DateLibrary
import java.util.*

internal class GetLong {
    fun getTimeInMillis(): Long = Calendar.getInstance().timeInMillis

    fun getTimeInMills(date: String, dateLibrary: DateLibrary): Long {
        return dateLibrary.getCalendar(date).timeInMillis
    }

    fun getTimeInMills(year: Int, month: Int, dayOfMonth: Int): Long {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, dayOfMonth)
        return calendar.timeInMillis
    }
}