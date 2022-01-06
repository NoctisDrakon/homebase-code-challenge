package com.homebase.core.service.schedules

import com.homebase.core.service.schedules.model.Shift
import com.homebase.core.service.schedules.model.Shifts
import retrofit2.Response
import retrofit2.http.GET

interface ScheduleApiClient {
    @GET("/b/0KZP") //Path in which mock schedules are stored
    suspend fun getShifts(): Response<Shifts>
}