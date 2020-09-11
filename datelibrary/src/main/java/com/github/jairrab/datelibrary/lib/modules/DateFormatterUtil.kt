package com.github.jairrab.datelibrary.lib.modules

import android.os.Build
import androidx.annotation.RequiresApi
import com.github.jairrab.datelibrary.lib.toDate
import com.github.jairrab.datelibrary.lib.toLocalTime
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

internal class DateFormatterUtil(private val checkIsoFormat: CheckIsoFormat) {
    @RequiresApi(Build.VERSION_CODES.O)
    fun getDateTextIso(date: Date, pattern: String): String {
        val formatter = if (checkIsoFormat.check(pattern)) {
            DateTimeFormatter.ofPattern(pattern, Locale.US)
        } else {
            DateTimeFormatter.ofPattern(pattern, Locale.getDefault())
        }
        return formatter.format(date.toLocalTime)
            ?: throw IllegalStateException("Invalid conversion of $date")
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getDateParsed(date: String, pattern: String): Date {
        return if (checkIsoFormat.check(pattern)) {
            LocalDateTime.parse(date, DateTimeFormatter.ofPattern(pattern)).toDate
        } else {
            LocalDate.parse(date, DateTimeFormatter.ofPattern(pattern)).toDate
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getCalendar(date: String, pattern: String): Calendar {
        val c = Calendar.getInstance()
        c.time = getDateParsed(date, pattern)
        return c
    }
}