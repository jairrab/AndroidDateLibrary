package com.github.jairrab.datelibrary.lib.modules

import com.github.jairrab.datelibrary.DateFormat
import com.github.jairrab.datelibrary.PeriodSelection
import com.github.jairrab.datelibrary.PeriodSelection.*
import com.github.jairrab.datelibrary.lib.DateLibrary
import java.util.*
import java.util.Calendar.*

internal class GetMonth {
    //var startMonthDay = 1

    fun getSameDayLastMonth(dateLibrary: DateLibrary): String {
        return dateLibrary.getDateTextIsoAdjusted(dateLibrary.getDateTextIsoTrimmed(), MONTH, -1)
    }

    fun withIncrement(dateLibrary: DateLibrary, monthsIncrement: Int): String {
        val todayDate = day(dateLibrary, dateLibrary.getDateTextIso())
        val useLastMonthStartDate = todayDate < dateLibrary.startMonthDay
        val c = getInstance()

        return if (useLastMonthStartDate) {
            c.add(MONTH, monthsIncrement - 1)
            fromCalendar(dateLibrary, c, START_OF_PERIOD)
        } else {
            c.add(MONTH, monthsIncrement)
            fromCalendar(dateLibrary, c, START_OF_PERIOD)
        }
    }

    fun startOfMonth(dateLibrary: DateLibrary, useStartMonthSetting: Boolean = true): String {
        val todayDate = day(dateLibrary, dateLibrary.getDateTextIso())
        val useLastMonthStartDate = todayDate < dateLibrary.startMonthDay
        val c = getInstance()

        return if (useLastMonthStartDate) {
            c.add(MONTH, -1)
            fromCalendar(dateLibrary, c, START_OF_PERIOD, useStartMonthSetting)
        } else {
            fromCalendar(dateLibrary, c, START_OF_PERIOD, useStartMonthSetting)
        }
    }

    fun endOfMonth(dateLibrary: DateLibrary, useStartMonthSetting: Boolean = true): String {
        val todayDate = day(dateLibrary, dateLibrary.getDateTextIso())
        val useLastMonthStartDate = todayDate < dateLibrary.startMonthDay
        val c = getInstance()

        return if (useLastMonthStartDate) {
            c.add(MONTH, -1)
            fromCalendar(dateLibrary, c, END_OF_PERIOD, useStartMonthSetting)
        } else {
            fromCalendar(dateLibrary, c, END_OF_PERIOD, useStartMonthSetting)
        }
    }

    fun startOfLastMonth(
        dateLibrary: DateLibrary,
        useStartMonthSetting: Boolean = true
    ): String {
        val todayDate = day(dateLibrary, dateLibrary.getDateTextIso())
        val useLastMonthStartDate = todayDate < dateLibrary.startMonthDay
        val c = getInstance()

        return if (useLastMonthStartDate) {
            c.add(MONTH, -2)
            fromCalendar(dateLibrary, c, START_OF_PERIOD, useStartMonthSetting)
        } else {
            c.add(MONTH, -1)
            fromCalendar(dateLibrary, c, START_OF_PERIOD, useStartMonthSetting)
        }
    }

    fun endOfLastMonth(dateLibrary: DateLibrary, useStartMonthSetting: Boolean = true): String {
        val todayDate = day(dateLibrary, dateLibrary.getDateTextIso())
        val useLastMonthStartDate = todayDate < dateLibrary.startMonthDay
        val c = getInstance()

        return if (useLastMonthStartDate) {
            c.add(MONTH, -2)
            fromCalendar(dateLibrary, c, END_OF_PERIOD, useStartMonthSetting)
        } else {
            c.add(MONTH, -1)
            fromCalendar(dateLibrary, c, END_OF_PERIOD, useStartMonthSetting)
        }
    }

    fun startOfNextMonth(dateLibrary: DateLibrary): String {
        val todayDate = day(dateLibrary, dateLibrary.getDateTextIso())
        val useLastMonthStartDate = todayDate < dateLibrary.startMonthDay
        val c = getInstance()

        return if (useLastMonthStartDate) {
            c.add(MONTH, 0)
            fromCalendar(dateLibrary, c, START_OF_PERIOD)
        } else {
            c.add(MONTH, 1)
            fromCalendar(dateLibrary, c, START_OF_PERIOD)
        }
    }

    fun fromString(
        dateLibrary: DateLibrary,
        date: String,
        dateSelect: PeriodSelection,
        useStartMonthSetting: Boolean = true
    ): String {
        return fromCalendar(
            dateLibrary = dateLibrary,
            c = dateLibrary.getCalendar(date),
            dateSelect = dateSelect,
            useStartMonthSetting = useStartMonthSetting
        )
    }

    fun fromCalendar(
        dateLibrary: DateLibrary,
        c: Calendar,
        dateSelect: PeriodSelection,
        useStartMonthSetting: Boolean = true
    ): String {
        c.set(HOUR_OF_DAY, 0)
        c.set(MINUTE, 0)
        c.set(SECOND, 0)
        c.set(MILLISECOND, 0)

        c.set(DATE, if (useStartMonthSetting) dateLibrary.startMonthDay else 1)

        return when (dateSelect) {
            START_OF_PERIOD -> dateLibrary.getDateTextIso(c.time)
            END_OF_PERIOD -> {
                c.add(MONTH, 1)
                c.add(DATE, -1)
                dateLibrary.getDateTextIso(c.time)
            }
            START_OF_NEXT_PERIOD -> {
                c.add(MONTH, 1)
                dateLibrary.getDateTextIso(c.time)
            }
        }
    }

    fun setMonthDate(dateLibrary: DateLibrary, date: String, day: Int): String {
        val c = dateLibrary.getCalendar(date)
        c.set(DATE, day)
        return dateLibrary.getDateTextIso(c.time)
    }

    fun setEndOfMonth(dateLibrary: DateLibrary, date: String): String {
        val c = dateLibrary.getCalendar(date)
        return setMonthDate(dateLibrary, date, c.getActualMaximum(DATE))
    }

    fun addMonthsSameDayOfWeek(
        dateLibrary: DateLibrary,
        date: String,
        addedMonths: Int
    ): String {
        val dateDate = dateLibrary.getDate(date)
        val c1 = getInstance()
        val c2 = getInstance()

        c1.time = dateDate
        c2.time = dateDate
        c2.add(MONTH, addedMonths)

        val dayOfWeek = c1.get(DAY_OF_WEEK)

        c1.set(DAY_OF_MONTH, 1)
        c2.set(DAY_OF_MONTH, 1)

        val daysBetween = dayOfWeek - c1.get(DAY_OF_WEEK)
        val daysBetween2 = dayOfWeek - c2.get(DAY_OF_WEEK)

        c1.add(DATE, if (daysBetween < 0) 7 + daysBetween else daysBetween)
        c2.add(DATE, if (daysBetween2 < 0) 7 + daysBetween2 else daysBetween2)

        val weekCounter = getWeekCounter(dateDate, c1)

        for (n in 1 until weekCounter) {
            c2.add(DATE, 7)
        }

        val yearDiff = c2.get(YEAR) - c1.get(YEAR)

        if (c2.get(MONTH) + 12 * yearDiff - c1.get(MONTH) > addedMonths) {
            c2.add(DATE, -7)
        }

        return dateLibrary.getDateText(c2.time, DateFormat.DATE_ISO)
    }

    fun getMonthIndices(
        dateLibrary: DateLibrary,
        startDate: String?,
        endDate: String?
    ): List<Int> {
        val indexA = getMonthIndexBasedOnMonthPreference(dateLibrary, startDate)
        val indexB = getMonthIndexBasedOnMonthPreference(dateLibrary, endDate)

        return if (indexA <= indexB) {
            (indexA..indexB).toList()
        } else {
            (0..indexB)
                .toMutableList()
                .let {
                    it.addAll((indexA..11).toList())
                    it
                }
        }
    }

    private fun getMonthIndexBasedOnMonthPreference(
        dateLibrary: DateLibrary,
        date: String?
    ): Int {
        val dayNum = getDateParameter(dateLibrary, date ?: "", DATE)

        return if (dayNum < dateLibrary.startMonthDay) {
            val index = getDateParameter(dateLibrary, date ?: "", MONTH) - 1
            if (index == -1) 11 else index
        } else {
            getDateParameter(dateLibrary, date ?: "", MONTH)
        }
    }

    fun getCurrentMonth(): Int {
        val c = getInstance()
        return c.get(MONTH)
    }

    fun isSameAsCurrentMonth(dateLibrary: DateLibrary, date: String): Boolean {
        return getDateParameter(dateLibrary, date, MONTH) ==
            getDateParameter(dateLibrary, dateLibrary.getDateTextIso(), MONTH)
    }

    fun isFirstDayOfMonth(dateLibrary: DateLibrary, date: String): Boolean {
        return getDateParameter(dateLibrary, date, DAY_OF_MONTH) == 1
    }

    fun isFirstDayOfMonthBasedOnPreference(dateLibrary: DateLibrary, date: String): Boolean {
        return getDateParameter(dateLibrary, date, DAY_OF_MONTH) == dateLibrary.startMonthDay
    }

    fun getLastDayOfMonth(dateLibrary: DateLibrary, date: String): Int {
        val calendar = dateLibrary.getCalendar(date)
        return calendar.getActualMaximum(DATE)
    }

    fun getLastDayOfMonthBasedOnPreference(dateLibrary: DateLibrary, date: String): Int {
        val endOfMonth = fromString(dateLibrary, date, END_OF_PERIOD)
        return getDateParameter(dateLibrary, endOfMonth, DAY_OF_MONTH)
    }

    fun isNthDayOfMonth(dateLibrary: DateLibrary, date: String, n: Int): Boolean {
        return getDateParameter(dateLibrary, date, DAY_OF_MONTH) == n
    }

    fun isMonthlyDates(dateLibrary: DateLibrary, startDate: String, endDate: String): Boolean {
        val month1 = getDateParameter(dateLibrary, startDate, MONTH)
        val month2 = getDateParameter(dateLibrary, endDate, MONTH)

        if (month1 == month2) {
            val isDay1 = isFirstDayOfMonth(dateLibrary, startDate)
            if (isDay1) {
                val day2 = getDateParameter(dateLibrary, endDate, DATE)
                val lastDayOfMonth = getLastDayOfMonth(dateLibrary, endDate)
                if (day2 == lastDayOfMonth) return true
            }
        }

        return false
    }

    fun isMonthlyDatesBasedOnPreference(
        dateLibrary: DateLibrary,
        startDate: String,
        endDate: String
    ): Boolean {
        val month1 = getMonthIndexBasedOnMonthPreference(dateLibrary, startDate)
        val month2 = getMonthIndexBasedOnMonthPreference(dateLibrary, endDate)

        if (month1 == month2) {
            val isDay1 = isFirstDayOfMonthBasedOnPreference(dateLibrary, startDate)
            if (isDay1) {
                val day2 = getDateParameter(dateLibrary, endDate, DATE)
                val lastDayOfMonth = getLastDayOfMonthBasedOnPreference(dateLibrary, endDate)
                if (day2 == lastDayOfMonth) return true
            }
        }

        return false
    }

    fun isSpanningWholeMonths(
        dateLibrary: DateLibrary,
        startDate: String,
        endDate: String
    ): Boolean {
        val isDay1 = isFirstDayOfMonthBasedOnPreference(dateLibrary, startDate)

        if (isDay1) {
            val day2 = getDateParameter(dateLibrary, endDate, DATE)
            val lastDayOfMonth = getLastDayOfMonthBasedOnPreference(dateLibrary, endDate)
            if (day2 == lastDayOfMonth) return true
        }

        return false
    }

    private fun getDateParameter(dateLibrary: DateLibrary, date: String, parameter: Int) =
        dateLibrary.getDateParameter(date, parameter)

    private fun getWeekCounter(date: Date, calendar: Calendar): Int {
        var weekCounter = 1

        while (calendar.timeInMillis < date.time) {
            calendar.add(DATE, 7)
            ++weekCounter
        }

        return weekCounter
    }

    private fun day(dateLibrary: DateLibrary, date: String): Int {
        return dateLibrary.getCalendar(date).get(DAY_OF_MONTH)
    }
}