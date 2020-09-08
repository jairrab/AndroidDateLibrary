package com.github.jairrab.datelibrary.lib.modules

import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import androidx.annotation.RequiresApi
import com.github.jairrab.datelibrary.DateFormat
import com.github.jairrab.datelibrary.DateFormat.ENABLE_DATE_TIME_FORMATTER
import com.github.jairrab.datelibrary.lib.toDate
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

internal class GetDate(
    private val checkIsoFormat: CheckIsoFormat,
    private val simpleDateFormatUtil: SimpleDateFormatUtil
) {
    fun getDate(date: String): Date {
        return if (ENABLE_DATE_TIME_FORMATTER && VERSION.SDK_INT >= VERSION_CODES.O) {
            getDateIsoNew(date)
        } else {
            simpleDateFormatUtil.getDateParserIso(date)
        }
    }

    @RequiresApi(VERSION_CODES.O)
    private fun getDateIsoNew(date: String): Date {
        return LocalDateTime.parse(date, DateTimeFormatter.ofPattern(DateFormat.DATE_ISO)).toDate
    }

    fun getDate(date: String, pattern: String): Date {
        return if (ENABLE_DATE_TIME_FORMATTER && VERSION.SDK_INT >= VERSION_CODES.O) {
            getDateNew(date, pattern)
        } else {
            simpleDateFormatUtil.getDateParser(date, pattern)
        }
    }

    @RequiresApi(VERSION_CODES.O)
    private fun getDateNew(date: String, pattern: String): Date {
        return if (checkIsoFormat.check(pattern)) {
            LocalDateTime.parse(date, DateTimeFormatter.ofPattern(pattern)).toDate
        } else {
            LocalDate.parse(date, DateTimeFormatter.ofPattern(pattern)).toDate
        }
    }
}