package com.github.jairrab.datelibrary.lib.modules

import com.github.jairrab.datelibrary.DateConstants

class CheckIsoFormat {
    /**
     * checks if the provided format is ISO
     *
     * @param format given format
     * @return true if ISO format
     */
    fun check(format: String): Boolean {
        return format == DateConstants.DATE_ISO ||
            format == DateConstants.DATE_ISO_TRIMMED ||
            format == DateConstants.DATE_ISO_NO_COLON ||
            format == DateConstants.MATERIAL_CALENDAR_VIEW
    }
}