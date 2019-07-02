package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName: String?): Pair<String?, String?> {
        val parts: List<String>? = fullName?.split(" ")
        val firstName = if (parts?.getOrNull(0) == "") null else parts?.getOrNull(0)
        val lastName = if (parts?.getOrNull(1) == "") null else parts?.getOrNull(1)
        return firstName to lastName
    }

    fun toInitials(firstName: String?, lastName: String?): String? {

        when (firstName?.trim()) {
            null, "", " " -> when (lastName?.trim()) {
                null, "", " " -> return null
                else -> return "${lastName.get(0)}".toUpperCase()
            }
            else -> when (lastName?.trim()) {
                null, "", " " -> return "${firstName.get(0)}".toUpperCase()
                else -> return "${firstName.get(0)}".toUpperCase() + "${lastName.get(0)}".toUpperCase()
            }
        }
    }

    var liters: Map<String, String> = mapOf(
        "а" to "a",
        "б" to "b",
        "в" to "v",
        "г" to "g",
        "д" to "d",
        "е" to "e",
        "ё" to "e",
        "ж" to "zh",
        "з" to "z",
        "и" to "i",
        "й" to "i",
        "к" to "k",
        "л" to "l",
        "м" to "m",
        "н" to "n",
        "о" to "o",
        "п" to "p",
        "р" to "r",
        "с" to "s",
        "т" to "t",
        "у" to "u",
        "ф" to "f",
        "х" to "h",
        "ц" to "c",
        "ч" to "ch",
        "ш" to "sh",
        "щ" to "sh\'",
        "ъ" to "",
        "ы" to "i",
        "ь" to "",
        "э" to "e",
        "ю" to "yu",
        "я" to "ya"
    )

    fun transliteration(payload: String, divider: String = " "): String {
        var newStr = ""
        if (!payload.contains(" ")) {
            val firstName = payload.toCharArray()
            for (char in firstName) if (char.isUpperCase()) {
                newStr += liters[char.toString().toLowerCase()]?.capitalize() ?: char.toString()
            } else {
                newStr += liters[char.toString().toLowerCase()] ?: char.toString()
            }
        } else {
            val str: List<String> = payload.split(" ")

            for (st in str) {
                for (char in st.toCharArray()) {
                    if (char.isUpperCase()) {
                        newStr+= liters[char.toString().toLowerCase()]?.capitalize() ?: char.toString()
                    } else {
                        newStr+= liters[char.toString().toLowerCase()] ?: char.toString()
                    }
                }
                newStr+=divider
            }
            newStr = newStr.dropLast(1)
        }
        return newStr
    }
}

