package com.github.jairrab.datelibrary

import com.github.jairrab.datelibrary.lib.DateLibrary
import com.github.jairrab.datelibrary.lib.modules.*
import java.text.DateFormat.LONG
import java.text.DateFormat.getDateInstance
import java.text.SimpleDateFormat
import java.util.*

interface DateUtils {
    var dateFormatPreference: String
    var timeFormatPreference: String
    var startMonthOfYear: Int
    var startMonthDay: Int
    var firstDayOfWeek: Int

    fun updateLocale(locale: Locale)

    fun getCalendar(date: String): Calendar
    fun getCalendar(date: Date): Calendar
    fun getCalendar(date: String, pattern: String): Calendar

    fun getDate(date: String?): Date
    fun getDate(date: String?, pattern: String): Date
    fun getDateOffset(date: Date, field: Int, num: Int): Date

    fun getTime(): Long
    fun getTime(date: Date): Long
    fun getTime(date: String): Long
    fun getTime(year: Int, month: Int, dayOfMonth: Int): Long

    fun getTimeText(hour: Int, minute: Int, seconds: Int): String

    fun getDateTextIso(): String
    fun getDateTextIso(date: Date): String
    fun getDateTextIso(date: String, hour: Int, minute: Int, seconds: Int): String
    fun getDateTextIso(date: String?, frequency: DateFrequency): String
    fun getDateTextIso(hour: Int, minute: Int, seconds: Int, pattern: String): String
    fun getDateTextIso(timeInMills: Long): String

    fun getDateTextIsoTrimmed(): String
    fun getDateTextIsoTrimmed(date: Date): String
    fun getDateTextIsoTrimmed(date: String): String

    fun getDateText(pattern: String): String
    fun getDateText(pattern: DatePattern): String
    fun getDateText(date: Date, pattern: String): String
    fun getDateText(date: Date, pattern: DatePattern): String
    fun getDateText(date: String, pattern: String): String
    fun getDateText(timeInMills: Long, pattern: String): String
    fun getDateText(date: String, pattern: DatePattern): String

    fun getDateTextPreferred(): String
    fun getDateTextPreferred(date: Date): String
    fun getDateTextPreferred(date: String): String
    fun getDateTextPreferred(timeInMills: Long): String

    fun getDateTextIsoOffset(date: String, field: Int, num: Int): String
    fun getDateTextIsoOffset(date: String, dateFrequency: DateFrequency): String
    fun getDateTextIsoOffsetCurrentTime(date: String): String

    fun getDateTextIsoEndOfLastYear(): String
    fun getDateTextIsoEndOfLastQuarter(): String
    fun getDateTextIsoStartOfMonth(useStartMonthSetting: Boolean = true): String
    fun getDateTextIsoEndOfMonth(useStartMonthSetting: Boolean = true): String
    fun getDateTextIsoStartOfLastMonth(useStartMonthSetting: Boolean = true): String
    fun getDateTextIsoEndOfLastMonth(useStartMonthSetting: Boolean = true): String
    fun getDateTextIsoStartOfNextMonth(): String

    fun getDateTextIsoLastYear(): String
    fun getDateTextIsoOffsetYear(period: Period, c: Calendar): String
    fun getDateTextIsoOffsetYear(period: Period, date: String): String
    fun getDateTextIsoOffsetYear(yearsIncrement: Int, period: Period): String

    fun getDateTextIsoOffsetQuarter(period: Period, c: Calendar): String
    fun getDateTextIsoOffsetQuarter(period: Period, date: String): String
    fun getDateTextIsoOffsetQuarter(period: Period): String
    fun getDateTextIsoOffsetQuarterLast(period: Period): String

    fun getDateTextIsoSameDayOfLastMonth(): String
    fun getDateTextIsoOffsetMonth(monthsIncrement: Int): String
    fun getDateTextIsoOffsetMonth(date: String, period: Period): String
    fun getDateTextIsoOffsetMonth(date: String, period: Period, useStartMonth: Boolean): String
    fun getDateTextIsoOffsetMonth(c: Calendar, period: Period): String
    fun getDateTextIsoOffsetMonth(c: Calendar, period: Period, useStartMonth: Boolean): String
    fun getDateTextIsoOffsetDayOfMonth(date: String, day: Int): String
    fun getDateTextIsoOffsetToEndOfMonth(date: String): String
    fun getDateTextIsoOffsetMonthSameDayOfWeek(date: String, addedMonths: Int): String

    fun getDateTextIsoOffsetBiMonthLast(period: Period): String
    fun getDateTextIsoOffsetBiMonth(dateSelect: Period): String

    fun getDateTextIsoOffsetWeek(period: Period, date: String): String
    fun getDateTextIsoOffsetWeek(period: Period, c: Calendar): String
    fun getDateTextIsoOffsetWeek(period: Period, weekIncrement: Int): String

    fun getMonthIndices(startDate: String?, endDate: String?): List<Int>
    fun getWeekDays(): Array<String>
    fun getWeekDaysValues(): Array<String>
    fun getWeekDayName(num: Int): String

    fun getCurrentMonth(): Int
    fun getLastDayOfMonth(date: String): Int
    fun getLastDayOfMonthBasedOnPreference(date: String): Int
    fun getDateParameter(date: String, parameter: Int): Int
    fun getDateParameterQuarter(date: String): Int
    fun getDaysBetween(from: String, to: String): Int
    fun getDaysBetweenExact(from: String, to: String): Double
    fun getDaysBetweenExact(from: Date, to: Date): Double

    fun getDatePattern(pattern: DatePattern): String

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
        fun getInstance(
            dateFormatPreference: String = (getDateInstance(LONG) as SimpleDateFormat).toLocalizedPattern(),
            timeFormatPreference: String = "HH:mm",
            startMonthOfYear: Int = 1,
            startMonthDay: Int = 1,
            firstDayOfWeek: Int = Calendar.SUNDAY,
            locale: Locale = Locale.getDefault(),
        ): DateUtils {
            val checkIsoFormat = CheckIsoFormat()
            val simpleDateFormatUtil = SimpleDateFormatUtil(checkIsoFormat)
            val dateFormatterUtil = DateFormatterUtil(checkIsoFormat)
            return DateLibrary(
                locale = locale,
                dateFormatPreference = dateFormatPreference,
                timeFormatPreference = timeFormatPreference,
                startMonthOfYear = startMonthOfYear,
                startMonthDay = startMonthDay,
                firstDayOfWeek = firstDayOfWeek,
                adders = Adders(),
                compute = Compute(),
                getBiMonth = GetBiMonth(),
                getCalendar = GetCalendar(dateFormatterUtil, simpleDateFormatUtil),
                getDate = GetDate(dateFormatterUtil, simpleDateFormatUtil),
                getDatePattern = GetDatePattern(),
                getLong = GetLong(),
                getMonth = GetMonth(),
                getParameter = GetParameter(),
                getQuarter = GetQuarter(),
                getString = GetString(dateFormatterUtil, simpleDateFormatUtil),
                getWeek = GetWeek(),
                getYear = GetYear(),
                simpleDateFormatUtil = simpleDateFormatUtil,
            )
        }
    }
}

