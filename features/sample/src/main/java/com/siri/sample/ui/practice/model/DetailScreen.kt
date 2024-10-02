package com.siri.sample.ui.practice.model

sealed class DetailScreen(val route: String) {

    object Detail : PracticeScreen("landing/{userId}") {
        fun createRoute(userId: Int) = "landing/$userId"
    }
}