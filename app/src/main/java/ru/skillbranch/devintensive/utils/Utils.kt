package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName:String?):Pair<String?,String?>{
        val parts:List<String>?=fullName?.split(" ")
        val firstName = if(parts?.getOrNull(0)=="") null else parts?.getOrNull(0)
        val lastName = if(parts?.getOrNull(1)=="") null else parts?.getOrNull(1)
        return firstName to lastName
    }

    fun toInitials(firstName:String?, lastName:String?):String? = "${firstName?.get(0)?: ""}".toUpperCase()+
            "${lastName?.get(0) ?: ""}".toUpperCase()

    var liters: Map<String, String> = mapOf("а" to "a", "б" to "b", "в" to "v", "г" to "g", "д" to "d", "е" to "e",
        "ё" to "e", "ж" to "zh", "з" to "z", "и" to "i", "й" to "i", "к" to "k", "л" to "l", "м" to "m", "н" to "n",
        "о" to "o", "п" to "p", "р" to "r", "с" to "s", "т" to "t", "у" to "u", "ф" to "f", "х" to "h", "ц" to "c",
        "ч" to "ch", "ш" to "sh", "щ" to "sh\'", "ъ" to "", "ы" to "i", "ь" to "", "э" to "e", "ю" to "yu", "я" to "ya")

    fun transliteration(payload:String, divider:String? = " "):String?{
        val str:List<String> = payload.split(" ")
        val firstName:List<String> = str[0].toLowerCase().split("")
        val lastName:List<String> = str[1].toLowerCase().split("")
        var newFirstName= ""
        var newLastName= ""
        
        for (char in firstName){
            newFirstName+=liters[char]?:char
        }
        for (char in lastName){
            newLastName += liters[char]?:char
        }

        return "${newFirstName.dropLast(newFirstName.count()-1).toUpperCase()}${newFirstName.drop(1)}$divider${newLastName.dropLast(newLastName.count()-1).toUpperCase()}${newLastName.drop(1)}"
    }
}