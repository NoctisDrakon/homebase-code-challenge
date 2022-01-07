package com.homebase.core.service.schedules.model

import com.google.gson.annotations.SerializedName

data class Shift(
    val role: String = "",
    val name: String = "",
    @SerializedName("start_date")
    val startDate: String = "",
    @SerializedName("end_date")
    val endDate: String = "",
    val color: String = ""
)
