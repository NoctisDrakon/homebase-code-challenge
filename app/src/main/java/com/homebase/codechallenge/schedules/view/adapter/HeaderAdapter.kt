package com.homebase.codechallenge.schedules.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.homebase.codechallenge.databinding.HeaderAdapterUiBinding
import com.homebase.codechallenge.schedules.view.listener.AddScheduleListener
import com.homebase.core.ui.adapter.BaseBindingAdapter


class HeaderAdapter(val listener: AddScheduleListener) : BaseBindingAdapter<HeaderAdapterUiBinding>() {

    override fun onCreateBindingViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int): HeaderAdapterUiBinding {
        return HeaderAdapterUiBinding.inflate(inflater, parent, false)
    }

    override fun onBindViewStateHolder(
        binding: HeaderAdapterUiBinding,
        position: Int,
        viewType: Int
    ) {
        binding.addFab.setOnClickListener {
            listener.onAddScheduleClicked()
        }
    }

    override fun getItemCount() = 1
}