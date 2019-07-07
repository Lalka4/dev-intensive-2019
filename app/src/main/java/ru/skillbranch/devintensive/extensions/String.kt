package ru.skillbranch.devintensive.extensions

import java.util.regex.Pattern

fun String.truncate(value:Int = 16):String{
    val del = this.length-value
    if (del>0){
        var new = this.dropLast(this.length-value).trim()
        return new+"..."
    }
    else return this
}

fun String.stripHtml():String{
/*    var string = this.replaceAll("\\s+"," ")*/

    val REMOVE_TAGS = Pattern.compile("<.+?>")
    if (this == null || this.length == 0)
    {
        return this
    }
    val m = REMOVE_TAGS.matcher(this)
    val s = m.replaceAll("")
    var CLEAR_PATTERN = Pattern.compile("[\\s]+")

    return CLEAR_PATTERN.matcher(s).replaceAll(" ").trim()

}