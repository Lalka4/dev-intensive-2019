package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs

const val SECOND = 1000L
const val MINUTE = 60* SECOND
const val HOUR = 60* MINUTE
const val DAY = 24* HOUR

interface Time{
    fun plural(value: Int){}
}

enum class TimeUnits:Time {
    SECOND {
        override fun plural(value: Int) {
            val all = 1..Int.MAX_VALUE step 10
            val all1 = 2..Int.MAX_VALUE step 10
            val all2 = 3..Int.MAX_VALUE step 10
            val all3 = 4..Int.MAX_VALUE step 10
            when (value) {
                in all -> println("$value секунду")
                in all1, in all2, in all3 -> println("$value секунды")
                else -> println("$value секунд")
            }
        }
    },
    MINUTE {
        override fun plural(value: Int) {
            val all = 1..Int.MAX_VALUE step 10
            val all1 = 2..Int.MAX_VALUE step 10
            val all2 = 3..Int.MAX_VALUE step 10
            val all3 = 4..Int.MAX_VALUE step 10
            when (value) {
                in all -> println("$value минуту")
                in all1, in all2, in all3 -> println("$value минуты")
                else -> println("$value минут")
            }
        }
    },
    HOUR {
        override fun plural(value: Int) {
            val all = 1..Int.MAX_VALUE step 10
            val all1 = 2..Int.MAX_VALUE step 10
            val all2 = 3..Int.MAX_VALUE step 10
            val all3 = 4..Int.MAX_VALUE step 10
            when (value) {
                in all -> println("$value час")
                in all1, in all2, in all3 -> println("$value часа")
                else -> println("$value часов")
            }
        }
    },
    DAY {
        override fun plural(value: Int) {
            val all = 1..Int.MAX_VALUE step 10
            val all1 = 2..Int.MAX_VALUE step 10
            val all2 = 3..Int.MAX_VALUE step 10
            val all3 = 4..Int.MAX_VALUE step 10
            when (value) {
                in all -> println("$value день")
                in all1, in all2, in all3 -> println("$value дня")
                else -> println("$value дней")
            }
        }
    };
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


fun Date.humanizeDiff(date:Date = Date()):String {
    val diffSeconds = ((date.time - this.time) / 1000).toInt()
    val days = diffSeconds / 86400
    val hours = diffSeconds / 3600
    val minutes = diffSeconds / 60

    if (diffSeconds >= 0) {
        if (diffSeconds > 1) {
            if (diffSeconds > 45) {
                if (diffSeconds > 75) {
                    if (minutes > 45) {
                        if (minutes > 75) {
                            if (hours > 22) {
                                if (hours > 26) {
                                    if (days > 360) {
                                        return "более года назад"
                                    } else when (days) {
                                        in 1..360 step 10 -> return "${abs(days)} день назад"
                                        in 2..360 step 10, in 3..360 step 10, in 4..360 step 10 -> return "${abs(days)} дня назад"
                                        else -> return "${abs(days)} дней назад"
                                    }
                                } else return "день назад"
                            } else when (hours) {
                                1, 21 -> return "${abs(hours)} час назад"
                                2, 3, 4, 22 -> return "${abs(hours)} часа назад"
                                else -> return "${abs(hours)} часов назад"
                            }
                        } else return "час назад"
                    } else when (minutes) {
                        in 1..60 step 10 -> return "${abs(minutes)} минуту назад"
                        in 2..60 step 10, in 3..60 step 10, in 4..60 step 10 -> return "${abs(minutes)} минуты назад"
                        else -> return "${abs(minutes)} минут назад"
                    }
                } else return "минуту назад"
            } else return "несколько секунд назад"
        } else return "только что"
    } else {
        if (diffSeconds < -1) {
            if (diffSeconds < -45) {
                if (diffSeconds < -75) {
                    if (minutes < -45) {
                        if (minutes < -75) {
                            if (hours < -22) {
                                if (hours < -26) {
                                    if (days < -360) {
                                        return "более чем через год"
                                    } else when (days) {
                                        in -351..-1 step 10 -> return "через ${abs(days)} день"
                                        in -352..-2 step 10, in -353..-3 step 10, in -354..-4 step 10 -> return "через ${abs(days)} дня"
                                        else -> return "через ${abs(days)} дней"
                                    }
                                } else return "через день"
                            } else when (hours) {
                                -1, -21 -> return "через ${abs(hours)} час"
                                -2, -3, -4, -22 -> return "через ${abs(hours)} часа"
                                else -> return "через ${abs(hours)} часов"
                            }
                        } else return "час назад"
                    } else when (minutes) {
                        in -51..-1 step 10 -> return "через ${abs(minutes)} минуту"
                        in -52..-2 step 10, in -53..-3 step 10, in -54..-4 step 10 -> return "через ${abs(minutes)} минуты"
                        else -> return "через ${abs(minutes)} минут"
                    }
                } else return "через минуту"
            } else return "через несколько секунд"
        } else return "только что"
    }
}