package com.github.jairrab.datelibrary.lib

import com.github.jairrab.datelibrary.DateFormat.DATE_ISO
import com.github.jairrab.datelibrary.DateFrequency
import com.github.jairrab.datelibrary.DatePattern
import com.github.jairrab.datelibrary.DateUtils
import com.github.jairrab.datelibrary.PeriodSelection
import com.github.jairrab.datelibrary.lib.modules.*
import com.github.jairrab.datelibrary.lib.modules.GetParameter.Companion.CALENDAR_QUARTER
import java.text.SimpleDateFormat
import java.util.*

internal class DateLibrary(
    private val getCalendar: GetCalendar,
    private val getString: GetString,
    private val getDate: GetDate,
    private val getDatePattern: GetDatePattern,
    private val getLong: GetLong,
    private val adders: Adders,
    private val compute: Compute,
    private val getYear: GetYear,
    private val getQuarter: GetQuarter,
    private val getMonth: GetMonth,
    private val getBiMonth: GetBiMonth,
    private val getWeek: GetWeek,
    private val getParameter: GetParameter
) : DateUtils {
    private var dateFormatPreference = getDatePattern.getPattern(DatePattern.LONG)

    private val format = "EEEE"
    val friday by lazy { getDateText("2017-01-06 00:00:00", format) }
    val monday by lazy { getDateText("2017-01-02 00:00:00", format) }
    val saturday by lazy { getDateText("2017-01-07 00:00:00", format) }
    val sunday by lazy { getDateText("2017-01-01 00:00:00", format) }
    val thursday by lazy { getDateText("2017-01-05 00:00:00", format) }
    val tuesday by lazy { getDateText("2017-01-03 00:00:00", format) }
    val wednesday by lazy { getDateText("2017-01-04 00:00:00", format) }

    override fun updateLocale() {
        getCalendar.simpleDateFormatLocalized = SimpleDateFormat(DATE_ISO, Locale.getDefault())
        getString.simpleDateFormatLocalized = SimpleDateFormat(DATE_ISO, Locale.getDefault())
    }

    override fun setDatePatternPreference(pattern: String) {
        dateFormatPreference = pattern
    }

    override fun setStartMonthOfYear(startMonthOfYear: String) {
        getYear.startMonthOfYear = startMonthOfYear
    }

    override fun setStartMonthDay(startMonthDay: Int) {
        getMonth.startMonthDay = startMonthDay
        getYear.startMonthDay = startMonthDay
    }

    override fun setFirstDayOfWeek(firstDayOfWeek: Int) {
        getWeek.firstDayOfWeek = firstDayOfWeek
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
        return getDate.fromString(date)
    }

    override fun getDateAdjusted(date: Date, field: Int, num: Int): Date {
        return adders.add(date, field, num)
    }

    override fun getTime(): Long {
        return getLong.getTimeInMillis()
    }

    override fun getTime(date: String): Long {
        return getLong.getTimeInMills(date, this)
    }

    override fun getTime(year: Int, month: Int, dayOfMonth: Int): Long {
        return getLong.getTimeInMills(year, month, dayOfMonth)
    }

    override fun getPattern(pattern: DatePattern): String {
        return getDatePattern.getPattern(pattern)
    }

    override fun getDateTextIso(): String {
        return getString.getToday(this)
    }

    override fun getDateTextIso(date: Date): String {
        return getString.fromDate(this, date)
    }

    override fun getDateTextIso(hour: Int, minute: Int, seconds: Int, pattern: String): String {
        return getString.fromTime(this, hour, minute, seconds, pattern)
    }

    override fun getDateTextIso(date: String, hour: Int, minute: Int, seconds: Int): String {
        return getString.fromTime(this, date, hour, minute, seconds)
    }

    override fun getDateTextIso(date: String?, frequency: DateFrequency): String {
        return getString.getDayFromFrequency(this, date, frequency)
    }

    override fun getDateTextIsoTrimmed(): String {
        return getString.getTodayTrimmed(this)
    }

    override fun getDateTextIsoTrimmed(date: Date): String {
        return getString.fromDateTrimmed(this, date)
    }

    override fun getDateTextIsoTrimmed(date: String): String {
        return getString.trim(this, date)
    }

    override fun getDateText(pattern: String): String {
        return getString.today(this, pattern)
    }

    override fun getDateText(pattern: DatePattern): String {
        return getDateText(getDatePattern.getPattern(pattern))
    }

    override fun getDateText(date: Date, pattern: String): String {
        return getString.fromDate(this, date, pattern)
    }

    override fun getDateText(date: Date, pattern: DatePattern): String {
        return getDateText(date, getDatePattern.getPattern(pattern))
    }

    override fun getDateText(timeInMills: Long): String {
        return getString.fromLong(this, timeInMills)
    }

    override fun getDateText(date: String, pattern: String): String {
        return getString.transform(this, date, pattern)
    }

    override fun getDateText(date: String, pattern: DatePattern): String {
        return getDateText(date, getDatePattern.getPattern(pattern))
    }

    override fun getDateTextPreferred(date: String): String {
        return getString.transform(this, date, dateFormatPreference)
    }

    override fun getDateTextIsoAdjusted(date: String, field: Int, num: Int): String {
        return adders.add(this, date, field, num)
    }

    override fun getDateTextIsoAdjusted(date: String, dateFrequency: DateFrequency): String {
        return adders.add(this, date, dateFrequency)
    }

    override fun getDateTextIsoAdjustedCurrentTime(date: String): String {
        return getString.updateToCurrentTime(this, date)
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

    override fun getDateTextIsoAdjustedYear(dateSelect: PeriodSelection, c: Calendar): String {
        return getYear.getYearDaysOfDate(this, dateSelect, c)
    }

    override fun getDateTextIsoAdjustedYear(dateSelect: PeriodSelection, date: String): String {
        return getYear.getYearDaysOfDate(this, dateSelect, date)
    }

    override fun getDateTextIsoAdjustedYear(yearsIncrement: Int, dateSelect: PeriodSelection): String {
        return getYear.getYearsDays(this, yearsIncrement, dateSelect)
    }

    override fun getDateTextIsoAdjustedQuarter(dateSelect: PeriodSelection, c: Calendar): String {
        return getQuarter.getQuartersDaysOfDate(this, dateSelect, c)
    }

    override fun getDateTextIsoAdjustedQuarter(dateSelect: PeriodSelection, date: String): String {
        return getQuarter.getQuartersDaysOfDate(this, dateSelect, date)
    }

    override fun getDateTextIsoAdjustedQuarter(dateSelect: PeriodSelection): String {
        return getQuarter.getThisQuartersDays(this, dateSelect)
    }

    override fun getDateTextIsoAdjustedQuarterLast(dateSelect: PeriodSelection): String {
        return getQuarter.getLastQuartersDays(this, dateSelect)
    }

    override fun getDateTextIsoSameDayOfLastMonth(): String {
        return getMonth.getSameDayLastMonth(this)
    }

    override fun getDateTextIsoAdjustedMonth(monthsIncrement: Int): String {
        return getMonth.withIncrement(this, monthsIncrement)
    }

    override fun getDateTextIsoAdjustedMonth(
        date: String,
        dateSelect: PeriodSelection,
        useStartMonthSetting: Boolean
    ): String {
        return getMonth.fromString(this, date, dateSelect, useStartMonthSetting)
    }

    override fun getDateTextIsoAdjustedMonth(
        c: Calendar,
        dateSelect: PeriodSelection,
        useStartMonthSetting: Boolean
    ): String {
        return getMonth.fromCalendar(this, c, dateSelect, useStartMonthSetting)
    }

    override fun getDateTextIsoAdjustedDayOfMonth(date: String, day: Int): String {
        return getMonth.setMonthDate(this, date, day)
    }

    override fun getDateTextIsoAdjustedToEndOfMonth(date: String): String {
        return getMonth.setEndOfMonth(this, date)
    }

    override fun getDateTextIsoAdjustedMonthSameDayOfWeek(date: String, addedMonths: Int): String {
        return getMonth.addMonthsSameDayOfWeek(this, date, addedMonths)
    }

    override fun getDateTextIsoAdjustedBiMonthLast(dateSelect: PeriodSelection): String {
        return getBiMonth.getLastBiMonthDays(this, dateSelect)
    }

    override fun getDateTextIsoAdjustedBiMonth(dateSelect: PeriodSelection): String {
        return getBiMonth.getThisBiMonthDays(this, dateSelect)
    }

    override fun getDateTextIsoAdjustedWeek(dateSelect: PeriodSelection, date: String): String {
        return getWeek.getWeekDaysOfDate(this, dateSelect, date)
    }

    override fun getDateTextIsoAdjustedWeek(dateSelect: PeriodSelection, c: Calendar): String {
        return getWeek.getWeekDaysOfDate(this, dateSelect, c)
    }

    override fun getDateTextIsoAdjustedWeek(
        dateSelect: PeriodSelection,
        weekIncrement: Int
    ): String {
        return getWeek.withIncrement(this, dateSelect, weekIncrement)
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