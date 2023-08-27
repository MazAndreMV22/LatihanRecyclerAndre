package com.example.latihanrecyclerview

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Coffee(
    var name: String,
    var description: String,
    var image: Int
) : Parcelable
