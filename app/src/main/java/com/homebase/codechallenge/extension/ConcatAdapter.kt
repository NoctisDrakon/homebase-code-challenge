package com.homebase.codechallenge.extension

import androidx.recyclerview.widget.ConcatAdapter

fun ConcatAdapter.clear() {
    adapters.forEach {
        removeAdapter(it)
    }
}