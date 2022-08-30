package com.a71cities.trfc.api

import java.text.SimpleDateFormat
import java.util.*

fun main() {
    val reminderDate = "2022-08-26"
    val reminderTime = "08:44:00 PM"

    val dateForAlarm = customizeDateTimeForAlarm(reminderDate,reminderTime)


        println(dateForAlarm!!.after(Date()))
        println(Date().after(dateForAlarm))

}

fun customizeDateTimeForAlarm(
    reminderDate: String,
    reminderTime: String
): Date? {//2022-05-06 08:00:00 PM
    return SimpleDateFormat(
        "yyyy-MM-dd hh:mm:ss a",
        Locale.ENGLISH
    ).parse(StringBuilder().append(reminderDate).append(" ").append(reminderTime).toString())
}
