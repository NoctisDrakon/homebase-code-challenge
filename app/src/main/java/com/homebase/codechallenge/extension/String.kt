package com.homebase.codechallenge

fun String.getBackgroundResource() : Int {
    return when(this.lowercase()) {
        "red" -> R.drawable.bg_red
        "blue" -> R.drawable.bg_blue
        "green" -> R.drawable.bg_green
        else -> R.drawable.bg_gray
    }
}