package com.github.jairrab.datelibrary.lib.modules

import com.github.jairrab.datelibrary.lib.DateLibrary
import com.github.jairrab.datelibrary.Frequency
import java.util.*

internal class Adders {

    fun add(date: Date, field: Int, num: Int): Date {
        val calendar = Calendar.getInstance()
        calendar.time = date
        calendar.add(field, num)
        return calendar.time
    }

    fun add(
        dateLibrary: DateLibrary,
        date: String,
        field: Int,
        num: Int
    ): String = dateLibrary.run {
        return addDateToString(date, num, field)
    }

    fun add(dateLibrary: DateLibrary, date: String, frequency: Int): String = dateLibrary.run {
        return when (frequency) {
            Frequency.DAILY,
            Frequency.DAILY_DAY_ONLY,
            Frequency.ACCOUNT_CARD_DAILY -> addDateToString(date, 1, Calendar.DATE)
            Frequency.WEEKLY,
            Frequency.ACCOUNT_CARD_WEEKLY -> addDateToString(date, 1, Calendar.WEEK_OF_YEAR)
            Frequency.BI_WEEKLY -> addDateToString(date, 2, Calendar.WEEK_OF_YEAR)
            Frequency.MONTHLY -> addDateToString(date, 1, Calendar.MONTH)
            Frequency.QUARTERLY -> addDateToString(date, 3, Calendar.MONTH)
            Frequency.ANNUALLY -> addDateToString(date, 1, Calendar.YEAR)
            else -> addDateToString(date, 1, Calendar.DATE)
        }
    }

    private fun DateLibrary.addDateToString(date: String, num: Int, field: Int): String {
        val calendar = Calendar.getInstance()
        calendar.time = getDate(date)
        calendar.add(field, num)
        return getDateTextIso(calendar.time)
    }
}