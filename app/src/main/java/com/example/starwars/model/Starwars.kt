package com.example.starwars.model

data class Starwars(
    var count: Int?,
    val next: String?,
    val previous: Any?,
    val results: List<Result>
)