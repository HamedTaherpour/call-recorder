package io.hamed.callrecorder.date

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


/**
 * Author: Hamed Taherpour
 * *
 * Created: 10/23/2019
 */
class DateUtility {

    companion object {
        lateinit var sdf: SimpleDateFormat
        lateinit var jalaliCalendar: JalaliCalendar

        fun getMyDate(date: String): String {
            if (sdf == null)
                sdf = SimpleDateFormat("EEE LLL dd HH:mm:ss Z yyyy", Locale.ENGLISH)
            if (jalaliCalendar == null)
                jalaliCalendar = JalaliCalendar()
            val cal = Calendar.getInstance()
            try {
                cal.setTime(sdf.parse(date))
                jalaliCalendar.fromGregorian(
                    GregorianCalendar(
                        cal.get(Calendar.YEAR),
                        cal.get(Calendar.MONTH),
                        cal.get(Calendar.DAY_OF_MONTH)
                    )
                )
                return jalaliCalendar.toString() + " - " + cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(
                    Calendar.MINUTE
                )
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            return ""
        }
    }
}