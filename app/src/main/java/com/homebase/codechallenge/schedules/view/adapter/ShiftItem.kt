package com.homebase.codechallenge.schedules.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.homebase.codechallenge.databinding.ShiftItemBinding
import com.homebase.core.service.schedules.model.Shift
import com.homebase.core.ui.adapter.BaseBindingAdapter

class ShiftItem(var shift: Shift) : BaseBindingAdapter<ShiftItemBinding>() {

    override fun onCreateBindingViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): ShiftItemBinding {
        return ShiftItemBinding.inflate(inflater, parent, false)
    }

    override fun onBindViewStateHolder(binding: ShiftItemBinding, position: Int, viewType: Int) {
        binding.name.text = shift.name
        binding.role.text = shift.role
    }

    override fun getItemCount() = 1
}