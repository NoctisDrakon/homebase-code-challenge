package com.homebase.codechallenge.schedules.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.homebase.codechallenge.databinding.LoadingUiBinding
import com.homebase.core.ui.adapter.BaseBindingAdapter

class LoadingAdapter : BaseBindingAdapter<LoadingUiBinding>() {
    override fun onCreateBindingViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): LoadingUiBinding {
        return LoadingUiBinding.inflate(inflater, parent, false)
    }

    override fun onBindViewStateHolder(binding: LoadingUiBinding, position: Int, viewType: Int) = Unit //no-op

    override fun getItemCount() = 1
}