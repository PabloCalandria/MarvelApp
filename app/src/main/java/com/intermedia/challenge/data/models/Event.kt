package com.intermedia.challenge.data.models


data class Event(
    val description: String = "",
    val id: Int = 0,
    val title: String = "",
    val start: String = "",
    val thumbnail: Thumbnail = Thumbnail(),
    val urls: List<Url> = listOf(),
    val comics: Appearances = Appearances()
)
