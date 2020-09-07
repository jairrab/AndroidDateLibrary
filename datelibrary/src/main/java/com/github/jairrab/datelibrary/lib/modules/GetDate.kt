package com.github.jairrab.datelibrary.lib.modules

import com.github.jairrab.datelibrary.DateConstants
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

internal class GetDate {
    private val simpleDateFormat = SimpleDateFormat(DateConstants.DATE_ISO, Locale.US)

    @Synchronized
    fun fromString(date: String?): Date {
        return try {
            simpleDateFormat.applyPattern(DateConstants.DATE_ISO)
            simpleDateFormat.parse(date)
        } catch (e: ParseException) {
            Date()
        }
    }
}