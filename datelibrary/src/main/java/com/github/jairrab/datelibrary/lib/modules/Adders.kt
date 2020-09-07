package com.github.jairrab.datelibrary.lib.modules

import com.github.jairrab.datelibrary.DateFrequency
import com.github.jairrab.datelibrary.lib.DateLibrary
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

    fun add(
        dateLibrary: DateLibrary,
        date: String,
        dateFrequency: DateFrequency
    ): String = dateLibrary.run {
        return when (dateFrequency) {
            DateFrequency.DAILY,
            DateFrequency.DAILY_DAY_ONLY,
            DateFrequency.ACCOUNT_CARD_DAILY -> addDateToString(date, 1, Calendar.DATE)
            DateFrequency.WEEKLY,
            DateFrequency.ACCOUNT_CARD_WEEKLY -> addDateToString(date, 1, Calendar.WEEK_OF_YEAR)
            DateFrequency.BI_WEEKLY -> addDateToString(date, 2, Calendar.WEEK_OF_YEAR)
            DateFrequency.MONTHLY -> addDateToString(date, 1, Calendar.MONTH)
            DateFrequency.QUARTERLY -> addDateToString(date, 3, Calendar.MONTH)
            DateFrequency.ANNUALLY -> addDateToString(date, 1, Calendar.YEAR)
        }
    }

    private fun DateLibrary.addDateToString(date: String, num: Int, field: Int): String {
        val calendar = Calendar.getInstance()
        calendar.time = getDate(date)
        calendar.add(field, num)
        return getDateTextIso(calendar.time)
    }
}