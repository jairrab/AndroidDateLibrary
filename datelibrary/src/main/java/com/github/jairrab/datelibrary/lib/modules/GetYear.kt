package com.github.jairrab.datelibrary.lib.modules

import com.github.jairrab.datelibrary.DateFormat
import com.github.jairrab.datelibrary.PeriodSelection
import com.github.jairrab.datelibrary.PeriodSelection.*
import com.github.jairrab.datelibrary.lib.DateLibrary
import java.util.*

internal class GetYear {
    //var startMonthOfYear: String = DateFormatSymbols().months[Calendar.JANUARY]
    //var startMonthDay = 1

    fun getEndLastYear(dateLibrary: DateLibrary): String {
        return getYearDaysOfDate(
            dateLibrary,
            END_OF_PERIOD,
            dateLibrary.getCalendar(dateLibrary.getDateAdjusted(Date(), Calendar.YEAR, -1))
        )
    }

    fun getSinceLastYear(dateLibrary: DateLibrary): String {
        return dateLibrary.getDateTextIsoAdjusted(
            dateLibrary.getDateTextIsoAdjusted(
                dateLibrary.getDateTextIsoTrimmed(),
                Calendar.YEAR,
                -1
            ),
            Calendar.DATE, 1
        )
    }

    fun getYearDaysOfDate(
        dateLibrary: DateLibrary,
        dateSelect: PeriodSelection,
        c: Calendar
    ): String {
        c.set(Calendar.MONTH, dateLibrary.startMonthOfYear)
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
        }
    }

    fun getYearDaysOfDate(
        dateLibrary: DateLibrary,
        dateSelect: PeriodSelection,
        date: String
    ): String {
        val calendar = dateLibrary.getCalendar(date)
        return getYearDaysOfDate(dateLibrary, dateSelect, calendar)
    }

    fun getYearsDays(
        dateLibrary: DateLibrary,
        yearsIncrement: Int,
        dateSelect: PeriodSelection
    ): String {
        val firstDay: Date
        val secondDay: Date

        val c = Calendar.getInstance()
        c.set(Calendar.YEAR, c.get(Calendar.YEAR) + yearsIncrement)
        c.set(Calendar.MONTH, dateLibrary.startMonthOfYear)
        c.set(Calendar.DATE, dateLibrary.startMonthDay)
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
            START_OF_PERIOD -> dateLibrary.getDateText(firstDay, DateFormat.DATE_ISO)
            END_OF_PERIOD -> dateLibrary.getDateText(secondDay, DateFormat.DATE_ISO)
            START_OF_NEXT_PERIOD -> dateLibrary.getDateText(endDay, DateFormat.DATE_ISO)
        }
    }
}