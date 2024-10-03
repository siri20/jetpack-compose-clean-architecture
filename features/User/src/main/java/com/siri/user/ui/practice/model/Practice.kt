package com.siri.user.ui.practice.model

import androidx.annotation.StringRes

sealed class Practice(
    val route: String,
    @StringRes val titleResId: Int,
)