package com.github.jairrab.datelibrary.lib.modules

import com.github.jairrab.datelibrary.Period
import com.github.jairrab.datelibrary.Period.*
import com.github.jairrab.datelibrary.lib.DateLibrary
import java.util.*

internal class GetQuarter {
    fun getEndLastQuarter(dateLibrary: DateLibrary): String {
        return getQuartersDaysOfDate(
            dateLibrary,
            END_OF_PERIOD,
            dateLibrary.getCalendar(dateLibrary.getDateOffset(Date(), Calendar.MONTH, -3))
        )
    }

    fun getQuartersDaysOfDate(
        dateLibrary: DateLibrary,
        dateSelect: Period,
        c: Calendar
    ): String {
        val quarter = c.get(Calendar.MONTH) / 3

        val firstDay: Date
        val secondDay: Date

        when (quarter) {
            0 -> {
                c.set(c.get(Calendar.YEAR), 0, 1, 0, 0, 0)
                firstDay = c.time
                c.set(c.get(Calendar.YEAR), 2, 31, 0, 0, 0)
                secondDay = c.time
            }
            1 -> {
                c.set(c.get(Calendar.YEAR), 3, 1, 0, 0, 0)
                firstDay = c.time
                c.set(c.get(Calendar.YEAR), 5, 30, 0, 0, 0)
                secondDay = c.time
            }
            2 -> {
                c.set(c.get(Calendar.YEAR), 6, 1, 0, 0, 0)
                firstDay = c.time
                c.set(c.get(Calendar.YEAR), 8, 30, 0, 0, 0)
                secondDay = c.time
            }
            3 -> {
                c.set(c.get(Calendar.YEAR), 9, 1, 0, 0, 0)
                firstDay = c.time
                c.set(c.get(Calendar.YEAR), 11, 31, 0, 0, 0)
                secondDay = c.time
            }
            else -> {
                c.set(c.get(Calendar.YEAR), 0, 1, 0, 0, 0)
                firstDay = c.time
                c.set(c.get(Calendar.YEAR), 2, 31, 0, 0, 0)
                secondDay = c.time
            }
        }

        c.add(Calendar.DATE, 1)
        val endDay = c.time

        return when (dateSelect) {
            START_OF_PERIOD -> dateLibrary.getDateTextIso(firstDay)
            END_OF_PERIOD -> dateLibrary.getDateTextIso(secondDay)
            START_OF_NEXT_PERIOD -> dateLibrary.getDateTextIso(endDay)
        }
    }

    fun getQuartersDaysOfDate(
        dateLibrary: DateLibrary,
        dateSelect: Period,
        date: String
    ): String {
        return getQuartersDaysOfDate(dateLibrary, dateSelect, dateLibrary.getCalendar(date))
    }

    fun getLastQuartersDays(dateLibrary: DateLibrary, dateSelect: Period): String {
        val c = Calendar.getInstance()
        val quarter = c.get(Calendar.MONTH) / 3
        var firstDay = Date()
        var secondDay = Date()

        when (quarter) {
            0 -> {
                c.set(c.get(Calendar.YEAR) - 1, 9, 1, 0, 0, 0)
                firstDay = c.time
                c.set(c.get(Calendar.YEAR), 11, 31, 0, 0, 0)
                secondDay = c.time
            }
            1 -> {
                c.set(c.get(Calendar.YEAR), 0, 1, 0, 0, 0)
                firstDay = c.time
                c.set(c.get(Calendar.YEAR), 2, 31, 0, 0, 0)
                secondDay = c.time
            }
            2 -> {
                c.set(c.get(Calendar.YEAR), 3, 1, 0, 0, 0)
                firstDay = c.time
                c.set(c.get(Calendar.YEAR), 5, 30, 0, 0, 0)
                secondDay = c.time
            }
            3 -> {
                c.set(c.get(Calendar.YEAR), 6, 1, 0, 0, 0)
                firstDay = c.time
                c.set(c.get(Calendar.YEAR), 8, 30, 0, 0, 0)
                secondDay = c.time
            }
        }

        c.add(Calendar.DATE, 1)

        val endDay = c.time

        return when (dateSelect) {
            START_OF_PERIOD -> dateLibrary.getDateTextIso(firstDay)
            END_OF_PERIOD -> dateLibrary.getDateTextIso(secondDay)
            START_OF_NEXT_PERIOD -> dateLibrary.getDateTextIso(endDay)
        }
    }

    fun getThisQuartersDays(dateLibrary: DateLibrary, dateSelect: Period): String {
        val c = Calendar.getInstance()
        val quarter = c.get(Calendar.MONTH) / 3
        var firstDay = Date()
        var secondDay = Date()

        when (quarter) {
            0 -> {
                c.set(c.get(Calendar.YEAR), 0, 1, 0, 0, 0)
                firstDay = c.time
                c.set(c.get(Calendar.YEAR), 2, 31, 0, 0, 0)
                secondDay = c.time
            }
            1 -> {
                c.set(c.get(Calendar.YEAR), 3, 1, 0, 0, 0)
                firstDay = c.time
                c.set(c.get(Calendar.YEAR), 5, 30, 0, 0, 0)
                secondDay = c.time
            }
            2 -> {
                c.set(c.get(Calendar.YEAR), 6, 1, 0, 0, 0)
                firstDay = c.time
                c.set(c.get(Calendar.YEAR), 8, 30, 0, 0, 0)
                secondDay = c.time
            }
            3 -> {
                c.set(c.get(Calendar.YEAR), 9, 1, 0, 0, 0)
                firstDay = c.time
                c.set(c.get(Calendar.YEAR), 11, 31, 0, 0, 0)
                secondDay = c.time
            }
        }

        c.add(Calendar.DATE, 1)
        val endDay = c.time

        return when (dateSelect) {
            START_OF_PERIOD -> dateLibrary.getDateTextIso(firstDay)
            END_OF_PERIOD -> dateLibrary.getDateTextIso(secondDay)
            START_OF_NEXT_PERIOD -> dateLibrary.getDateTextIso(endDay)
        }
    }
}