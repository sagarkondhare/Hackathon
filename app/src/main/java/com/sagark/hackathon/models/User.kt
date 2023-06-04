package com.sagark.hackathon.models

data class User(
    var userName: String? = "",
    var userId: String? = "",
    var email: String? = "",
    var image: String? = "",
    var searching: Boolean = false,
    var score: Int? = 0,
    var playing: Boolean = false,
    var gameNode: String = "",
    var questions: Questions? = null
)