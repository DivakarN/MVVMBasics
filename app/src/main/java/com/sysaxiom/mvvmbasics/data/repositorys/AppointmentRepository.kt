package com.sysaxiom.mvvmbasics.data.repositorys

import com.sysaxiom.mvvmbasics.data.models.AppointmentResponse
import com.sysaxiom.mvvmbasics.data.network.NetworkApis
import com.sysaxiom.mvvmbasics.data.network.SafeApiRequest

class AppointmentRepository (val networkApis: NetworkApis) : SafeApiRequest() {

    suspend fun getAppointment(id : String): AppointmentResponse {
        return apiRequest { networkApis.getAppointment(id) }
    }

}