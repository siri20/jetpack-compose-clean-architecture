package com.siri.sample.ui.practice.model

import com.siri.core.R

sealed class PracticeScreen(val route: String) {
    object Landing : Practice(
        route = "landing",
        titleResId = R.string.landing,
    )
}