package com.github.jairrab.datelibrary.lib.helpers

import java.text.DateFormat
import java.text.SimpleDateFormat

enum class DatePattern {
    FULL, LONG, MEDIUM, SHORT, MONTH,
    MONTH_DAY, MONTH_DAY_SHORT, MONTH_YEAR,
    MONTH_YEAR_MEDIUM, MONTH_YEAR_SHORT,
    WEEKDAY_MONTH_DAY_TIME;

    companion object {
        /**
         * Returns current date using given style
         *
         * @param pattern one of the available [DatePattern]
         * @return date string
         */
        @JvmStatic
        fun getPattern(pattern: DatePattern): String {
            return when (pattern) {
                FULL                   -> {
                    val defaultFormat = "EEE, MMMM d yyyy"
                    val localizedPattern: String? = (DateFormat.getDateInstance(DateFormat.FULL) as SimpleDateFormat).toLocalizedPattern()
                    localizedPattern ?: defaultFormat
                }
                LONG                   -> {
                    val defaultFormat = "MMMM d yyyy"
                    val localizedPattern = (DateFormat.getDateInstance(DateFormat.LONG) as SimpleDateFormat).toLocalizedPattern()
                    localizedPattern ?: defaultFormat
                }
                MEDIUM                 -> {
                    val defaultFormat = "MMM d yyyy"
                    val localizedPattern = (DateFormat.getDateInstance(DateFormat.MEDIUM) as SimpleDateFormat).toLocalizedPattern()
                    localizedPattern ?: defaultFormat
                }
                SHORT                  -> {
                    val defaultFormat = "MM-dd-yyyy"
                    val localizedPattern = (DateFormat.getDateInstance(DateFormat.SHORT) as SimpleDateFormat).toLocalizedPattern()
                    localizedPattern ?: defaultFormat
                }
                MONTH                  -> "MMMM"
                MONTH_DAY              -> {
                    val defaultFormat = "MMM d"
                    val localizedPattern = (DateFormat.getDateInstance(DateFormat.LONG) as SimpleDateFormat).toLocalizedPattern()
                    val regExpPatternTxt = if (localizedPattern.contains("de")) "[^Mm]*[Yy]+[^Mm]*" else "[^DdMm]*[Yy]+[^DdMm]*"
                    val newPattern: String? = localizedPattern.replace(
                        regExpPatternTxt.toRegex(),
                        ""
                    )
                    newPattern ?: defaultFormat
                }
                MONTH_DAY_SHORT        -> {
                    val localizedPattern = (DateFormat.getDateInstance(DateFormat.SHORT) as SimpleDateFormat).toLocalizedPattern()
                    val regExpPatternTxt = if (localizedPattern.contains("de")) "[^Mm]*[Yy]+[^Mm]*" else "[^DdMm]*[Yy]+[^DdMm]*"
                    localizedPattern.replace(regExpPatternTxt.toRegex(), "")
                }
                MONTH_YEAR             -> {
                    val localizedPattern = (DateFormat.getDateInstance(DateFormat.LONG) as SimpleDateFormat).toLocalizedPattern()
                    val regExpPatternTxt = if (localizedPattern.contains("de")) "[^MmYy]*(^d)+[^MmYy]*" else "[^YyMm]*[Dd]+[^YyMm]"
                    localizedPattern.replace(regExpPatternTxt.toRegex(), "")
                }
                MONTH_YEAR_MEDIUM      -> {
                    val localizedPattern = (DateFormat.getDateInstance(DateFormat.MEDIUM) as SimpleDateFormat).toLocalizedPattern()
                    val regExpPatternTxt = if (localizedPattern.contains("de")) "[^MmYy]*(^d)+[^MmYy]*" else "[^YyMm]*[Dd]+[^YyMm]"
                    localizedPattern.replace(regExpPatternTxt.toRegex(), "")
                }
                MONTH_YEAR_SHORT       -> {
                    val localizedPattern = (DateFormat.getDateInstance(DateFormat.SHORT) as SimpleDateFormat).toLocalizedPattern()
                    val regExpPatternTxt = "[^YyMm/]*[Dd]+[^YyMm]"
                    localizedPattern.replace(regExpPatternTxt.toRegex(), "")
                }
                WEEKDAY_MONTH_DAY_TIME -> "EEE, MMMM d HH:mm"
            }
        }
    }
}
