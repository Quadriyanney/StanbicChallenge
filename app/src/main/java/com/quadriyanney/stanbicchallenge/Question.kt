package com.quadriyanney.stanbicchallenge

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Question (
        val question: String,
        val options: List<String>
) : Parcelable