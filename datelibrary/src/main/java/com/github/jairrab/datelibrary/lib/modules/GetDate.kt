package com.github.jairrab.datelibrary.lib.modules

import com.github.jairrab.datelibrary.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

internal class GetDate {
    private val simpleDateFormat = SimpleDateFormat(DateFormat.DATE_ISO, Locale.US)

    @Synchronized
    fun fromString(date: String): Date {
        return try {
            simpleDateFormat.applyPattern(DateFormat.DATE_ISO)
            simpleDateFormat.parse(date) ?: Date()
        } catch (e: ParseException) {
            Date()
        }
    }
}