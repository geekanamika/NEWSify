package com.example.newsapp.utils

import android.annotation.SuppressLint
import android.text.format.DateUtils
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object DateUtils {
    // convert date to format as 5 hours ago
    fun manipulateDateFormat(post_date: String?): String {
        if (post_date == null) return "" //if no date is returned by the API then set corresponding date view to empty
        if (post_date == "0001-01-01T00:00:00Z") //because Times of India is always returning this in time stamp which is Jan1,1 (wrong information they are sending)
            return ""
        @SuppressLint("SimpleDateFormat") val existingUTCFormat =
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        var date: Date? = null
        try {
            date = existingUTCFormat.parse(post_date)
        } catch (e: Throwable) {
            e.printStackTrace()
        }
        return if (date != null) { // Converting timestamp into x ago format
            val timeAgo =
                DateUtils.getRelativeTimeSpanString(
                    date.time.toString().toLong(),
                    System.currentTimeMillis(),
                    DateUtils.SECOND_IN_MILLIS
                )
            timeAgo.toString() + ""
        } else {
            post_date
        }
    }
}
