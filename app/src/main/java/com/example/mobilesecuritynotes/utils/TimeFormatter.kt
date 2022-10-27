package com.example.mobilesecuritynotes.utils

import java.text.SimpleDateFormat
import java.util.*

class TimeFormatter {
    private val loc: Locale = Locale("en", "US")
    private val notesDatePattern = "MMM dd, yyyy hh:mm"
    private val notesDateFormat: SimpleDateFormat = SimpleDateFormat(notesDatePattern, loc)

    fun beautifyDateText(date: Date) = notesDateFormat.format(date)
    fun getNoteDateText(str: String) = this.beautifyDateText(Date(str))
}
