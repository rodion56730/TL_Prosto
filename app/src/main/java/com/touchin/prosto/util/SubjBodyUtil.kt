package com.touchin.prosto.util

object SubjBodyUtil {
    private const val BASE = "[A-Za-z]"
    val SUBJECT_AND_BODY_REGEX = BASE.toRegex()
}