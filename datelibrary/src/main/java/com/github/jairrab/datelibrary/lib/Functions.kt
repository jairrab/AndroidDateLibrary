package com.github.jairrab.datelibrary.lib

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

val Date.toLocalTime: LocalDateTime
    @RequiresApi(Build.VERSION_CODES.O)
    get() = toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime() ?: LocalDateTime.now()

val LocalDateTime.toDate: Date
    @RequiresApi(Build.VERSION_CODES.O)
    get() = Date.from(atZone(ZoneId.systemDefault()).toInstant())

val LocalDate.toDate: Date
    @RequiresApi(Build.VERSION_CODES.O)
    get() = Date.from(atStartOfDay(ZoneId.systemDefault()).toInstant())
