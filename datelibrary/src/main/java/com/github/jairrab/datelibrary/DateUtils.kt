package com.github.jairrab.datelibrary

import com.github.jairrab.datelibrary.lib.DateLibrary
import com.github.jairrab.datelibrary.lib.modules.GetYear
import com.github.jairrab.datelibrary.lib.modules.Adders
import com.github.jairrab.datelibrary.lib.modules.CheckIsoFormat
import com.github.jairrab.datelibrary.lib.modules.Compute
import com.github.jairrab.datelibrary.lib.modules.GetBiMonth
import com.github.jairrab.datelibrary.lib.modules.GetCalendar
import com.github.jairrab.datelibrary.lib.modules.GetDate
import com.github.jairrab.datelibrary.lib.modules.GetLong
import com.github.jairrab.datelibrary.lib.modules.GetMonth
import com.github.jairrab.datelibrary.lib.modules.GetParameter
import com.github.jairrab.datelibrary.lib.modules.GetQuarter
import com.github.jairrab.datelibrary.lib.modules.GetString
import com.github.jairrab.datelibrary.lib.modules.GetWeek
import java.util.*

interface DateUtils {
    fun updateLocale()
    fun setDatePatternPreference(pattern: String)
    fun setStartMonthOfYear(startMonthOfYear: String)
    fun setStartMonthDay(startMonthDay: Int)
    fun setFirstDayOfWeek(firstDayOfWeek: Int)

    fun getCalendar(date: String): Calendar
    fun getCalendar(date: Date): Calendar
    fun getCalendar(date: String, pattern: String): Calendar

    fun getDate(date: String?): Date
    fun getDateAdjusted(date: Date, field: Int, num: Int): Date

    fun getTime(): Long
    fun getTime(date: String): Long
    fun getTime(year: Int, month: Int, dayOfMonth: Int): Long

    fun getDateTextIso(): String
    fun getDateTextIso(date: Date): String
    fun getDateTextIso(hour: Int, minute: Int, seconds: Int, pattern: String): String
    fun getDateTextIso(date: String, hour: Int, minute: Int, seconds: Int): String
    fun getDateTextIso(date: String?, frequency: Int): String

    fun getDateTextIsoTrimmed(): String
    fun getDateTextIsoTrimmed(date: Date): String
    fun getDateTextIsoTrimmed(date: String): String

    fun getDateText(pattern: String): String
    fun getDateText(date: Date, pattern: String): String
    fun getDateText(timeInMills: Long): String
    fun getDateText(date: String, pattern: String): String
    fun getDateTextPreferred(date: String): String

    fun getDateTextIsoAdjusted(date: String, field: Int, num: Int): String
    fun getDateTextIsoAdjusted(date: String, frequency: Int): String
    fun getDateTextIsoAdjustedCurrentTime(date: String): String

    fun getDateTextIsoEndOfLastYear(): String
    fun getDateTextIsoEndOfLastQuarter(): String
    fun getDateTextIsoStartOfMonth(useStartMonthSetting: Boolean = true): String
    fun getDateTextIsoEndOfMonth(useStartMonthSetting: Boolean = true): String
    fun getDateTextIsoStartOfLastMonth(useStartMonthSetting: Boolean = true): String
    fun getDateTextIsoEndOfLastMonth(useStartMonthSetting: Boolean = true): String
    fun getDateTextIsoStartOfNextMonth(): String

    fun getDateTextIsoLastYear(): String
    fun getDateTextIsoAdjustedYear(dateSelect: Int, c: Calendar): String
    fun getDateTextIsoAdjustedYear(dateSelect: Int, date: String): String
    fun getDateTextIsoAdjustedYear(yearsIncrement: Int, dateSelect: Int): String

    fun getDateTextIsoAdjustedQuarter(dateSelect: Int, c: Calendar): String
    fun getDateTextIsoAdjustedQuarter(dateSelect: Int, date: String): String
    fun getDateTextIsoAdjustedQuarter(dateSelect: Int): String
    fun getDateTextIsoAdjustedQuarterLast(dateSelect: Int): String

    fun getDateTextIsoSameDayOfLastMonth(): String
    fun getDateTextIsoAdjustedMonth(monthsIncrement: Int): String
    fun getDateTextIsoAdjustedMonth(date: String, dateSelect: Int, useStartMonthSetting: Boolean = true): String
    fun getDateTextIsoAdjustedMonth(c: Calendar, dateSelect: Int, useStartMonthSetting: Boolean = true): String

    fun getDateTextIsoAdjustedDayOfMonth(date: String, day: Int): String
    fun getDateTextIsoAdjustedToEndOfMonth(date: String): String
    fun getDateTextIsoAdjustedMonthSameDayOfWeek(date: String, addedMonths: Int): String

    fun getDateTextIsoAdjustedBiMonthLast(dateSelect: Int): String
    fun getDateTextIsoAdjustedBiMonth(dateSelect: Int): String

    fun getDateTextIsoAdjustedWeek(dateSelect: Int, date: String): String
    fun getDateTextIsoAdjustedWeek(dateSelect: Int, c: Calendar): String
    fun getDateTextIsoAdjustedWeek(dateSelect: Int, weekIncrement: Int): String

    fun getMonthIndices(startDate: String?, endDate: String?): List<Int>
    fun getWeekDays(): Array<String>
    fun getWeekDaysValues(): Array<String>
    fun getWeekDayName(num: Int): String

    fun getCurrentMonth(): Int
    fun getLastDayOfMonth(date: String): Int
    fun getLastDayOfMonthBasedOnPreference(date: String): Int
    fun getDateParameter(date: String, parameter: Int): Int
    fun getDaysBetween(from: String, to: String): Int
    fun getDaysBetweenExact(from: String, to: String): Double
    fun getDaysBetweenExact(from: Date, to: Date): Double

    fun isSameAsCurrentMonth(date: String): Boolean
    fun isFirstDayOfMonth(date: String): Boolean
    fun isFirstDayOfMonthBasedOnPreference(date: String): Boolean
    fun isNthDayOfMonth(date: String, n: Int): Boolean
    fun isMonthlyDates(startDate: String, endDate: String): Boolean
    fun isMonthlyDatesBasedOnPreference(startDate: String, endDate: String): Boolean
    fun isSpanningWholeMonths(startDate: String, endDate: String): Boolean
    fun isFirstBeforeEndDate(firsDay: String, endDate: String): Boolean
    fun isFirstOnOrBeforeEndDate(firsDay: String, endDate: String): Boolean

    companion object {
        fun getInstance(): DateUtils {
            val isoFormat = CheckIsoFormat()
            return DateLibrary(
                getCalendar = GetCalendar(isoFormat),
                getString = GetString(isoFormat),
                getDate = GetDate(),
                getLong = GetLong(),
                adders = Adders(),
                compute = Compute(),
                getYear = GetYear(),
                getQuarter = GetQuarter(),
                getMonth = GetMonth(),
                getBiMonth = GetBiMonth(),
                getWeek = GetWeek(),
                getParameter = GetParameter()
            )
        }
    }
}

