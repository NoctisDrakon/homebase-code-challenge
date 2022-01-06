package com.homebase.codechallenge.schedules.view.viewstate

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.recyclerview.widget.ConcatAdapter

class SchedulesFragmentViewState : BaseObservable() {

    @Bindable
    val adapter = ConcatAdapter()

}