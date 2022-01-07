package com.homebase.codechallenge.schedules.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.homebase.core.base.BaseViewModel
import com.homebase.core.base.State
import com.homebase.core.service.schedules.ScheduleService
import com.homebase.core.service.NetworkState
import com.homebase.core.service.schedules.model.Shift
import com.homebase.core.service.schedules.model.Shifts
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SchedulesViewModel : BaseViewModel() {

    private val service = ScheduleService()
    private val _schedulesData = MutableLiveData<State>()
    val schedulesData: LiveData<State> = _schedulesData

    lateinit var schedules : MutableList<Shift>

    fun getSchedules() {
        _schedulesData.value = State.Loading

        vmScope.launch {

            delay(3000) //So we can see the loading animation :)

            when (val networkResponse = service.getSchedules()) {
                is NetworkState.Success -> {
                    val shiftData = networkResponse.data as? Shifts
                    schedules = shiftData?.shifts?.toMutableList() ?: mutableListOf()
                    _schedulesData.postValue(State.Success(schedules))
                }
                is NetworkState.Error -> _schedulesData.postValue(State.Error(networkResponse.error ?: ""))
            }
        }
    }

    fun addEntry(shift: Shift) {
        schedules.add(shift)
        _schedulesData.value = State.Success(schedules)
    }

}