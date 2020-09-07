package com.github.jairrab.datelibrary.lib.modules

import com.github.jairrab.datelibrary.lib.DateLibrary
import com.github.jairrab.datelibrary.DateFormat
import com.github.jairrab.datelibrary.PeriodSelection
import com.github.jairrab.datelibrary.PeriodSelection.END_OF_PERIOD
import com.github.jairrab.datelibrary.PeriodSelection.START_OF_NEXT_PERIOD
import com.github.jairrab.datelibrary.PeriodSelection.START_OF_PERIOD
import java.util.*

internal class GetWeek {
    var firstDayOfWeek = Calendar.SUNDAY

    fun getWeekDaysOfDate(dateLibrary: DateLibrary, dateSelect: PeriodSelection, date: String): String {
        val c = dateLibrary.getCalendar(date)
        val i = firstDayOfWeek

        c.add(Calendar.DATE, if (i <= 0) i else i - 7)
        val firstDay = c.time

        c.add(Calendar.DATE, 6)
        val secondDay = c.time

        c.add(Calendar.DATE, 1)
        val endDay = c.time

        return when (dateSelect) {
            START_OF_PERIOD -> dateLibrary.getDateTextIso(firstDay)
            END_OF_PERIOD -> dateLibrary.getDateTextIso(secondDay)
            START_OF_NEXT_PERIOD -> dateLibrary.getDateTextIso(endDay)
        }
    }

    fun getWeekDaysOfDate(dateLibrary: DateLibrary, dateSelect: PeriodSelection, c: Calendar): String {
        c.set(Calendar.HOUR_OF_DAY, 0)
        c.set(Calendar.MINUTE, 0)
        c.set(Calendar.SECOND, 0)
        c.set(Calendar.MILLISECOND, 0)

        val i = firstDayOfWeek - c.get(Calendar.DAY_OF_WEEK)

        c.add(Calendar.DATE, if (i <= 0) i else i - 7)

        return when (dateSelect) {
            START_OF_PERIOD -> dateLibrary.getDateTextIso(c.time)
            END_OF_PERIOD -> {
                c.add(Calendar.DATE, 6)
                dateLibrary.getDateTextIso(c.time)
            }
            START_OF_NEXT_PERIOD -> {
                c.add(Calendar.DATE, 7)
                dateLibrary.getDateTextIso(c.time)
            }
        }
    }

    fun withIncrement(dateLibrary: DateLibrary, dateSelect: PeriodSelection, weekIncrement: Int): String {
        val c = Calendar.getInstance()
        c.set(Calendar.HOUR_OF_DAY, 0)
        c.set(Calendar.MINUTE, 0)
        c.set(Calendar.SECOND, 0)
        c.set(Calendar.MILLISECOND, 0)

        val i = firstDayOfWeek - c.get(Calendar.DAY_OF_WEEK)

        c.add(Calendar.DATE, if (i <= 0) i else i - 7)
        c.add(Calendar.DATE, weekIncrement * 7)
        val firstDay = c.time

        c.add(Calendar.DATE, 6)
        val secondDay = c.time

        c.add(Calendar.DATE, 1)
        val endDay = c.time

        return when (dateSelect) {
            START_OF_PERIOD -> dateLibrary.getDateText(firstDay, DateFormat.DATE_ISO)
            END_OF_PERIOD -> dateLibrary.getDateText(secondDay, DateFormat.DATE_ISO)
            START_OF_NEXT_PERIOD -> dateLibrary.getDateText(endDay, DateFormat.DATE_ISO)
        }
    }

    fun getWeekDays(dateLibrary: DateLibrary): Array<String> = dateLibrary.run {
        return arrayOf(sunday, monday, tuesday, wednesday, thursday, friday, saturday)
    }

    fun getWeekDaysValues(): Array<String> {
        return arrayOf("1", "2", "3", "4", "5", "6", "7")
    }

    fun name(dateLibrary: DateLibrary, num: Int): String = dateLibrary.run {
        return when (num) {
            Calendar.SUNDAY -> sunday
            Calendar.MONDAY -> monday
            Calendar.TUESDAY -> tuesday
            Calendar.WEDNESDAY -> wednesday
            Calendar.THURSDAY -> thursday
            Calendar.FRIDAY -> friday
            Calendar.SATURDAY -> saturday
            else -> sunday
        }
    }
}