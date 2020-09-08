package com.github.jairrab.datelibrary.lib.modules

import com.github.jairrab.datelibrary.Period
import com.github.jairrab.datelibrary.Period.*
import com.github.jairrab.datelibrary.lib.DateLibrary
import java.util.*

internal class GetBiMonth {
    fun getLastBiMonthDays(dateLibrary: DateLibrary, dateSelect: Period): String {
        val c = Calendar.getInstance()
        c.set(Calendar.HOUR_OF_DAY, 0)
        c.set(Calendar.MINUTE, 0)
        c.set(Calendar.SECOND, 0)
        c.set(Calendar.MILLISECOND, 0)

        val firstDay: Date
        val secondDay: Date
        val endDay: Date

        if (c.get(Calendar.DATE) <= 15) {
            c.add(Calendar.MONTH, -1)

            c.set(Calendar.DATE, 16)
            firstDay = c.time

            c.set(Calendar.DATE, c.getActualMaximum(Calendar.DATE))
            secondDay = c.time

            c.add(Calendar.DATE, 1)
            endDay = c.time
        } else {
            c.set(Calendar.DATE, 1)
            firstDay = c.time

            c.set(Calendar.DATE, 15)
            secondDay = c.time

            c.add(Calendar.DATE, 1)
            endDay = c.time
        }

        return when (dateSelect) {
            START_OF_PERIOD -> dateLibrary.getDateTextIso(firstDay)
            END_OF_PERIOD -> dateLibrary.getDateTextIso(secondDay)
            START_OF_NEXT_PERIOD -> dateLibrary.getDateTextIso(endDay)
        }
    }

    fun getThisBiMonthDays(dateLibrary: DateLibrary, dateSelect: Period): String {
        val c = Calendar.getInstance()
        c.set(Calendar.HOUR_OF_DAY, 0)
        c.set(Calendar.MINUTE, 0)
        c.set(Calendar.SECOND, 0)
        c.set(Calendar.MILLISECOND, 0)

        val firstDay: Date
        val secondDay: Date
        val endDay: Date

        if (c.get(Calendar.DATE) <= 15) {
            c.set(Calendar.DATE, 1)
            firstDay = c.time

            c.set(Calendar.DATE, 15)
            secondDay = c.time

            c.add(Calendar.DATE, 1)
            endDay = c.time
        } else {
            c.set(Calendar.DATE, 16)
            firstDay = c.time

            c.set(Calendar.DATE, c.getActualMaximum(Calendar.DATE))
            secondDay = c.time

            c.add(Calendar.DATE, 1)
            endDay = c.time
        }

        return when (dateSelect) {
            START_OF_PERIOD -> dateLibrary.getDateTextIso(firstDay)
            END_OF_PERIOD -> dateLibrary.getDateTextIso(secondDay)
            START_OF_NEXT_PERIOD -> dateLibrary.getDateTextIso(endDay)
        }
    }
}