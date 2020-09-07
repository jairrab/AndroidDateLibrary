package com.github.jairrab.datelibrary.lib.modules

import com.github.jairrab.datelibrary.lib.DateLibrary
import com.github.jairrab.datelibrary.DateConstants
import com.github.jairrab.datelibrary.PeriodSelection.END_OF_PERIOD
import com.github.jairrab.datelibrary.PeriodSelection.START_OF_NEXT_PERIOD
import com.github.jairrab.datelibrary.PeriodSelection.START_OF_PERIOD
import java.text.DateFormatSymbols
import java.util.*

internal class GetYear {
    var startMonthOfYear: String = DateFormatSymbols().months[Calendar.JANUARY]
    var startMonthDay = 1

    fun getEndLastYear(dateLibrary: DateLibrary): String {
        return getYearDaysOfDate(
            dateLibrary,
            END_OF_PERIOD,
            dateLibrary.getCalendar(dateLibrary.getDateAdjusted(Date(), Calendar.YEAR, -1))
        )
    }

    fun getSinceLastYear(dateLibrary: DateLibrary): String {
        return dateLibrary.getDateTextIsoAdjusted(
            dateLibrary.getDateTextIsoAdjusted(dateLibrary.getDateTextIsoTrimmed(), Calendar.YEAR, -1),
            Calendar.DATE, 1
        )
    }

    fun getYearDaysOfDate(dateLibrary: DateLibrary, dateSelect: Int, c: Calendar): String {
        c.set(Calendar.MONTH, indexOfMonth(startMonthOfYear, DateFormatSymbols().months))
        c.set(Calendar.DAY_OF_MONTH, 1)
        c.set(Calendar.HOUR_OF_DAY, 0)
        c.set(Calendar.MINUTE, 0)
        c.set(Calendar.SECOND, 0)
        c.set(Calendar.MILLISECOND, 0)

        return when (dateSelect) {
            START_OF_PERIOD -> dateLibrary.getDateTextIso(c.time)
            END_OF_PERIOD -> {
                c.set(Calendar.MONTH, 11)
                c.set(Calendar.DAY_OF_MONTH, 31)
                dateLibrary.getDateTextIso(c.time)
            }
            START_OF_NEXT_PERIOD -> {
                c.set(Calendar.YEAR, c.get(Calendar.YEAR) + 1)
                c.set(Calendar.MONTH, 0)
                c.set(Calendar.DAY_OF_MONTH, 1)
                dateLibrary.getDateTextIso(c.time)
            }
            else -> dateLibrary.getDateTextIso(c.time)
        }
    }

    fun getYearDaysOfDate(dateLibrary: DateLibrary, dateSelect: Int, date: String): String {
        val calendar = dateLibrary.getCalendar(date)
        return getYearDaysOfDate(dateLibrary, dateSelect, calendar)
    }

    fun getYearsDays(dateLibrary: DateLibrary, yearsIncrement: Int, dateSelect: Int): String {
        val firstDay: Date
        val secondDay: Date

        val c = Calendar.getInstance()
        c.set(Calendar.YEAR, c.get(Calendar.YEAR) + yearsIncrement)
        c.set(Calendar.MONTH, indexOfMonth(startMonthOfYear, DateFormatSymbols().months))
        c.set(Calendar.DATE, startMonthDay)
        c.set(Calendar.HOUR_OF_DAY, 0)
        c.set(Calendar.MINUTE, 0)
        c.set(Calendar.SECOND, 0)
        c.set(Calendar.MILLISECOND, 0)
        firstDay = c.time

        c.add(Calendar.MONTH, 12)
        c.add(Calendar.DATE, -1)
        secondDay = c.time

        c.add(Calendar.DATE, 1)
        val endDay = c.time

        return when (dateSelect) {
            1 -> dateLibrary.getDateText(firstDay, DateConstants.DATE_ISO)
            2 -> dateLibrary.getDateText(secondDay, DateConstants.DATE_ISO)
            3 -> dateLibrary.getDateText(endDay, DateConstants.DATE_ISO)
            else -> dateLibrary.getDateText(firstDay, DateConstants.DATE_ISO)
        }
    }

    private fun indexOfMonth(month: String?, months: Array<String>): Int {
        for ((i, m) in months.withIndex()) {
            if (m == month) {
                return i
            }
        }
        return 0
    }
}