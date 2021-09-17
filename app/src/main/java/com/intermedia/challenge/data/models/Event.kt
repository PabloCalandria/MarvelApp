package com.intermedia.challenge.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Event(
    val description: String = "",
    val id: Int = 0,
    val title: String = "",
    val start: String = "",
    val thumbnail: Thumbnail = Thumbnail(),
    val urls: List<Url> = listOf(),
    val comics: Appearances = Appearances()
): Parcelable
