package com.example.mobilesecuritynotes.utils

import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

class TimeFormatter {
    private val loc: Locale = Locale("en", "US")
    private val notesDatePattern = "MMM dd, yyyy HH:mm"
    private val notesDateFormat: SimpleDateFormat = SimpleDateFormat(notesDatePattern, loc)

    fun beautifyDateText(date: Date) = notesDateFormat.format(date)
    fun getNoteDateText(long: Long) = this.beautifyDateText(Date(Timestamp(long).time))
}
