package com.touchin.prosto.feature.support

data class SupportState(
    val emailText: String = "",
    val hasEmailError: Boolean = false,
    val subjectText: String = "",
    val hasSubjectError: Boolean = false,
    val bodyText: String = "",
    val hasBodyError: Boolean = false
) {
    val sendButtonEnable: Boolean
        get() = listOf(
            emailText.isBlank(),
            hasEmailError,
            subjectText.isBlank(),
            hasSubjectError,
            bodyText.isBlank(),
            hasBodyError,
        ).all { hasError -> !hasError }
}

