package com.homebase.codechallenge.schedules.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.homebase.core.base.BaseViewModel
import com.homebase.core.base.State
import com.homebase.core.service.schedules.ScheduleService
import com.homebase.core.service.NetworkState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SchedulesViewModel : BaseViewModel() {

    private val service = ScheduleService()
    private val _schedulesData = MutableLiveData<State>()
    val schedulesData: LiveData<State> = _schedulesData

    fun getSchedules() {
        _schedulesData.value = State.Loading

        vmScope.launch {

            delay(3000)

            when (val networkResponse = service.getSchedules()) {
                is NetworkState.Success -> _schedulesData.postValue(State.Success(networkResponse.data))
                is NetworkState.Error -> _schedulesData.postValue(State.Error(networkResponse.error ?: ""))
            }
        }
    }

}