package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs

const val SECOND = 1000L
const val MINUTE = 60* SECOND
const val HOUR = 60* MINUTE
const val DAY = 24* HOUR

enum class TimeUnits{
    SECOND,
    MINUTE,
    HOUR,
    DAY
}

fun Date.format(pattern:String= "HH:mm:ss dd.MM.yy"):String{
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value:Int, units: TimeUnits = TimeUnits.SECOND): Date{
    var time = this.time
    time+=when(units){
        TimeUnits.SECOND-> value* SECOND
        TimeUnits.MINUTE-> value* MINUTE
        TimeUnits.HOUR-> value* HOUR
        TimeUnits.DAY-> value* DAY

    }
    this.time = time
    return this
}


fun Date.humanizeDiff(date:Date = Date()) {
    val diffSeconds = ((date.time - this.time) / 1000).toInt()
    val days = diffSeconds / 86400
    val hours = diffSeconds / 3600
    val minutes = diffSeconds / 60

    if (diffSeconds >= 0) {
        if (diffSeconds >= 1) {
            if (diffSeconds >= 45) {
                if (diffSeconds >= 75) {
                    if (minutes >= 45) {
                        if (minutes >= 75) {
                            if (hours >= 22) {
                                if (hours >= 26) {
                                    if (days >= 360) {
                                        println("более года назад")
                                    } else when (days) {
                                        in 1..360 step 10 -> println("$days день назад")
                                        in 2..360 step 10, in 3..360 step 10, in 4..360 step 10 -> println("$days дня назад")
                                        else -> println("$days дней назад")
                                    }
                                } else println("день назад")
                            } else when (hours) {
                                1, 21 -> println("$hours час назад")
                                2, 3, 4, 22 -> println("$hours часа назад")
                                else -> println("$hours часов назад")
                            }
                        } else println("час назад")
                    } else when (minutes) {
                        in 1..60 step 10 -> println("$minutes минуту назад")
                        in 2..60 step 10, in 3..60 step 10, in 4..60 step 10 -> println("$minutes минуты назад")
                        else -> println("$minutes минут назад")
                    }
                } else println("минуту назад")
            } else println("несколько секунд назад")
        } else println("только что")
    } else {
        if (diffSeconds <= -1) {
            if (diffSeconds <= -45) {
                if (diffSeconds <= -75) {
                    if (minutes <= -45) {
                        if (minutes <= -75) {
                            if (hours <= -22) {
                                if (hours <= -26) {
                                    if (days <= -360) {
                                        println("более чем через год")
                                    } else when (days) {
                                        in -351..-1 step 10 -> println("через ${abs(days)} день")
                                        in -352..-2 step 10, in -353..-3 step 10, in -354..-4 step 10 -> println("через ${abs(days)} дня")
                                        else -> println("через ${abs(days)} дней")
                                    }
                                } else println("через день")
                            } else when (hours) {
                                -1, -21 -> println("через ${abs(hours)} час")
                                -2, -3, -4, -22 -> println("через ${abs(hours)} часа")
                                else -> println("через ${abs(hours)} часов")
                            }
                        } else println("час назад")
                    } else when (minutes) {
                        in -51..-1 step 10 -> println("через ${abs(minutes)} минуту")
                        in -52..-2 step 10, in -53..-3 step 10, in -54..-4 step 10 -> println("через ${abs(minutes)} минуты")
                        else -> println("через ${abs(minutes)} минут")
                    }
                } else println("через минуту")
            } else println("через несколько секунд")
        } else println("только что")
    }
}