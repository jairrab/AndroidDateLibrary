package com.github.jairrab.datelibrary.lib.modules

import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import com.github.jairrab.datelibrary.DateFormat
import com.github.jairrab.datelibrary.DateFormat.ENABLE_DATE_TIME_FORMATTER
import java.util.*

internal class GetDate(
    private val dateFormatterUtil: DateFormatterUtil,
    private val simpleDateFormatUtil: SimpleDateFormatUtil
) {
    fun getDate(date: String): Date {
        return getDate(date, DateFormat.DATE_ISO)
    }

    fun getDate(date: String, pattern: String): Date {
        if (date.isBlank()) return Date()
        return if (ENABLE_DATE_TIME_FORMATTER && VERSION.SDK_INT >= VERSION_CODES.O) {
            dateFormatterUtil.getDateParsed(date, pattern)
        } else {
            simpleDateFormatUtil.getDateParsed(date, pattern)
        }
    }
}