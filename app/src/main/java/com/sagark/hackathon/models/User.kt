package com.sagark.hackathon.models

data class User(
    var userName: String? = "",
    var userId: String? = "",
    var email: String? = "",
    var image: String? = "",
    var isSearching: Boolean = false,
    var score: Int? = 0
)