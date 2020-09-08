package com.github.jairrab.datelibrary.lib.modules

import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import androidx.annotation.RequiresApi
import com.github.jairrab.datelibrary.DateFormat.ENABLE_DATE_TIME_FORMATTER
import com.github.jairrab.datelibrary.lib.DateLibrary
import java.util.*

internal class GetCalendar(
    private val simpleDateFormatUtil: SimpleDateFormatUtil
) {
    fun getCalendar(date: Date): Calendar {
        val calendar = Calendar.getInstance()
        calendar.time = date
        return calendar
    }

    fun getCalendar(
        dateLibrary: DateLibrary,
        date: String,
        pattern: String
    ): Calendar = dateLibrary.run {
        return if (ENABLE_DATE_TIME_FORMATTER && VERSION.SDK_INT >= VERSION_CODES.O) {
            getDateStringNew(date, pattern)
        } else {
            simpleDateFormatUtil.getCalendar(date, pattern)
        }
    }

    fun getCalendar(
        dateLibrary: DateLibrary,
        date: String
    ): Calendar = dateLibrary.run {
        return if (ENABLE_DATE_TIME_FORMATTER && VERSION.SDK_INT >= VERSION_CODES.O) {
            getDateStringNew(date)
        } else {
            simpleDateFormatUtil.getCalendar(date)
        }
    }

    @RequiresApi(VERSION_CODES.O)
    private fun DateLibrary.getDateStringNew(date: String): Calendar {
        val c = Calendar.getInstance()
        c.time = getDate(date)
        return c
    }

    @RequiresApi(VERSION_CODES.O)
    private fun DateLibrary.getDateStringNew(date: String, pattern: String): Calendar {
        val c = Calendar.getInstance()
        c.time = getDate(date, pattern)
        return c
    }
}