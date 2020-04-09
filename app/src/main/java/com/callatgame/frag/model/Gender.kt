package com.callatgame.frag.model

import com.callatgame.frag.R

enum class Gender(val res: Int, val value: String) {
    MALE(R.string.male, "M"),
    FEMALE(R.string.female, "F");

    companion object {
        val map = Gender.values().associateBy(Gender::value)
        fun fromValue(v: String) = map[v]
    }

}