package com.github.jairrab.datelibrary.lib.modules

import com.github.jairrab.datelibrary.lib.DateLibrary
import java.util.*

internal class GetParameter {
    fun getDateParameter(dateLibrary: DateLibrary, date: String, parameter: Int): Int {
        val calendar = dateLibrary.getCalendar(date)
        if (parameter == CALENDAR_QUARTER) {
            when (calendar.get(Calendar.MONTH)) {
                0, 1, 2 -> return 1
                3, 4, 5 -> return 2
                6, 7, 8 -> return 3
                9, 10, 11 -> return 4
            }
            return -1
        }

        return calendar.get(parameter)
    }

    companion object{
        val CALENDAR_QUARTER = 100
    }
}