package com.siri.sample.ui.practice.model

import androidx.annotation.StringRes

sealed class Practice(
    val route: String,
    @StringRes val titleResId: Int,
)