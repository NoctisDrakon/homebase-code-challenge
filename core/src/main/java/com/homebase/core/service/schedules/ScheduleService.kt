package com.homebase.core.service.schedules

import com.homebase.core.service.BaseService
import com.homebase.core.service.NetworkState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class ScheduleService : BaseService() {

    suspend fun getSchedules(): NetworkState {
        var result : NetworkState

        withContext(Dispatchers.IO) {
            result = try {
                val serviceResponse = retrofit.create(ScheduleApiClient::class.java).getShifts()
                NetworkState.Success(serviceResponse.body())
            } catch (e : Exception) {
                NetworkState.Error(e.message)
            }
        }
        return result
    }

}