package com.github.jairrab.datelibrary.lib.modules

import com.github.jairrab.datelibrary.DateFormat

class CheckIsoFormat {
    /**
     * checks if the provided format is ISO
     *
     * @param format given format
     * @return true if ISO format
     */
    fun check(format: String): Boolean {
        return format == DateFormat.DATE_ISO ||
            format == DateFormat.DATE_ISO_TRIMMED ||
            format == DateFormat.DATE_ISO_NO_COLON ||
            format == DateFormat.MATERIAL_CALENDAR_VIEW
    }
}