package com.github.jairrab.datelibrary.lib.modules

import com.github.jairrab.datelibrary.DatePattern
import java.text.DateFormat.*
import java.text.SimpleDateFormat

class GetDatePattern {
    fun getPattern(pattern: DatePattern): String {
        return when (pattern) {
            DatePattern.FULL -> {
                val defaultFormat = "EEE, MMMM d yyyy"
                val localizedPattern = (getDateInstance(FULL) as SimpleDateFormat)
                    .toLocalizedPattern()
                localizedPattern ?: defaultFormat
            }
            DatePattern.LONG -> {
                val defaultFormat = "MMMM d yyyy"
                val localizedPattern = (getDateInstance(LONG) as SimpleDateFormat)
                    .toLocalizedPattern()
                localizedPattern ?: defaultFormat
            }
            DatePattern.MEDIUM -> {
                val defaultFormat = "MMM d yyyy"
                val localizedPattern = (getDateInstance(MEDIUM) as SimpleDateFormat)
                    .toLocalizedPattern()
                localizedPattern ?: defaultFormat
            }
            DatePattern.SHORT -> {
                val defaultFormat = "MM-dd-yyyy"
                val localizedPattern = (getDateInstance(SHORT) as SimpleDateFormat)
                    .toLocalizedPattern()
                localizedPattern ?: defaultFormat
            }
            DatePattern.MONTH -> "MMMM"
            DatePattern.MONTH_DAY -> {
                val defaultFormat = "MMM d"
                val localizedPattern = (getDateInstance(LONG) as SimpleDateFormat)
                    .toLocalizedPattern()
                val regExpPatternTxt = if (localizedPattern.contains("de")) {
                    "[^Mm]*[Yy]+[^Mm]*"
                } else "[^DdMm]*[Yy]+[^DdMm]*"
                val newPattern: String? = localizedPattern.replace(
                    regExpPatternTxt.toRegex(),
                    ""
                )
                newPattern ?: defaultFormat
            }
            DatePattern.MONTH_DAY_SHORT -> {
                val localizedPattern = (getDateInstance(SHORT) as SimpleDateFormat)
                    .toLocalizedPattern()
                val regExpPatternTxt = if (localizedPattern.contains("de")) {
                    "[^Mm]*[Yy]+[^Mm]*"
                } else "[^DdMm]*[Yy]+[^DdMm]*"
                localizedPattern.replace(regExpPatternTxt.toRegex(), "")
            }
            DatePattern.MONTH_YEAR -> {
                val localizedPattern = (getDateInstance(LONG) as SimpleDateFormat)
                    .toLocalizedPattern()
                val regExpPatternTxt = if (localizedPattern.contains("de")) {
                    "[^MmYy]*(^d)+[^MmYy]*"
                } else "[^YyMm]*[Dd]+[^YyMm]"
                localizedPattern.replace(regExpPatternTxt.toRegex(), "")
            }
            DatePattern.MONTH_YEAR_MEDIUM -> {
                val localizedPattern = (getDateInstance(MEDIUM) as SimpleDateFormat)
                    .toLocalizedPattern()
                val regExpPatternTxt = if (localizedPattern.contains("de")) {
                    "[^MmYy]*(^d)+[^MmYy]*"
                } else "[^YyMm]*[Dd]+[^YyMm]"
                localizedPattern.replace(regExpPatternTxt.toRegex(), "")
            }
            DatePattern.MONTH_YEAR_SHORT -> {
                val localizedPattern = (getDateInstance(SHORT) as SimpleDateFormat)
                    .toLocalizedPattern()
                val regExpPatternTxt = "[^YyMm/]*[Dd]+[^YyMm]"
                localizedPattern.replace(regExpPatternTxt.toRegex(), "")
            }
            DatePattern.WEEKDAY_MONTH_DAY_TIME -> "EEE, MMMM d HH:mm"
        }
    }
}