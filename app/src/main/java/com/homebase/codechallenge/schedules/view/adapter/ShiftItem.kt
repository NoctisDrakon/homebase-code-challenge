package com.homebase.codechallenge.schedules.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.homebase.codechallenge.R
import com.homebase.codechallenge.databinding.ShiftItemBinding
import com.homebase.codechallenge.getBackgroundResource
import com.homebase.core.service.schedules.model.Shift
import com.homebase.core.ui.adapter.BaseBindingAdapter
import java.text.SimpleDateFormat
import java.util.*

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
        binding.role.text = String.format(binding.root.context.getString(R.string.role_format), shift.role)
        binding.color.setBackgroundResource(shift.color.getBackgroundResource())
        binding.shift.text = getFormattedShift(shift.startDate, shift.endDate)
        getFormattedShift(shift.startDate, shift.endDate)
    }

    override fun getItemCount() = 1

    private fun getFormattedShift(startDate: String, endDate: String) : String{

        val inputFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.US)

        val outputDateFormat = SimpleDateFormat("E, dd MMMM", Locale.US)
        val outputHourFormatIn = SimpleDateFormat("h", Locale.US)
        val outputHourFormatOut = SimpleDateFormat("h a", Locale.US)

        val formattedStartDate = inputFormat.parse(startDate)
        val formattedEndDate = inputFormat.parse(endDate)

        return "${outputDateFormat.format(formattedStartDate)} ${outputHourFormatIn.format(formattedStartDate)}-${outputHourFormatOut.format(formattedEndDate)}"

    }
}