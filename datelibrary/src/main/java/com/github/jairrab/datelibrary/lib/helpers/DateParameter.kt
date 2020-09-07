package com.github.jairrab.datelibrary.lib.helpers

import com.github.jairrab.datelibrary.DateConstants
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object DateParameter {

    /**
     * Gets the date parameter from a date
     *
     * @param date      given date
     * @param parameter given parameter. Can be any of [Calendar.MONTH], [Calendar.DAY_OF_MONTH],
     * etc... In addition, [.QUARTER] is also available
     * @return date parameter
     */
    fun getDateParameter(date: String, parameter: Int): Int {
        val calendar = getCalendar(date)
        if (parameter == DateConstants.QUARTER) {
            when (calendar.get(Calendar.MONTH)) {
                0, 1, 2   -> return 1
                3, 4, 5   -> return 2
                6, 7, 8   -> return 3
                9, 10, 11 -> return 4
            }
            return -1
        }

        return calendar.get(parameter)
    }

    private fun getCalendar(date: String): Calendar {
        val c = Calendar.getInstance()
        return try {
            c.time = SimpleDateFormat(DateConstants.DATE_ISO, Locale.getDefault()).parse(date)
            c
        } catch (e: ParseException) {
            c
        }
    }
}
