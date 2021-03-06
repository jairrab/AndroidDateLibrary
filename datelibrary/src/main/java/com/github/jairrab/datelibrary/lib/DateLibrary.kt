package com.github.jairrab.datelibrary.lib

import com.github.jairrab.datelibrary.*
import com.github.jairrab.datelibrary.DateFormat.DATE_ISO
import com.github.jairrab.datelibrary.DateFormat.DATE_ISO_TRIMMED
import com.github.jairrab.datelibrary.lib.modules.*
import com.github.jairrab.datelibrary.lib.modules.GetParameter.Companion.CALENDAR_QUARTER
import java.util.*

internal class DateLibrary(
    override var dateFormatPreference: String,
    override var timeFormatPreference: String,
    override var startMonthOfYear: Int,
    override var startMonthDay: Int,
    override var firstDayOfWeek: Int,
    private var locale: Locale,
    private val adders: Adders,
    private val compute: Compute,
    private val getBiMonth: GetBiMonth,
    private val getCalendar: GetCalendar,
    private val getDate: GetDate,
    private val getDatePattern: GetDatePattern,
    private val getLong: GetLong,
    private val getMonth: GetMonth,
    private val getParameter: GetParameter,
    private val getQuarter: GetQuarter,
    private val getString: GetString,
    private val getWeek: GetWeek,
    private val getYear: GetYear,
    private val simpleDateFormatUtil: SimpleDateFormatUtil,
) : DateUtils {

    init {
        simpleDateFormatUtil.updateLocale(locale)
    }

    private val format = "EEEE"
    val friday by lazy { getDateText("2017-01-06 00:00:00", format) }
    val monday by lazy { getDateText("2017-01-02 00:00:00", format) }
    val saturday by lazy { getDateText("2017-01-07 00:00:00", format) }
    val sunday by lazy { getDateText("2017-01-01 00:00:00", format) }
    val thursday by lazy { getDateText("2017-01-05 00:00:00", format) }
    val tuesday by lazy { getDateText("2017-01-03 00:00:00", format) }
    val wednesday by lazy { getDateText("2017-01-04 00:00:00", format) }

    override fun updateLocale(locale: Locale) {
        this.locale = locale
        simpleDateFormatUtil.updateLocale(locale)
    }

    override fun getCalendar(date: String): Calendar {
        return getCalendar.getCalendar(this, date)
    }

    override fun getCalendar(date: Date): Calendar {
        return getCalendar.getCalendar(date)
    }

    override fun getCalendar(date: String, pattern: String): Calendar {
        return getCalendar.getCalendar(this, date, pattern)
    }

    override fun getDate(date: String?): Date {
        if (date == null || date.isEmpty()) return Date()
        return getDate.getDate(date)
    }

    override fun getDate(date: String?, pattern: String): Date {
        if (date == null || date.isEmpty()) return Date()
        return getDate.getDate(date, pattern)
    }

    override fun getDateOffset(date: Date, field: Int, num: Int): Date {
        return adders.add(date, field, num)
    }

    override fun getDateTrimmed(date: Date): Date {
        return getDate.getDateTrimmed(date)
    }

    override fun getDateTrimmed(time: Long): Date {
        return getDate.getDateTrimmed(Date(time))
    }

    override fun getTime(): Long {
        return getLong.getTimeInMillis()
    }

    override fun getTime(date: Date): Long {
        return date.time
    }

    override fun getTime(date: String): Long {
        return getLong.getTimeInMills(date, this)
    }

    override fun getTime(year: Int, month: Int, dayOfMonth: Int): Long {
        return getLong.getTimeInMills(year, month, dayOfMonth)
    }

    override fun getTimeText(hour: Int, minute: Int, seconds: Int): String {
        return getString.getDateText(this, hour, minute, seconds, timeFormatPreference)
    }

    override fun getDateTextIso(): String {
        return getDateTextIso(Date())
    }

    override fun getDateTextIso(date: Date): String {
        return getDateText(date, DATE_ISO)
    }

    override fun getDateTextIso(date: String, hour: Int, minute: Int, seconds: Int): String {
        return getString.getDateText(this, date, hour, minute, seconds)
    }

    override fun getDateTextIso(date: String?, frequency: DateFrequency): String {
        return getString.getDateText(this, date ?: getDateTextIso(), frequency)
    }

    override fun getDateTextIso(hour: Int, minute: Int, seconds: Int, pattern: String): String {
        return getString.getDateText(this, hour, minute, seconds, pattern)
    }

    override fun getDateTextIso(timeInMills: Long): String {
        return getDateText(Date(timeInMills), DATE_ISO)
    }

    override fun getDateTextIsoTrimmed(): String {
        return getDateTextIsoTrimmed(Date())
    }

    override fun getDateTextIsoTrimmed(date: Date): String {
        return getDateText(date, DATE_ISO_TRIMMED)
    }

    override fun getDateTextIsoTrimmed(date: String): String {
        return getDateText(getDate(date), DATE_ISO_TRIMMED)
    }

    override fun getDateText(pattern: String): String {
        return getDateText(Date(), pattern)
    }

    override fun getDateText(pattern: DatePattern): String {
        return getDateText(Date(), pattern)
    }

    override fun getDateText(date: Date, pattern: DatePattern): String {
        return try {
            getDateText(date, getDatePattern.getPattern(pattern))
        } catch (e: Exception) {
            //fallback in case parsing fails
            when (pattern) {
                DatePattern.FULL -> getDateTextPreferred(date)
                DatePattern.LONG -> getDateTextPreferred(date)
                DatePattern.MEDIUM -> getDateText(date, DateFormat.YEAR_MONTH_DAY_SHORT)
                DatePattern.SHORT -> getDateText(date, DateFormat.YEAR_MONTH_DAY_SHORT)
                DatePattern.MONTH -> getDateText(date, DateFormat.MONTH)
                DatePattern.MONTH_DAY -> getDateText(date, DateFormat.MONTH_DAY_SHORT)
                DatePattern.MONTH_DAY_SHORT -> getDateText(date, DateFormat.MONTH_DAY_SHORT)
                DatePattern.MONTH_YEAR -> getDateText(date, DateFormat.YEAR_MONTH_SHORT)
                DatePattern.MONTH_YEAR_MEDIUM -> getDateText(date, DateFormat.YEAR_MONTH_SHORT)
                DatePattern.MONTH_YEAR_SHORT -> getDateText(date, DateFormat.YEAR_MONTH_SHORT)
                DatePattern.WEEKDAY_MONTH_DAY_TIME -> getDateTextPreferred(date)
            }
        }
    }

    override fun getDateText(date: String, pattern: DatePattern): String {
        return getDateText(getDate(date), pattern)
    }

    override fun getDateText(date: Date, pattern: String): String {
        return getString.getDateText(this, date, pattern)
    }

    override fun getDateText(date: String, pattern: String): String {
        return getDateText(getDate(date), pattern)
    }

    override fun getDateText(timeInMills: Long, pattern: String): String {
        return getDateText(Date(timeInMills), pattern)
    }

    override fun getDateTextPreferred(): String {
        return getDateTextPreferred(Date())
    }

    override fun getDateTextPreferred(date: Date): String {
        return getDateText(date, dateFormatPreference)
    }

    override fun getDateTextPreferred(date: String): String {
        return getDateText(getDate(date), dateFormatPreference)
    }

    override fun getDateTextPreferred(timeInMills: Long): String {
        return getDateText(Date(timeInMills), dateFormatPreference)
    }

    override fun getDateTextIsoOffset(date: String, field: Int, num: Int): String {
        return adders.add(this, date, field, num)
    }

    override fun getDateTextIsoOffset(date: String, dateFrequency: DateFrequency): String {
        return adders.add(this, date, dateFrequency)
    }

    override fun getDateTextIsoOffsetCurrentTime(date: String): String {
        return getString.getDateTextIsoOffsetCurrentTime(this, date)
    }

    override fun getDateTextIsoEndOfLastYear(): String {
        return getYear.getEndLastYear(this)
    }

    override fun getDateTextIsoEndOfLastQuarter(): String {
        return getQuarter.getEndLastQuarter(this)
    }

    override fun getDateTextIsoStartOfMonth(useStartMonthSetting: Boolean): String {
        return getMonth.startOfMonth(this, useStartMonthSetting)
    }

    override fun getDateTextIsoEndOfMonth(useStartMonthSetting: Boolean): String {
        return getMonth.endOfMonth(this, useStartMonthSetting)
    }

    override fun getDateTextIsoStartOfLastMonth(useStartMonthSetting: Boolean): String {
        return getMonth.startOfLastMonth(this, useStartMonthSetting)
    }

    override fun getDateTextIsoEndOfLastMonth(useStartMonthSetting: Boolean): String {
        return getMonth.endOfLastMonth(this, useStartMonthSetting)
    }

    override fun getDateTextIsoStartOfNextMonth(): String {
        return getMonth.startOfNextMonth(this)
    }

    override fun getDateTextIsoLastYear(): String {
        return getYear.getSinceLastYear(this)
    }

    override fun getDateTextIsoOffsetYear(period: Period, c: Calendar): String {
        return getYear.getYearDaysOfDate(this, period, c)
    }

    override fun getDateTextIsoOffsetYear(period: Period, date: String): String {
        return getYear.getYearDaysOfDate(this, period, date)
    }

    override fun getDateTextIsoOffsetYear(yearsIncrement: Int, period: Period): String {
        return getYear.getYearsDays(this, yearsIncrement, period)
    }

    override fun getDateTextIsoOffsetQuarter(period: Period, c: Calendar): String {
        return getQuarter.getQuartersDaysOfDate(this, period, c)
    }

    override fun getDateTextIsoOffsetQuarter(period: Period, date: String): String {
        return getQuarter.getQuartersDaysOfDate(this, period, date)
    }

    override fun getDateTextIsoOffsetQuarter(period: Period): String {
        return getQuarter.getThisQuartersDays(this, period)
    }

    override fun getDateTextIsoOffsetQuarterLast(period: Period): String {
        return getQuarter.getLastQuartersDays(this, period)
    }

    override fun getDateTextIsoSameDayOfLastMonth(): String {
        return getMonth.getSameDayLastMonth(this)
    }

    override fun getDateTextIsoOffsetMonth(monthsIncrement: Int): String {
        return getMonth.withIncrement(this, monthsIncrement)
    }

    override fun getDateTextIsoOffsetMonth(date: String, period: Period): String {
        return getDateTextIsoOffsetMonth(date, period, true)
    }

    override fun getDateTextIsoOffsetMonth(date: String, period: Period, useStartMonth: Boolean) =
        getMonth.fromString(this, date, period, useStartMonth)

    override fun getDateTextIsoOffsetMonth(c: Calendar, period: Period): String {
        return getDateTextIsoOffsetMonth(c, period, true)
    }

    override fun getDateTextIsoOffsetMonth(c: Calendar, period: Period, useStartMonth: Boolean) =
        getMonth.fromCalendar(this, c, period, useStartMonth)

    override fun getDateTextIsoOffsetDayOfMonth(date: String, day: Int): String {
        return getMonth.setMonthDate(this, date, day)
    }

    override fun getDateTextIsoOffsetToEndOfMonth(date: String): String {
        return getMonth.setEndOfMonth(this, date)
    }

    override fun getDateTextIsoOffsetMonthSameDayOfWeek(date: String, addedMonths: Int): String {
        return getMonth.addMonthsSameDayOfWeek(this, date, addedMonths)
    }

    override fun getDateTextIsoOffsetBiMonthLast(period: Period): String {
        return getBiMonth.getLastBiMonthDays(this, period)
    }

    override fun getDateTextIsoOffsetBiMonth(dateSelect: Period): String {
        return getBiMonth.getThisBiMonthDays(this, dateSelect)
    }

    override fun getDateTextIsoOffsetWeek(period: Period, date: String): String {
        return getWeek.getWeekDaysOfDate(this, period, date)
    }

    override fun getDateTextIsoOffsetWeek(period: Period, c: Calendar): String {
        return getWeek.getWeekDaysOfDate(this, period, c)
    }

    override fun getDateTextIsoOffsetWeek(period: Period, weekIncrement: Int): String {
        return getWeek.withIncrement(this, period, weekIncrement)
    }

    override fun getMonthIndices(startDate: String?, endDate: String?): List<Int> {
        return getMonth.getMonthIndices(this, startDate, endDate)
    }

    override fun getWeekDays(): Array<String> {
        return getWeek.getWeekDays(this)
    }

    override fun getWeekDaysValues(): Array<String> {
        return getWeek.getWeekDaysValues()
    }

    override fun getWeekDayName(num: Int): String {
        return getWeek.name(this, num)
    }

    override fun getCurrentMonth(): Int {
        return getMonth.getCurrentMonth()
    }

    override fun getLastDayOfMonth(date: String): Int {
        return getMonth.getLastDayOfMonth(this, date)
    }

    override fun getLastDayOfMonthBasedOnPreference(date: String): Int {
        return getMonth.getLastDayOfMonthBasedOnPreference(this, date)
    }

    override fun getDateParameter(date: Date, parameter: Int): Int {
        return getParameter.getDateParameter(this, date, parameter)
    }

    override fun getDateParameter(date: String, parameter: Int): Int {
        return getParameter.getDateParameter(this, date, parameter)
    }

    override fun getDateParameterQuarter(date: String): Int {
        return getParameter.getDateParameter(this, date, CALENDAR_QUARTER)
    }

    override fun getDaysBetween(from: String, to: String): Int {
        return compute.getDaysBetweenWhole(this, from, to)
    }

    override fun getDaysBetweenExact(from: String, to: String): Double {
        return compute.getDaysBetweenExact(this, from, to)
    }

    override fun getDaysBetweenExact(from: Date, to: Date): Double {
        return compute.getDaysBetweenExact(from, to)
    }

    override fun getDatePattern(pattern: DatePattern): String {
        return getDatePattern.getPattern(pattern)
    }

    override fun isSameAsCurrentMonth(date: String): Boolean {
        return getMonth.isSameAsCurrentMonth(this, date)
    }

    override fun isFirstDayOfMonth(date: String): Boolean {
        return getMonth.isFirstDayOfMonth(this, date)
    }

    override fun isFirstDayOfMonthBasedOnPreference(date: String): Boolean {
        return getMonth.isFirstDayOfMonthBasedOnPreference(this, date)
    }

    override fun isNthDayOfMonth(date: String, n: Int): Boolean {
        return getMonth.isNthDayOfMonth(this, date, n)
    }

    override fun isMonthlyDates(startDate: String, endDate: String): Boolean {
        return getMonth.isMonthlyDates(this, startDate, endDate)
    }

    override fun isMonthlyDatesBasedOnPreference(startDate: String, endDate: String): Boolean {
        return getMonth.isMonthlyDatesBasedOnPreference(this, startDate, endDate)
    }

    override fun isSpanningWholeMonths(startDate: String, endDate: String): Boolean {
        return getMonth.isSpanningWholeMonths(this, startDate, endDate)
    }

    override fun isFirstBeforeEndDate(firsDay: String, endDate: String): Boolean {
        return compute.isFirstBeforeEndDate(this, firsDay, endDate)
    }

    override fun isFirstOnOrBeforeEndDate(firsDay: String, endDate: String): Boolean {
        return compute.isFirstOnOrBeforeEndDate(this, firsDay, endDate)
    }
}